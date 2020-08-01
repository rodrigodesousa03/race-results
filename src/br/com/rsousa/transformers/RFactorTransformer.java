package br.com.rsousa.transformers;

import static br.com.rsousa.enums.FileType.QUALIFY;
import static br.com.rsousa.enums.FileType.RACE;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import static java.util.stream.Collectors.toList;

import br.com.rsousa.enums.FileType;
import br.com.rsousa.formatter.SessionFormatter;
import br.com.rsousa.pojo.Session;
import br.com.rsousa.pojo.SessionType;
import br.com.rsousa.pojo.ams.Driver;
import br.com.rsousa.pojo.ams.RFactorXML;

public class RFactorTransformer {
    private static final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern("m:ss");

    public static void processQualify(File file, List<br.com.rsousa.pojo.Driver> driverTeams, Map<FileType, String> results) {
        if (file != null) {
            JAXBContext jaxbContext;
            try {
                jaxbContext = JAXBContext.newInstance(RFactorXML.class);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

                InputStream inputStream = new FileInputStream(file);

                Reader reader = new InputStreamReader(inputStream, "UTF-8");

                RFactorXML raceResult = (RFactorXML) jaxbUnmarshaller.unmarshal(reader);

                if (raceResult.getRaceResults().getQualify() == null) {
                    processRace(file, driverTeams, results);

                    return;
                }

                Driver[] drivers = raceResult.getRaceResults().getQualify().getDriver();

                int position = 1;

                Session session = new Session(SessionType.QUALIFY);

                for (Driver driver : drivers) {
                    if (isDriver(driver)) {
                        String bestLapTimeFormatted = formatSeconds(driver.getBestLapTime(), "sem tempo");

                        session.addDriver(DriverTransformer.toDriver(driver, position, bestLapTimeFormatted, driverTeams));

                        position++;
                    }
                }

                results.put(QUALIFY, SessionFormatter.format(session));
            } catch (Exception e) {
                results.put(QUALIFY, "Error transforming the XML file.");

                e.printStackTrace();
            }
        }
    }

    public static void processRace(File file, List<br.com.rsousa.pojo.Driver> driverTeams, Map<FileType, String> results) {
        if (file != null) {
            JAXBContext jaxbContext;
            try {
                jaxbContext = JAXBContext.newInstance(RFactorXML.class);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

                InputStream inputStream = new FileInputStream(file);

                Reader reader = new InputStreamReader(inputStream, "UTF-8");

                RFactorXML raceResult = (RFactorXML) jaxbUnmarshaller.unmarshal(reader);

                if (raceResult.getRaceResults().getRace() == null) {
                    processQualify(file, driverTeams, results);

                    return;
                }

                List<Driver> drivers = Stream.of(raceResult.getRaceResults().getRace().getDriver())
                        .sorted(Comparator.comparing(Driver::getPosition))
                        .collect(toList());

                Driver polePositionDriver = drivers.stream()
                        .filter(d -> "1".equals(d.getGridPos()))
                        .findFirst()
                        .orElse(null);

                Session session = new Session(SessionType.RACE);

                Integer position = 1;
                String resultStr = "";
                Driver driverBestLap = null;
                Driver driverWinner = null;
                Integer totalLaps = 0;
                Double leaderFinishTime = null;

                for (Driver driver : drivers) {
                    if (isDriver(driver)) {
                        String raceTimeFormatted;

                        if (driver.getPosition() == 1) {
                            driverBestLap = driver;
                            raceTimeFormatted = driver.getLaps() + " voltas";
                            totalLaps = driver.getLaps();
                            leaderFinishTime = Double.parseDouble(driver.getFinishTime());
                            driverWinner = driver;
                        } else {
                            if (driver.getBestLapTime() != null && Double.parseDouble(driverBestLap.getBestLapTime()) > Double.parseDouble(driver.getBestLapTime())) {
                                driverBestLap = driver;
                            }

                            if (totalLaps == driver.getLaps()) {
                                Double secondsBehindTheLeader = Double.parseDouble(driver.getFinishTime()) - leaderFinishTime;

                                raceTimeFormatted = formatSeconds(secondsBehindTheLeader.toString());

                            } else {
                                int lapsBehindTheLeader = totalLaps - driver.getLaps();

                                raceTimeFormatted = "+" + lapsBehindTheLeader + " laps";
                            }
                        }

                        if (!"Finished Normally".equals(driver.getFinishStatus()) && !"None".equals(driver.getFinishStatus())) {
                            raceTimeFormatted += " (" + driver.getFinishStatus() + ")";
                        }

                        session.addDriver(DriverTransformer.toDriver(driver, position, raceTimeFormatted, driverTeams));

                        position++;
                    }
                }

                String driverBestLapName = driverBestLap.getName();

                br.com.rsousa.pojo.Driver sessionDriverBestLap = session.drivers().stream()
                        .filter(d -> d.getName().equals(driverBestLapName))
                        .findFirst()
                        .orElse(null);
                sessionDriverBestLap.setBestLap(true);
                
                if (polePositionDriver.equals(driverBestLap) && driverWinner.equals(driverBestLap)) {
                    boolean ledAllTheLaps = Arrays.asList(driverWinner.getLap()).stream().allMatch(l -> "1".equals(l.getP()));

                    sessionDriverBestLap.setHattrick(true);

                    if (ledAllTheLaps) {
                        sessionDriverBestLap.setGrandChelem(true);
                    }
                }

                results.put(RACE, SessionFormatter.format(session));
            } catch (Exception e) {
                results.put(RACE, "Error transforming the XML file.");

                e.printStackTrace();
            }
        }
    }

    private static boolean isDriver(Driver driver) {
        return !driver.getName().contains("Diretor") && !driver.getName().contains("Comentarista") && !driver.getName().contains("Narrador");
    }

    private static String resultLine(int position, Driver driver, String time, List<br.com.rsousa.pojo.Driver> driverTeams) {
        String driverTeamName = driverTeams.stream().filter(d -> driver.getName().trim().equals(d.getName()))
                .map(d -> d.getTeam())
                .findAny()
                .orElse(driver.getTeamName());

        return position + " " + driver.getName().trim() + " (" + driverTeamName.trim() + "), " + time;
    }

    private static String formatSeconds(String time, String textIfNull) {
        String formattedSeconds = formatSeconds(time);

        if (formattedSeconds == null) {
            return textIfNull;
        }

        return formattedSeconds;
    }

    private static String formatSeconds(String time) {
        if (time == null) {
            return null;
        }

        String[] bestLapTime = time.split("\\.");

        Integer totalSeconds = Integer.parseInt(bestLapTime[0]);
        String milliseconds = bestLapTime[1].substring(0, 3);

        String bestLapTimeFormatted = LocalTime.MIN.plusSeconds(totalSeconds).format(MINUTE_FORMATTER) + "."
                + milliseconds;
        return bestLapTimeFormatted;
    }
}

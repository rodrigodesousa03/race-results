package br.com.rsousa.transformers;

import br.com.rsousa.pojo.DriverStatus;
import br.com.rsousa.pojo.Session;
import br.com.rsousa.pojo.SessionType;
import br.com.rsousa.pojo.ams.Driver;
import br.com.rsousa.pojo.ams.RFactorXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class RFactorTransformer {
    private static final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern("m:ss");

    public static Session processQualify(File file, List<br.com.rsousa.pojo.Driver> driverTeams) {
        if (file != null) {
            JAXBContext jaxbContext;
            try {
                jaxbContext = JAXBContext.newInstance(RFactorXML.class);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

                InputStream inputStream = new FileInputStream(file);

                Reader reader = new InputStreamReader(inputStream, "UTF-8");

                RFactorXML raceResult = (RFactorXML) jaxbUnmarshaller.unmarshal(reader);

                if (raceResult.getRaceResults().getQualify() == null) {
                    return processRace(file, driverTeams);
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

                return session;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static Session processRace(File file, List<br.com.rsousa.pojo.Driver> driverTeams) {
        if (file != null) {
            JAXBContext jaxbContext;
            try {
                jaxbContext = JAXBContext.newInstance(RFactorXML.class);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

                InputStream inputStream = new FileInputStream(file);

                Reader reader = new InputStreamReader(inputStream, "UTF-8");

                RFactorXML raceResult = (RFactorXML) jaxbUnmarshaller.unmarshal(reader);

                if (raceResult.getRaceResults().getRace() == null) {
                    return processQualify(file, driverTeams);
                }

                List<Driver> drivers = Stream.of(raceResult.getRaceResults().getRace().getDriver())
                        .sorted(Comparator.comparing(Driver::getPosition))
                        .collect(toList());

                Session session = new Session(SessionType.RACE);

                Integer position = 1;
                Driver driverWinner = null;
                Integer totalLaps = 0;
                Double leaderFinishTime = null;

                for (Driver driver : drivers) {
                    if (isDriver(driver)) {
                        String raceTimeFormatted;

                        if (driver.getPosition() == 1) {
                            raceTimeFormatted = driver.getLaps() + " voltas";
                            totalLaps = driver.getLaps();
                            leaderFinishTime = Double.parseDouble(driver.getFinishTime());
                            driverWinner = driver;
                        } else {
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

                        br.com.rsousa.pojo.Driver sessionDriver = DriverTransformer.toDriver(driver, position, raceTimeFormatted, driverTeams);

                        if ("1".equals(driver.getGridPos())) {
                            sessionDriver.setPoleposition(true);
                        }

                        if (totalLaps/2 > driver.getLaps()) {
                            sessionDriver.setStatus(DriverStatus.DID_NOT_FINISH);
                        }

                        session.addDriver(sessionDriver);

                        position++;
                    }
                }

                br.com.rsousa.pojo.Driver sessionDriverBestLap = session.bestLapDriver();

                if (sessionDriverBestLap.isPoleposition() && driverWinner.getName().equals(sessionDriverBestLap.getName())) {
                    boolean ledAllTheLaps = Arrays.asList(driverWinner.getLap()).stream().allMatch(l -> "1".equals(l.getP()));

                    sessionDriverBestLap.setHattrick(true);
                    sessionDriverBestLap.setPoleposition(true);

                    if (ledAllTheLaps) {
                        sessionDriverBestLap.setGrandChelem(true);
                    }
                }

                return session;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private static boolean isDriver(Driver driver) {
        return !driver.getName().contains("Diretor") && !driver.getName().contains("Comentarista") && !driver.getName().contains("Narrador");
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

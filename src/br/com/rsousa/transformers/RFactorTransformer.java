package br.com.rsousa.transformers;

import br.com.rsousa.pojo.DriverStatus;
import br.com.rsousa.pojo.Event;
import br.com.rsousa.pojo.Session;
import br.com.rsousa.pojo.SessionType;
import br.com.rsousa.pojo.ams.Driver;
import br.com.rsousa.pojo.ams.Lap;
import br.com.rsousa.pojo.ams.RFactorXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class RFactorTransformer implements SimulatorTransformer {
    private static final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern("m:ss");

    @Override
    public Event processEvent(File file, List<br.com.rsousa.pojo.Driver> driverTeams, boolean hardDnf, boolean isSelective) {
        return null;
    }

    public Session processQualify(File file, List<br.com.rsousa.pojo.Driver> driverTeams, boolean hardDnf, boolean isSelective) {
        if (file != null) {
            JAXBContext jaxbContext;
            try {
                jaxbContext = JAXBContext.newInstance(RFactorXML.class);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

                InputStream inputStream = Files.newInputStream(file.toPath());

                Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

                RFactorXML raceResult = (RFactorXML) jaxbUnmarshaller.unmarshal(reader);

                if (raceResult.getRaceResults().getQualify() == null) {
                    return processRace(file, driverTeams, hardDnf);
                }

                Driver[] drivers = raceResult.getRaceResults().getQualify().getDriver();

                int position = 1;

                Session session = new Session(SessionType.QUALIFY, isSelective);

                for (Driver driver : drivers) {
                    if (isDriver(driver.getName())) {
                        String bestLapTimeFormatted = formatSecondsWithTextIfNull(driver.getBestLapTime());

                        session.addDriver(DriverTransformer.toDriver(driver, position, bestLapTimeFormatted, 0D, driverTeams));

                        position++;
                    }
                }

                return session;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return null;
    }

    public Session processRace(File file, List<br.com.rsousa.pojo.Driver> driverTeams, boolean hardDnf) {
        if (file != null) {
            JAXBContext jaxbContext;
            try {
                jaxbContext = JAXBContext.newInstance(RFactorXML.class);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

                InputStream inputStream = Files.newInputStream(file.toPath());

                Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

                RFactorXML raceResult = (RFactorXML) jaxbUnmarshaller.unmarshal(reader);

                if (raceResult.getRaceResults().getRace() == null) {
                    return processQualify(file, driverTeams, hardDnf, false);
                }

                List<Driver> drivers = Stream.of(raceResult.getRaceResults().getRace().getDriver())
                        .sorted(Comparator.comparing(Driver::getPosition))
                        .collect(toList());

                Session session = new Session(SessionType.RACE, false);

                int position = 1;
                Driver driverWinner = null;
                Integer totalLaps = 0;
                Double leaderFinishTime = null;

                for (Driver driver : drivers) {
                    if (isDriver(driver.getName())) {
                        String raceTimeFormatted;

                        double driverTotalTime = 0D;

                        if (driver.getLap() != null) {
                            for (Lap lap: driver.getLap()) {
                                try {
                                    driverTotalTime += Double.parseDouble(lap.getEt());
                                } catch (NumberFormatException exception) {
                                    // Do Nothing
                                }
                            }
                        }

                        if (driver.getPosition() == 1) {
                            raceTimeFormatted = driver.getLaps() + " voltas";
                            totalLaps = driver.getLaps();
                            leaderFinishTime = Double.parseDouble(driver.getFinishTime());
                            driverWinner = driver;
                        } else {
                            if (totalLaps.equals(driver.getLaps())) {
                                if (driver.getFinishTime() != null && leaderFinishTime != null) {
                                    double secondsBehindTheLeader = Double.parseDouble(driver.getFinishTime()) - leaderFinishTime;

                                    raceTimeFormatted = formatSeconds(Double.toString(secondsBehindTheLeader));
                                } else {
                                    raceTimeFormatted = "Disqualificado";
                                }
                            } else {
                                int lapsBehindTheLeader = totalLaps - driver.getLaps();

                                raceTimeFormatted = "+" + lapsBehindTheLeader + " laps";
                            }
                        }

                        if (!"Finished Normally".equals(driver.getFinishStatus()) && !"None".equals(driver.getFinishStatus())) {
                            raceTimeFormatted += " (" + driver.getFinishStatus() + ")";
                        }

                        br.com.rsousa.pojo.Driver sessionDriver = DriverTransformer.toDriver(driver, position, raceTimeFormatted, driverTotalTime, driverTeams);

                        if ("1".equals(driver.getGridPos())) {
                            sessionDriver.setPolePosition(true);
                        }

                        if (hardDnf && (!"Finished Normally".equals(driver.getFinishStatus()) && !"None".equals(driver.getFinishStatus()))) {
                            sessionDriver.setStatus(DriverStatus.DID_NOT_FINISH);
                        }

                        if (totalLaps/2 > driver.getLaps()) {
                            sessionDriver.setStatus(DriverStatus.DID_NOT_FINISH);
                        }

                        session.addDriver(sessionDriver);

                        position++;
                    }
                }

                br.com.rsousa.pojo.Driver sessionDriverBestLap = session.bestLapDriver();

                if (sessionDriverBestLap.isPolePosition() && driverWinner != null && driverWinner.getName().equals(sessionDriverBestLap.getName())) {
                    boolean ledAllTheLaps = Arrays.stream(driverWinner.getLap()).allMatch(l -> "1".equals(l.getP()));

                    sessionDriverBestLap.setHattrick(true);
                    sessionDriverBestLap.setPolePosition(true);

                    if (ledAllTheLaps) {
                        sessionDriverBestLap.setGrandChelem(true);
                    }
                }

                return session;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return null;
    }

    private String formatSecondsWithTextIfNull(String time) {
        String formattedSeconds = formatSeconds(time);

        if (formattedSeconds == null) {
            return "sem tempo";
        }

        return formattedSeconds;
    }

    private String formatSeconds(String time) {
        if (time == null) {
            return null;
        }

        String[] bestLapTime = time.split("\\.");

        int totalSeconds = Integer.parseInt(bestLapTime[0]);

        String milliseconds;

        if (bestLapTime[1].length() >= 3) {
            milliseconds = bestLapTime[1].substring(0, 3);
        } else {
            milliseconds = bestLapTime[1].substring(0, 2)+"0";
        }

        return LocalTime.MIN.plusSeconds(totalSeconds).format(MINUTE_FORMATTER) + "." + milliseconds;
    }

    @Override
    public Boolean processEvent() {
        return false;
    }
}

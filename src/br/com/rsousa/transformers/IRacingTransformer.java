package br.com.rsousa.transformers;

import static br.com.rsousa.enums.FileType.QUALIFY;
import static br.com.rsousa.enums.FileType.RACE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import br.com.rsousa.enums.FileType;
import br.com.rsousa.formatter.SessionFormatter;
import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.Session;
import br.com.rsousa.pojo.SessionType;
import br.com.rsousa.pojo.iracing.DriverSession;

public class IRacingTransformer {
    public static void processQualify(File file, List<Driver> driverTeams, Map<FileType, String> results) {
        BufferedReader br = null;
        String line = "";
        boolean reachedFirstLine = false;
        int position = 1;

        if (file != null) {
            try {
                br = new BufferedReader(new FileReader(file));
                Session session = new Session(SessionType.QUALIFY);
                while ((line = br.readLine()) != null || !reachedFirstLine) {

                    if (line != null && !reachedFirstLine) {
                        String[] lineArray = line.replaceAll("\"", "").split(",");

                        if (lineArray[0].equals("1")) {
                            reachedFirstLine = true;
                        }
                    }

                    if (reachedFirstLine) {
                        DriverSession driverSession = transformLineInDriverSession(file, driverTeams, results, line);

                        if (position == 1 && driverSession.getQualifyTime().isEmpty()) {
                            processRace(file, driverTeams, results);

                            return;
                        }

                        if (driverSession != null) {
                            Driver driver = DriverTransformer.toDriver(driverSession, driverSession.getFinalPosition(), driverSession.getFastLap(), driverTeams);
                            session.addDriver(driver);
                        }

                        position++;
                    }
                }

                results.put(QUALIFY, SessionFormatter.format(session));
            } catch (Exception e) {
                results.put(QUALIFY, "Error transforming the CSV file.");

                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }

    public static void processRace(File file, List<Driver> driverTeams, Map<FileType, String> results) {
        BufferedReader br = null;
        String line = "";
        int position = 1;
        DriverSession driverBestLap = null;
        boolean reachedFirstLine = false;

        if (file != null) {
            try {
                br = new BufferedReader(new FileReader(file));
                Session session = new Session(SessionType.RACE);
                DriverSession winner = null;
                while ((line = br.readLine()) != null || !reachedFirstLine) {
                    if (line != null && !reachedFirstLine) {
                        String[] lineArray = line.replaceAll("\"", "").split(",");

                        if (lineArray[0].equals("1")) {
                            reachedFirstLine = true;
                        }
                    }

                    if (reachedFirstLine) {

                        DriverSession driverSession = transformLineInDriverSession(file, driverTeams, results, line);
                        Driver driver = null;
                        if (position == 1) {
                            driver = DriverTransformer.toDriver(driverSession, position, driverSession.getCompletedLaps() + " Laps", driverTeams);
                            driverBestLap = driverSession;
                            winner = driverSession;
                        } else {
                            if (driverSession != null) {
                                if (!driverSession.getFastLap().trim().isEmpty() && driverBestLap.getFastLap().compareTo(driverSession.getFastLap()) > 0) {
                                    driverBestLap = driverSession;
                                }

                                String formattedTime = driverSession.getOut().equals("Disqualified") ? driverSession.getInterval() + " (DNF)" : driverSession.getInterval();

                                driver = DriverTransformer.toDriver(driverSession, position, formattedTime, driverTeams);
                            }
                        }

                        if (driver != null) {
                            session.addDriver(driver);

                            if (driverSession.getStartPosition() == 1) {
                                driver.setPoleposition(true);
                            }
                        }

                        position++;
                    }
                }

                String driverBestLapName = driverBestLap.getName();

                br.com.rsousa.pojo.Driver sessionDriverBestLap = session.drivers().stream()
                        .filter(d -> d.getName().equals(driverBestLapName))
                        .findFirst()
                        .orElse(null);
                sessionDriverBestLap.setBestLap(true);

                if (sessionDriverBestLap.isPoleposition() && sessionDriverBestLap.getPosition() == 1) {
                    sessionDriverBestLap.setHattrick(true);

                    boolean ledAllTheLaps = winner.getCompletedLaps() == winner.getLapsLed();

                    if (ledAllTheLaps) {
                        sessionDriverBestLap.setGrandChelem(true);
                    }
                }

                results.put(RACE, SessionFormatter.format(session));
            } catch (Exception e) {
                results.put(RACE, "Error transforming the CSV file.");

                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static DriverSession transformLineInDriverSession(File file, List<Driver> driverTeams, Map<FileType, String> results,
                                                              String line) {
        String[] driverSessionArray = line.replaceAll("\"", "").split(",");

        if (driverSessionArray[0].equals("Fin Pos")) {
            return null;
        }

        DriverSession driverSession = new DriverSession();
        driverSession.setFinalPosition(Integer.parseInt(driverSessionArray[0]));
        driverSession.setId(driverSessionArray[6]);
        driverSession.setName(driverSessionArray[7]);
        driverSession.setStartPosition(Integer.parseInt(driverSessionArray[8]));
        driverSession.setOut(driverSessionArray[11]);
        driverSession.setInterval(driverSessionArray[12]);
        driverSession.setLapsLed(Integer.parseInt(driverSessionArray[13]));
        driverSession.setQualifyTime(driverSessionArray[14]);
        driverSession.setFastLap(driverSessionArray[16]);
        driverSession.setCompletedLaps(Integer.parseInt(driverSessionArray[18]));

        return driverSession;
    }

    private static String resultLine(int position, DriverSession driver, String time, List<Driver> driverTeams) {
        String driverTeamName = driverTeams.stream().filter(d -> driver.getId().equals(d.getId()))
                .map(d -> d.getTeam())
                .findAny()
                .orElse("Independente");

        String name = driverTeams.stream().filter(d -> driver.getId().equals(d.getId()))
                .map(d -> d.getName())
                .findAny()
                .orElse(driver.getName());

        time = !time.trim().isEmpty() ? time : "sem tempo";

        return position + " " + name + " (" + driverTeamName + "), " + time;
    }
}

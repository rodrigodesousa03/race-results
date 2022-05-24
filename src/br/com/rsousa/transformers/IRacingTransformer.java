package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.DriverStatus;
import br.com.rsousa.pojo.Session;
import br.com.rsousa.pojo.SessionType;
import br.com.rsousa.pojo.iracing.DriverSession;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class IRacingTransformer implements SimulatorTransformer {
    public Session processQualify(File file, List<Driver> driverTeams, boolean dnfRigido) {
        BufferedReader br = null;
        String line;
        boolean reachedFirstLine = false;
        int position = 1;
        Session session = new Session(SessionType.QUALIFY);

        if (file != null) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null || !reachedFirstLine) {

                    if (line != null && !reachedFirstLine) {
                        String[] lineArray = line.replaceAll("\"", "").split(",");

                        if (lineArray[0].equals("1")) {
                            reachedFirstLine = true;
                        }
                    }

                    if (reachedFirstLine) {
                        DriverSession driverSession = transformLineInDriverSession(line);

                        if (position == 1 && driverSession != null && driverSession.getQualifyTime().isEmpty()) {
                            return processRace(file, driverTeams, dnfRigido);
                        }

                        if (driverSession != null) {
                            Driver driver = DriverTransformer.toDriver(driverSession, driverSession.getFinalPosition(), driverSession.getFastLap(), driverTeams);
                            session.addDriver(driver);
                        }

                        position++;
                    }
                }
            } catch (Exception e) {
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

        return session;
    }

    public Session processRace(File file, List<Driver> driverTeams, boolean dnfRigido) {
        BufferedReader br = null;
        String line;
        int position = 1;
        int laps = 0;
        boolean reachedFirstLine = false;
        Session session = new Session(SessionType.RACE);

        if (file != null) {
            try {
                br = new BufferedReader(new FileReader(file));
                DriverSession winner = null;
                boolean isOval = false;
                while ((line = br.readLine()) != null || !reachedFirstLine) {
                    if (line != null && !reachedFirstLine) {
                        String[] lineArray = line.replaceAll("\"", "").split(",");

                        if (lineArray.length > 1 && lineArray[1].contains("Oval")) {
                            isOval = true;
                        }

                        if (lineArray[0].equals("1")) {
                            reachedFirstLine = true;
                        }
                    }

                    if (reachedFirstLine) {

                        DriverSession driverSession = transformLineInDriverSession(line);
                        Driver driver = null;
                        if (position == 1) {
                            driver = DriverTransformer.toDriver(driverSession, position, driverSession.getCompletedLaps() + " Laps", driverTeams);
                            winner = driverSession;
                            laps = driver.getLaps();
                        } else {
                            if (driverSession != null) {
                                if (driverSession.getCompletedLaps() == laps) {
                                    driver = DriverTransformer.toDriver(driverSession, position, driverSession.getInterval(), driverTeams);
                                } else {
                                    int lapsDifference = laps - driverSession.getCompletedLaps();

                                    driver = DriverTransformer.toDriver(driverSession, position, "+" + lapsDifference + " laps", driverTeams);
                                }

                            }
                        }

                        if (driver != null) {
                            if (!isOval) {
                                if (laps / 2 > driver.getLaps()) {
                                    driver.setStatus(DriverStatus.DID_NOT_FINISH);
                                }
                            }

                            session.addDriver(driver);

                            if (driverSession.getStartPosition() == 1) {
                                driver.setPoleposition(true);
                            }
                        }

                        position++;
                    }
                }

                if (session.bestLapDriver() != null) {
                    if (session.bestLapDriver().isPoleposition() && session.bestLapDriver().getPosition() == 1) {
                        session.bestLapDriver().setHattrick(true);

                        boolean ledAllTheLaps = winner.getCompletedLaps() == winner.getLapsLed();

                        if (ledAllTheLaps) {
                            session.bestLapDriver().setGrandChelem(true);
                        }
                    }
                }

            } catch (Exception e) {
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

        return session;
    }

    private DriverSession transformLineInDriverSession(String line) {
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
}

package br.com.rsousa.transformers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.com.rsousa.pojo.DriverStatus;
import br.com.rsousa.pojo.SessionType;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.assetto.Lap;
import br.com.rsousa.pojo.assetto.Result;
import br.com.rsousa.pojo.assetto.Session;

public class AssettoTransformer {
    private static final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern("m:ss");

    public static br.com.rsousa.pojo.Session processQualify(File file, List<Driver> driverTeams) {
        br.com.rsousa.pojo.Session session = null;

        if (file != null) {
            try {
                Session assettoSession = createSession(file);

                if ("RACE".equals(assettoSession.getType())) {
                    return processRace(file, driverTeams);
                }

                session = new br.com.rsousa.pojo.Session(SessionType.QUALIFY);

                int position = 1;

                for (Result result : assettoSession.getResult()) {
                    if (isDriver(result.getDriverName()) && result.getBestLap() != 999999999) {
                        Driver driver = DriverTransformer.toDriver(result, position, lapsFor(result.getDriverName(), assettoSession.getLaps()).intValue(), formatSeconds(result.getBestLap()), driverTeams);
                        session.addDriver(driver);

                        position++;
                    }
                }
            } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return session;
    }

    public static br.com.rsousa.pojo.Session processRace(File file, List<Driver> driverTeams) {
        br.com.rsousa.pojo.Session session = null;

        if (file != null) {
            try {
                Session assettoSession = createSession(file);

                int position = 1;
                Result driverBestLap = null;
                long totalLaps = 0;
                List<Lap> laps = assettoSession.getLaps();
                int leaderFinishTime = 0;
                String raceTimeFormatted;
                session = new br.com.rsousa.pojo.Session(SessionType.RACE);

                for (Result result : assettoSession.getResult()) {
                    if (isDriver(result.getDriverName()) && result.getBestLap() != 999999999) {
                        Long driverLaps = lapsFor(result.getDriverName(), laps);
                        if (position == 1) {
                            driverBestLap = result;
                            raceTimeFormatted = driverLaps + " voltas";
                            totalLaps = driverLaps;
                            leaderFinishTime = result.getTotalTime();
                        } else {
                            if (driverBestLap.getBestLap().compareTo(result.getBestLap()) > 0) {
                                driverBestLap = result;
                            }

                            if (totalLaps == driverLaps) {
                                int secondsBehindTheLeader = result.getTotalTime() - leaderFinishTime;

                                raceTimeFormatted = formatSeconds(secondsBehindTheLeader);
                            } else {
                                long lapsBehindTheLeader = totalLaps - driverLaps;

                                raceTimeFormatted = "+ " + lapsBehindTheLeader + " laps";
                            }
                        }

                        Driver driver = DriverTransformer.toDriver(result, position, driverLaps.intValue(), raceTimeFormatted, driverTeams);
                        driver.setRaceTime(formatSeconds(result.getTotalTime()));

                        if (totalLaps/2 > driverLaps) {
                            driver.setStatus(DriverStatus.DID_NOT_FINISH);
                        }

                        session.addDriver(driver);

                        position++;
                    }
                }

                if (driverBestLap != null) {
                    String driverBestLapName = driverBestLap.getDriverName();

                    session.drivers().stream()
                            .filter(d -> d.getName().equals(driverBestLapName))
                            .findFirst()
                            .ifPresent(s -> s.setBestLap(true));

                }
            } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return session;
    }

    private static Long lapsFor(String driverName, List<Lap> laps) {
        return laps.stream().filter(l -> l.getDriverName().equals(driverName)).count();
    }

    private static Session createSession(File file) throws FileNotFoundException {
        Gson gson = new Gson();

        return gson.fromJson(new FileReader(file), Session.class);
    }

    private static String formatSeconds(int time) {
        String bestLapTime = String.valueOf(time);

        int totalSeconds = 0;

        if (bestLapTime.length() > 3) {
            totalSeconds = Integer.parseInt(bestLapTime.substring(0, bestLapTime.length() - 3));
        }

        String milliseconds = bestLapTime.substring(bestLapTime.length() - 3);

        return LocalTime.MIN.plusSeconds(totalSeconds).format(MINUTE_FORMATTER) + "."
                + milliseconds;
    }

    private static boolean isDriver(String name) {
        return !name.isEmpty() && !name.contains("Diretor") && !name.contains("Comentarista") && !name.contains("Narrador");
    }
}

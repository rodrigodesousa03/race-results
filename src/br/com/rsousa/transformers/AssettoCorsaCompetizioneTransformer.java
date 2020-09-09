package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.DriverStatus;
import br.com.rsousa.pojo.SessionType;
import br.com.rsousa.pojo.acc.CurrentDriver;
import br.com.rsousa.pojo.acc.LeaderBoardLine;
import br.com.rsousa.pojo.acc.Session;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AssettoCorsaCompetizioneTransformer {
    private static final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern("m:ss");

    public static br.com.rsousa.pojo.Session processQualify(File file, List<Driver> driverTeams) {
        br.com.rsousa.pojo.Session session = null;

        if (file != null) {
            try {
                Session assettoSession = createSession(file);

                if ("R".equals(assettoSession.getSessionType())) {
                    return processRace(file, driverTeams);
                }

                session = new br.com.rsousa.pojo.Session(SessionType.QUALIFY);

                int position = 1;

                for (LeaderBoardLine result : assettoSession.getSessionResult().getLeaderBoardLines()) {
                    if (isDriver(result.getCurrentDriver()) && result.getTiming().getBestLap() != 999999999) {
                        Driver driver = DriverTransformer.toDriver(result, position, result.getTiming().getLapCount(), formatSeconds(result.getTiming().getBestLap()), driverTeams);
                        session.addDriver(driver);

                        position++;
                    }
                }
            } catch (JsonSyntaxException | JsonIOException | FileNotFoundException | UnsupportedEncodingException e) {
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
                LeaderBoardLine driverBestLap = null;
                long totalLaps = 0;
                int leaderFinishTime = 0;
                String raceTimeFormatted;
                session = new br.com.rsousa.pojo.Session(SessionType.RACE);

                for (LeaderBoardLine result : assettoSession.getSessionResult().getLeaderBoardLines()) {
                    if (isDriver(result.getCurrentDriver()) && result.getTiming().getBestLap() != 999999999) {
                        Integer driverLaps = result.getTiming().getLapCount();
                        if (position == 1) {
                            driverBestLap = result;
                            raceTimeFormatted = driverLaps + " voltas";
                            totalLaps = driverLaps;
                            leaderFinishTime = result.getTiming().getTotalTime();
                        } else {
                            if (driverBestLap.getTiming().getBestLap().compareTo(result.getTiming().getBestLap()) > 0) {
                                driverBestLap = result;
                            }

                            if (totalLaps == driverLaps) {
                                int secondsBehindTheLeader = result.getTiming().getTotalTime() - leaderFinishTime;

                                raceTimeFormatted = formatSeconds(secondsBehindTheLeader);
                            } else {
                                long lapsBehindTheLeader = totalLaps - driverLaps;

                                raceTimeFormatted = "+ " + lapsBehindTheLeader + " laps";
                            }
                        }

                        Driver driver = DriverTransformer.toDriver(result, position, driverLaps.intValue(), raceTimeFormatted, driverTeams);
                        driver.setRaceTime(formatSeconds(result.getTiming().getTotalTime()));

                        if (totalLaps/2 > driverLaps) {
                            driver.setStatus(DriverStatus.DID_NOT_FINISH);
                        }

                        session.addDriver(driver);

                        position++;
                    }
                }

                if (driverBestLap != null) {
                    String driverBestLapName = driverBestLap.getCurrentDriver().getFirstName() + " " + driverBestLap.getCurrentDriver().getLastName();

                    session.drivers().stream()
                            .filter(d -> d.getName().equals(driverBestLapName))
                            .findFirst()
                            .ifPresent(s -> s.setBestLap(true));

                }
            } catch (JsonSyntaxException | JsonIOException | FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return session;
    }

    private static Session createSession(File file) throws FileNotFoundException, UnsupportedEncodingException {
        Gson gson = new Gson();

        String charset = "UTF-8";
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader (new FileInputStream(file), charset));

            return gson.fromJson(in, Session.class);
        } catch (UnsupportedEncodingException e) {
            throw e;
        }
    }

    private static String formatSeconds(int time) {
        String bestLapTime = String.valueOf(time);

        int totalSeconds = 0;

        if ("2147483647".equals(bestLapTime)) {
            return "sem tempo";
        }

        if (bestLapTime.length() > 3) {
            totalSeconds = Integer.parseInt(bestLapTime.substring(0, bestLapTime.length() - 3));
        }

        String milliseconds = bestLapTime.substring(bestLapTime.length() - 3);

        return LocalTime.MIN.plusSeconds(totalSeconds).format(MINUTE_FORMATTER) + "."
                + milliseconds;
    }

    private static boolean isDriver(CurrentDriver driver) {
        String name = driver.getFirstName() + " " + driver.getLastName();

        return !name.isEmpty() && !name.contains("Diretor") && !name.contains("Comentarista") && !name.contains("Narrador");
    }
}

package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.DriverStatus;
import br.com.rsousa.pojo.Event;
import br.com.rsousa.pojo.SessionType;
import br.com.rsousa.pojo.acc.CurrentDriver;
import br.com.rsousa.pojo.acc.LeaderBoardLine;
import br.com.rsousa.pojo.acc.Session;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AssettoCorsaCompetizioneTransformer implements SimulatorTransformer {
    private final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern("m:ss");

    @Override
    public Event processEvent(File file, List<Driver> driverTeams, boolean hardDnf, boolean isSelective) {
        return null;
    }

    public br.com.rsousa.pojo.Session processQualify(File file, List<Driver> driverTeams, boolean hardDnf, boolean isSelective) throws IOException {
        br.com.rsousa.pojo.Session session = null;

        if (file != null) {
            Session assettoSession = createSession(file);

            if ("R".equals(assettoSession.getSessionType()) && !isSelective) {
                return processRace(file, driverTeams, hardDnf);
            }

            session = new br.com.rsousa.pojo.Session(SessionType.QUALIFY, isSelective);

            int position = 1;

            for (LeaderBoardLine result : assettoSession.getSessionResult().getLeaderBoardLines()) {
                if (isDriver(result.getCurrentDriver()) && result.getTiming().getBestLap() != 999999999) {
                    Driver driver = DriverTransformer.toDriver(result, position, result.getTiming().getLapCount(), formatSeconds(result.getTiming().getBestLap()), driverTeams);
                    session.addDriver(driver);

                    position++;
                }
            }
        }

        return session;
    }

    public br.com.rsousa.pojo.Session processRace(File file, List<Driver> driverTeams, boolean hardDnf) {
        br.com.rsousa.pojo.Session session = null;

        if (file != null) {
            try {
                Session assettoSession = createSession(file);

                int position = 1;
                long totalLaps = 0;
                Long leaderFinishTime = 0L;
                String raceTimeFormatted;
                session = new br.com.rsousa.pojo.Session(SessionType.RACE, false);

                for (LeaderBoardLine result : assettoSession.getSessionResult().getLeaderBoardLines()) {
                    if (isDriver(result.getCurrentDriver()) && result.getTiming().getBestLap() != 999999999) {
                        Integer driverLaps = result.getTiming().getLapCount();
                        if (position == 1) {
                            raceTimeFormatted = driverLaps + " voltas";
                            totalLaps = driverLaps;
                            leaderFinishTime = result.getTiming().getTotalTime();
                        } else {
                            if (totalLaps == driverLaps) {
                                Long secondsBehindTheLeader = result.getTiming().getTotalTime() - leaderFinishTime;

                                raceTimeFormatted = formatSeconds(secondsBehindTheLeader);
                            } else {
                                long lapsBehindTheLeader = totalLaps - driverLaps;

                                raceTimeFormatted = "+ " + lapsBehindTheLeader + " laps";
                            }
                        }

                        Driver driver = DriverTransformer.toDriver(result, position, driverLaps, raceTimeFormatted, driverTeams);
                        driver.setRaceTime(formatSeconds(result.getTiming().getTotalTime()));

                        if (totalLaps/2 > driverLaps) {
                            driver.setStatus(DriverStatus.DID_NOT_FINISH);
                        }

                        session.addDriver(driver);

                        position++;
                    }
                }
            } catch (JsonSyntaxException | JsonIOException | IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return session;
    }

    @Override
    public Boolean processEvent() {
        return false;
    }

    private Session createSession(File file) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader (Files.newInputStream(file.toPath()), StandardCharsets.UTF_8));

        Gson gson = new Gson();

        return gson.fromJson(in, Session.class);
    }

    private String formatSeconds(Long time) {
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

    private boolean isDriver(CurrentDriver driver) {
        String name = driver.getFirstName() + " " + driver.getLastName();

        return isDriver(name);
    }
}

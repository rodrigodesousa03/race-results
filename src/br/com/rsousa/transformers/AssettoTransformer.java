package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.DriverStatus;
import br.com.rsousa.pojo.Event;
import br.com.rsousa.pojo.SessionType;
import br.com.rsousa.pojo.assetto.Lap;
import br.com.rsousa.pojo.assetto.Result;
import br.com.rsousa.pojo.assetto.Session;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AssettoTransformer implements SimulatorTransformer {
    private static final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern("m:ss");

    @Override
    public Event processEvent(File file, List<Driver> driverTeams, boolean hardDnf, boolean isSelective) throws FileNotFoundException, UnsupportedEncodingException {
        return null;
    }

    public br.com.rsousa.pojo.Session processQualify(File file, List<Driver> driverTeams, boolean hardDnf, boolean isSelective) throws FileNotFoundException {
        br.com.rsousa.pojo.Session session = null;

        if (file != null) {
            Session assettoSession = createSession(file);

            if ("RACE".equals(assettoSession.getType()) && !isSelective) {
                return processRace(file, driverTeams, hardDnf);
            }

            session = new br.com.rsousa.pojo.Session(SessionType.QUALIFY, isSelective);

            int position = 1;

            for (Result result : assettoSession.getResult()) {
                if (isDriver(result.getDriverName()) && result.getBestLap() != 999999999) {
                    Driver driver = DriverTransformer.toDriver(result, position, lapsFor(result.getDriverName(), assettoSession.getLaps()).intValue(), formatSeconds(result.getBestLap()), driverTeams);
                    session.addDriver(driver);

                    position++;
                }
            }
        }

        return session;
    }

    public br.com.rsousa.pojo.Session processRace(File file, List<Driver> driverTeams, boolean hardDnf) throws FileNotFoundException {
        br.com.rsousa.pojo.Session session = null;

        if (file != null) {
            Session assettoSession = createSession(file);

            int position = 1;
            long totalLaps = 0;
            List<Lap> laps = assettoSession.getLaps();
            Long leaderFinishTime = 0L;
            String raceTimeFormatted;
            session = new br.com.rsousa.pojo.Session(SessionType.RACE, false);

            for (Result result : assettoSession.getResult()) {
                if (isDriver(result.getDriverName()) && result.getBestLap() != 999999999) {
                    Long driverLaps = lapsFor(result.getDriverName(), laps);
                    if (position == 1) {
                        raceTimeFormatted = driverLaps + " voltas";
                        totalLaps = driverLaps;
                        leaderFinishTime = result.getTotalTime();
                    } else {
                        if (totalLaps == driverLaps) {
                            Long secondsBehindTheLeader = result.getTotalTime() - leaderFinishTime;

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

        }

        return session;
    }

    private Long lapsFor(String driverName, List<Lap> laps) {
        return laps.stream().filter(l -> l.getDriverName().equals(driverName)).count();
    }

    private Session createSession(File file) throws FileNotFoundException {
        Gson gson = new Gson();

        return gson.fromJson(new FileReader(file), Session.class);
    }

    private String formatSeconds(Long time) {
        String bestLapTime = String.valueOf(time);

        int totalSeconds = 0;

        if (bestLapTime.length() > 3) {
            totalSeconds = Integer.parseInt(bestLapTime.substring(0, bestLapTime.length() - 3));
        }

        String milliseconds;

        if (bestLapTime.length() >= 3) {
            milliseconds = bestLapTime.substring(bestLapTime.length() - 3);
        } else if (bestLapTime.length() == 2) {
            milliseconds = "0" + bestLapTime;
        } else {
            milliseconds = "00" + bestLapTime;
        }

        return LocalTime.MIN.plusSeconds(totalSeconds).format(MINUTE_FORMATTER) + "."
                + milliseconds;
    }
}

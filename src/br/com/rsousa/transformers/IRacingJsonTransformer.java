package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.DriverStatus;
import br.com.rsousa.pojo.Event;
import br.com.rsousa.pojo.SessionType;
import br.com.rsousa.pojo.iracing.json.Result;
import br.com.rsousa.pojo.iracing.json.Session;
import br.com.rsousa.pojo.iracing.json.SessionResult;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class IRacingJsonTransformer implements SimulatorTransformer {
    private final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern("m:ss");

    @Override
    public Event processEvent(File file, List<Driver> driverTeams, boolean hardDnf, boolean isSelective) throws FileNotFoundException, UnsupportedEncodingException {
        Event event = new Event();

        if (file != null) {
            br.com.rsousa.pojo.Session qualifySession = processQualify(file, driverTeams, hardDnf, isSelective);
            event.setQualifySession(qualifySession);

            List<br.com.rsousa.pojo.Session> raceSessions = processRaces(file, driverTeams, hardDnf);
            event.setRaceSessions(raceSessions);
        }

        return event;
    }

    public br.com.rsousa.pojo.Session processQualify(File file, List<Driver> driverTeams, boolean hardDnf, boolean isSelective) throws FileNotFoundException, UnsupportedEncodingException {
        br.com.rsousa.pojo.Session session = null;

        if (file != null) {
            Session iracingSession = createSession(file);

            SessionResult sessionResult = iracingSession.getSessionResults()
                    .stream()
                    .filter(sr -> sr.getSimsessionTypeName().contains("Qualifying"))
                    .findFirst()
                    .orElse(null);

            if (sessionResult == null) {
                return null;
            }

            session = new br.com.rsousa.pojo.Session(SessionType.QUALIFY, isSelective);

            for (Result result : sessionResult.getResults()) {
                if (isDriver(result) && result.getBestLapTime() != -1) {
                    Driver driver = DriverTransformer.toDriver(result, result.getLapsComplete(), formatMilliseconds(result.getBestLapTime()), driverTeams);

                    session.addDriver(driver);
                }
            }
        }

        return session;
    }

    public br.com.rsousa.pojo.Session processRace(File file, List<Driver> driverTeams, boolean hardDnf) {
        return null;
    }

    public List<br.com.rsousa.pojo.Session> processRaces(File file, List<Driver> driverTeams, boolean hardDnf) {
        List<br.com.rsousa.pojo.Session> sessions = new ArrayList<>();

        if (file != null) {
            try {
                Session iracingSession = createSession(file);

                List<SessionResult> raceResults = iracingSession.getSessionResults()
                        .stream()
                        .filter(sr -> sr.getSimsessionTypeName().contains("Race"))
                        .collect(java.util.stream.Collectors.toList());

                if (raceResults.isEmpty()) {
                    return null;
                }

                for (SessionResult sessionResult : raceResults) {
                    long leaderFinishTime = 0L;
                    Long totalLaps = 0L;
                    String raceTimeFormatted;
                    br.com.rsousa.pojo.Session session = new br.com.rsousa.pojo.Session(SessionType.RACE, false);

                    for (Result result : sessionResult.getResults()) {
                        long driverTotalTime = result.getAverageLap() * result.getLapsComplete();

                        if (isDriver(result)) {
                            Long driverLaps = result.getLapsComplete();
                            if (result.getFinishPositionInClass() == 0) {
                                raceTimeFormatted = driverLaps + " voltas";
                                totalLaps = driverLaps;
                                leaderFinishTime = driverTotalTime;
                            } else {
                                if (totalLaps.equals(driverLaps)) {
                                    Long secondsBehindTheLeader = driverTotalTime - leaderFinishTime;

                                    raceTimeFormatted = formatMilliseconds(secondsBehindTheLeader);
                                } else {
                                    long lapsBehindTheLeader = totalLaps - driverLaps;

                                    raceTimeFormatted = "+" + lapsBehindTheLeader + " laps";
                                }
                            }

                            Driver driver = DriverTransformer.toDriver(result, driverLaps, raceTimeFormatted, driverTeams);
                            driver.setRaceTime(formatMilliseconds(driverTotalTime));

                            if (totalLaps / 2 > driverLaps) {
                                driver.setStatus(DriverStatus.DID_NOT_FINISH);
                            }

                            session.addDriver(driver);
                        }
                    }

                    sessions.add(session);
                }
            } catch (JsonSyntaxException | JsonIOException | IOException e) {
                e.printStackTrace();
            }
        }

        return sessions;
    }

    private br.com.rsousa.pojo.iracing.json.Session createSession(File file) throws FileNotFoundException, UnsupportedEncodingException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

        Gson gson = new Gson();

        return gson.fromJson(in, Session.class);
    }

    private String formatSeconds(Long time) {
        String formattedTime = String.valueOf(time);

        int totalSeconds = 0;

        if ("-1".equals(formattedTime)) {
            return "sem tempo";
        }

        if (formattedTime.length() > 3) {
            totalSeconds = Integer.parseInt(formattedTime.substring(0, formattedTime.length() - 3));
        }

        String milliseconds = formattedTime.substring(formattedTime.length());

        return LocalTime.MIN.plusSeconds(totalSeconds).format(MINUTE_FORMATTER) + "."
                + milliseconds;
    }

    private static final DateTimeFormatter MINUTE_FORMATTER_2 = DateTimeFormatter.ofPattern("m:ss.SSS");

    public static String formatMilliseconds(long milliseconds) {
        milliseconds = milliseconds/10;

        int totalSeconds = (int) (milliseconds / 1000);
        int millis = (int) (milliseconds % 1000);

        LocalTime time = LocalTime.MIN.plusSeconds(totalSeconds).plusNanos(millis * 1_000_000);

        return time.format(MINUTE_FORMATTER_2);
    }

    private boolean isDriver(Result result) {
        String name = result.getDisplayName();

        return isDriver(name);
    }

    @Override
    public Boolean processEvent() {
        return true;
    }
}

package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.DriverStatus;
import br.com.rsousa.pojo.Event;
import br.com.rsousa.pojo.SessionType;
import br.com.rsousa.pojo.ams2.History;
import br.com.rsousa.pojo.ams2.Result;
import br.com.rsousa.pojo.ams2.Server;
import br.com.rsousa.pojo.ams2.Stage;
import com.google.gson.*;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Automobilista2Transformer implements SimulatorTransformer {
    public static final String UTF_8 = "UTF-8";
    private final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern("m:ss");

    @Override
    public Event processEvent(File file, List<Driver> driverTeams, boolean hardDnf, boolean isSelective) throws FileNotFoundException, UnsupportedEncodingException {
        Event event = new Event();

        if (file != null) {
            br.com.rsousa.pojo.Session qualifySession = processQualify(file, driverTeams, hardDnf, isSelective);
            event.setQualifySession(qualifySession);

            br.com.rsousa.pojo.Session raceSession = processRace(file, driverTeams, hardDnf);
            event.setRaceSession(raceSession);
        }

        return event;
    }

    public br.com.rsousa.pojo.Session processQualify(File file, List<Driver> driverTeams, boolean hardDnf, boolean isSelective) throws FileNotFoundException, UnsupportedEncodingException {
        br.com.rsousa.pojo.Session session = null;

        if (file != null) {
            Server ams2Session = createServer(file);

            Stage stage = getStage(ams2Session, "qualify");

            if (stage == null) {
                return processRace(file, driverTeams, hardDnf);
            }

            session = new br.com.rsousa.pojo.Session(SessionType.QUALIFY, isSelective);

            int position = 1;

            for (Result result : stage.getResults()) {
                if (isDriver(result.getName()) && result.getAttributes().getFastestLapTime() > 0) {
                    Driver driver = DriverTransformer.toDriver(result, position, formatMilliseconds(result.getAttributes().getFastestLapTime()), driverTeams);
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
                Server ams2Session = createServer(file);

                Stage stage = getStage(ams2Session, "race");

                if (stage == null) {
                    return null;
                }

                int position = 1;
                long totalLaps = 0;
                Integer leaderFinishTime = 0;
                String raceTimeFormatted;
                session = new br.com.rsousa.pojo.Session(SessionType.RACE, false);

                for (Result result : stage.getResults()) {
                    if (isDriver(result.getName())) {
                        Integer driverLaps = result.getAttributes().getLap();
                        if (position == 1) {
                            raceTimeFormatted = driverLaps + " voltas";
                            totalLaps = driverLaps;
                            leaderFinishTime = result.getAttributes().getTotalTime();
                        } else {
                            if (totalLaps == driverLaps) {
                                Integer secondsBehindTheLeader = result.getAttributes().getTotalTime() - leaderFinishTime;

                                raceTimeFormatted = formatMilliseconds(secondsBehindTheLeader);
                            } else {
                                long lapsBehindTheLeader = totalLaps - driverLaps;

                                raceTimeFormatted = "+ " + lapsBehindTheLeader + " laps";
                            }
                        }

                        Driver driver = DriverTransformer.toDriver(result, position, raceTimeFormatted, driverTeams);
                        driver.setRaceTime(formatMilliseconds(result.getAttributes().getTotalTime()));

                        if (totalLaps / 2 > driverLaps) {
                            driver.setStatus(DriverStatus.DID_NOT_FINISH);
                        }

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

    private Server createServer(File file) throws FileNotFoundException, UnsupportedEncodingException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), UTF_8));

        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(Stage.class, new Automobilista2StageDeserializer());
        gsonBuilder.setLenient();

        Gson gson = gsonBuilder.create();

        return gson.fromJson(in, Server.class);
    }

    private static Stage getStage(Server ams2Session, String sessionName) {
        Stage stage = null;

        for (History history : ams2Session.getStats().getHistory()) {
            for (String stageStr : history.getStages().keySet()) {
                if (stageStr.contains(sessionName)) {
                    stage = history.getStages().get(stageStr);
                }
            }
        }

        return stage;
    }

    private String formatMilliseconds(Integer time) {
        String bestLapTime = String.valueOf(time);

        int totalSeconds = 0;


        if (bestLapTime.length() > 3) {
            totalSeconds = Integer.parseInt(bestLapTime.substring(0, bestLapTime.length() - 3));
        }

        String milliseconds = bestLapTime.substring(bestLapTime.length() - 3);

        return LocalTime.MIN.plusSeconds(totalSeconds).format(MINUTE_FORMATTER) + "."
                + milliseconds;
    }
}

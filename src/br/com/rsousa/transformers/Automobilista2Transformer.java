package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.DriverStatus;
import br.com.rsousa.pojo.Event;
import br.com.rsousa.pojo.SessionType;
import br.com.rsousa.pojo.ams2.*;
import com.google.gson.*;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

                session = new br.com.rsousa.pojo.Session(SessionType.RACE, false);

                Map<Integer, List<br.com.rsousa.pojo.ams2.Event>> driverEventsMap = stage.getEvents().stream()
                        .filter(event -> "Lap".equals(event.getEventName()) || "State".equals(event.getEventName()))
                        .collect(Collectors.groupingBy(br.com.rsousa.pojo.ams2.Event::getRefid));

                Map<String, Member> membersMap = ams2Session.getStats().getHistory().stream()
                        .map(History::getMembers)
                        .flatMap(m -> m.entrySet().stream())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                for (Integer driverRefId : driverEventsMap.keySet()) {
                    String driverName = !Objects.equals(driverEventsMap.get(driverRefId).get(0).getName(), "") ? driverEventsMap.get(driverRefId).get(0).getName() : membersMap.get(String.valueOf(driverRefId)).getName();

                    if (isDriver(driverName)) {
                        int driverLaps = 0;
                        Driver driver = new Driver();
                        Long driverBestLap = null;
                        Long driverTotalTime = 0L;

                        for (br.com.rsousa.pojo.ams2.Event event : driverEventsMap.get(driverRefId)) {
                            if ("Lap".equals(event.getEventName())) {
                                driverLaps += 1;

                                driverTotalTime += event.getAttributes().getLapTime();

                                if (driverBestLap == null || event.getAttributes().getLapTime().compareTo(Math.toIntExact(driverBestLap)) < 0) {
                                    driverBestLap = Long.valueOf(event.getAttributes().getLapTime());
                                }
                            }

                            driver = DriverTransformer.toDriver(event, driverName, "", driverTeams);
                            driver.setRaceTimeMilliseconds(driverTotalTime);
                            driver.setLaps(driverLaps);

                            if ("State".equals(event.getEventName())) {
                                switch (event.getAttributes().getNewState()) {
                                    case "Finished":
                                        driver.setStatus(DriverStatus.FINISHED);
                                        break;
                                    case "Disqualified":
                                        driver.setStatus(DriverStatus.DISQUALIFIED);
                                        break;
                                    case "Retired":
                                        driver.setStatus(DriverStatus.DID_NOT_FINISH);
                                        break;
                                }
                            }
                        }

                        driver.setBestLapMilliseconds(driverBestLap);
                        session.addDriver(driver);
                    }
                }

                for (Driver driver : session.drivers()) {
                    for (Result result : stage.getResults()) {
                        if (driver.getName().equals(result.getName())) {
                            driver.setRaceTimeMilliseconds(result.getAttributes().getTotalTime().longValue());
                        }
                    }
                }

                session.sortDriversByLapsAndTotalTime();

                Driver leader = session.drivers().get(0);

                String raceTimeFormatted = "";

                for (Driver driver : session.drivers()) {
                    if (driver.getPosition() == 1) {
                        raceTimeFormatted = driver.getLaps() + " voltas";
                    } else {
                        if (Objects.equals(leader.getLaps(), driver.getLaps())) {
                            Integer secondsBehindTheLeader = driver.getRaceTimeMilliseconds().intValue() - leader.getRaceTimeMilliseconds().intValue();

                            raceTimeFormatted = formatMilliseconds(secondsBehindTheLeader);
                        } else {
                            long lapsBehindTheLeader = leader.getLaps() - driver.getLaps();

                            raceTimeFormatted = "+ " + lapsBehindTheLeader + " laps";
                        }
                    }

                    driver.setRaceTimeFormatted(raceTimeFormatted);
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
        if (time == null || time == 0) {
            return "0";
        }

        String bestLapTime = String.valueOf(time);

        int totalSeconds = 0;

        if (bestLapTime.length() > 3) {
            totalSeconds = Integer.parseInt(bestLapTime.substring(0, bestLapTime.length() - 3));
        }

        String milliseconds = null;

        try {
            milliseconds = bestLapTime.substring(bestLapTime.length() - 3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return LocalTime.MIN.plusSeconds(totalSeconds).format(MINUTE_FORMATTER) + "."
                + milliseconds;
    }
}

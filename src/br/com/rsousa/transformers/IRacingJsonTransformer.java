package br.com.rsousa.transformers;

import br.com.rsousa.iracing.IRacingApiClient;
import br.com.rsousa.iracing.IRacingCredentials;
import br.com.rsousa.iracing.LapData;
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
import java.nio.file.Files;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class IRacingJsonTransformer implements SimulatorTransformer {

    private Consumer<String> progressCallback = msg -> {};
    private Consumer<String> detailCallback   = msg -> {};
    private IRacingApiClient sharedApiClient  = null;

    public void setProgressCallback(Consumer<String> callback) {
        this.progressCallback = callback != null ? callback : msg -> {};
    }

    public void setDetailCallback(Consumer<String> callback) {
        this.detailCallback = callback != null ? callback : msg -> {};
    }

    public void setApiClient(IRacingApiClient client) {
        this.sharedApiClient = client;
    }

    @Override
    public Event processEvent(File file, List<Driver> driverTeams, boolean hardDnf, boolean isSelective) throws IOException {
        Event event = new Event();

        if (file != null) {
            br.com.rsousa.pojo.Session qualifySession = processQualify(file, driverTeams, hardDnf, isSelective);
            event.setQualifySession(qualifySession);

            List<br.com.rsousa.pojo.Session> raceSessions = processRaces(file, driverTeams, hardDnf);
            event.setRaceSessions(raceSessions);
        }

        return event;
    }

    public br.com.rsousa.pojo.Session processQualify(File file, List<Driver> driverTeams, boolean hardDnf, boolean isSelective) throws IOException {
        if (file == null) return null;

        Session iracingSession = createSession(file);

        // Em modo seletiva, a seletiva é rodada como Lone Practice (não Qualifying).
        String typeFilter = isSelective ? "Practice" : "Qualifying";

        List<SessionResult> sessionResults = iracingSession.getData().getSessionResults()
                .stream()
                .filter(sr -> sr.getSimsessionTypeName().contains(typeFilter))
                .collect(Collectors.toList());

        if (sessionResults.isEmpty()) return null;

        br.com.rsousa.pojo.Session session = new br.com.rsousa.pojo.Session(SessionType.QUALIFY, isSelective);

        // Tenta usar a API para validar off-tracks apenas no modo seletiva
        IRacingApiClient apiClient = null;
        int subsessionId = 0;
        if (isSelective) {
            if (sharedApiClient != null) {
                apiClient = sharedApiClient;
            } else {
                IRacingCredentials credentials = IRacingCredentials.load();
                if (credentials.isConfigured()) {
                    apiClient = new IRacingApiClient(credentials);
                }
            }
            if (apiClient != null) {
                subsessionId = iracingSession.getData().getSubsessionId() != null
                        ? iracingSession.getData().getSubsessionId() : 0;
            }
        }

        if (apiClient != null && subsessionId > 0) {
            buildSessionWithApi(sessionResults, session, apiClient, subsessionId, driverTeams);
        } else {
            for (SessionResult sr : sessionResults) {
                for (Result r : sr.getResults()) {
                    if (!isDriver(r) || r.getBestLapTime() == null || r.getBestLapTime() == -1L) continue;
                    session.addDriver(DriverTransformer.toDriver(
                            r, r.getLapsComplete(), formatMilliseconds(r.getBestLapTime()), driverTeams));
                }
            }
        }

        return session;
    }

    /**
     * Consulta a API do iRacing em paralelo (8 threads) para validar off-tracks.
     * Cada piloto é processado de forma independente; os resultados são coletados
     * em um ConcurrentHashMap e depois adicionados à sessão na ordem original.
     */
    private void buildSessionWithApi(
            List<SessionResult> sessionResults,
            br.com.rsousa.pojo.Session session,
            IRacingApiClient apiClient,
            int subsessionId,
            List<Driver> driverTeams) {

        record DriverTask(Result result, int simsessionNumber) {}

        // Deduplica por custId — o mesmo piloto pode aparecer em múltiplas sessões de Practice
        List<DriverTask> tasks = new ArrayList<>();
        java.util.Set<Integer> seenCustIds = new java.util.HashSet<>();
        for (SessionResult sr : sessionResults) {
            for (Result r : sr.getResults()) {
                if (isDriver(r) && r.getBestLapTime() != null
                        && r.getBestLapTime() != -1L && r.getCustId() != null
                        && seenCustIds.add(r.getCustId())) {
                    tasks.add(new DriverTask(r, sr.getSimsessionNumber()));
                }
            }
        }

        int total = tasks.size();
        AtomicInteger done = new AtomicInteger(0);
        // custId → Optional.of(bestLapTime válido) | Optional.empty() = excluído
        ConcurrentHashMap<Integer, Optional<Long>> resolved = new ConcurrentHashMap<>();
        // Off-tracks substituídos: "Nome originalTime"
        ConcurrentLinkedQueue<String> invalidationQueue = new ConcurrentLinkedQueue<>();

        int threads = Math.max(1, Math.min(2, total));
        ExecutorService pool = Executors.newFixedThreadPool(threads);

        List<CompletableFuture<Void>> futures = tasks.stream().map(task ->
            CompletableFuture.runAsync(() -> {
                int n = done.incrementAndGet();
                progressCallback.accept("Consultando API do iRacing — "
                        + task.result().getDisplayName() + " (" + n + "/" + total + ")");
                try {
                    List<LapData> laps = apiClient.fetchLapData(
                            subsessionId, task.simsessionNumber(), task.result().getCustId());

                    int origBest = task.result().getBestLapNum() != null
                            ? task.result().getBestLapNum() : -1;
                    LapData bestClean = findBestCleanLap(laps, origBest);

                    if (bestClean != null) {
                        if (bestClean.getLapTime() != task.result().getBestLapTime()) {
                            detailCallback.accept("Off-track: " + task.result().getDisplayName()
                                    + " → volta " + bestClean.getLapNumber()
                                    + " (" + formatMilliseconds(bestClean.getLapTime()) + ")");
                            invalidationQueue.add(task.result().getDisplayName()
                                    + " " + formatMilliseconds(task.result().getBestLapTime()));
                        }
                        resolved.put(task.result().getCustId(), Optional.of(bestClean.getLapTime()));
                    } else {
                        detailCallback.accept("Sem volta limpa: "
                                + task.result().getDisplayName() + " — excluído da seletiva");
                        resolved.put(task.result().getCustId(), Optional.empty());
                    }
                } catch (Exception e) {
                    String reason = e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName();
                    detailCallback.accept("Aviso: falha ao validar "
                            + task.result().getDisplayName() + " — " + reason);
                    resolved.put(task.result().getCustId(),
                            Optional.of(task.result().getBestLapTime()));
                }
            }, pool)
        ).collect(Collectors.toList());

        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        } finally {
            pool.shutdown();
        }

        // Registra off-tracks em ordem alfabética
        List<String> sortedInvalidations = new ArrayList<>(invalidationQueue);
        Collections.sort(sortedInvalidations);
        sortedInvalidations.forEach(session::addLapInvalidation);

        // Reconstrói a sessão — um registro por custId (o mesmo piloto não entra duas vezes)
        java.util.Set<Integer> addedCustIds = new java.util.HashSet<>();
        for (SessionResult sr : sessionResults) {
            for (Result r : sr.getResults()) {
                if (!isDriver(r) || r.getCustId() == null) continue;
                if (!addedCustIds.add(r.getCustId())) continue;
                Optional<Long> bestTime = resolved.get(r.getCustId());
                if (bestTime != null && bestTime.isPresent()) {
                    long cleanTime = bestTime.get();
                    Driver driver = DriverTransformer.toDriver(
                            r, r.getLapsComplete(), formatMilliseconds(cleanTime), driverTeams);
                    driver.setBestLapMilliseconds(cleanTime / 10);
                    session.addDriver(driver);
                }
            }
        }
    }

    private LapData findBestCleanLap(List<LapData> laps, int originalBestLapNum) {
        // Verifica se a melhor volta original está limpa
        boolean originalIsClean = laps.stream()
                .filter(l -> l.getLapNumber() == originalBestLapNum)
                .findFirst()
                .map(l -> !l.isOffTrack())
                .orElse(false);

        if (originalIsClean) {
            return laps.stream()
                    .filter(l -> l.getLapNumber() == originalBestLapNum)
                    .findFirst()
                    .orElse(null);
        }

        // Melhor volta tinha off-track: busca o próximo tempo limpo
        return laps.stream()
                .filter(l -> l.hasValidTime() && !l.isOffTrack())
                .min(Comparator.comparingLong(LapData::getLapTime))
                .orElse(null);
    }

    public br.com.rsousa.pojo.Session processRace(File file, List<Driver> driverTeams, boolean hardDnf) {
        return null;
    }

    public List<br.com.rsousa.pojo.Session> processRaces(File file, List<Driver> driverTeams, boolean hardDnf) {
        List<br.com.rsousa.pojo.Session> sessions = new ArrayList<>();

        if (file != null) {
            try {
                Session iracingSession = createSession(file);

                List<SessionResult> raceResults = iracingSession.getData().getSessionResults()
                        .stream()
                        .filter(sr -> sr.getSimsessionTypeName().contains("Race"))
                        .sorted(Comparator.comparing(SessionResult::getSimsessionNumber))
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
                                    long secondsBehindTheLeader = driverTotalTime - leaderFinishTime;

                                    raceTimeFormatted = formatMilliseconds(secondsBehindTheLeader);
                                } else {
                                    long lapsBehindTheLeader = totalLaps - driverLaps;

                                    raceTimeFormatted = "+" + lapsBehindTheLeader + " laps";
                                }
                            }

                            Driver driver = DriverTransformer.toDriver(result, driverLaps, raceTimeFormatted, driverTeams);
                            driver.setRaceTime(formatMilliseconds(driverTotalTime));

                            if ((totalLaps / 2 > driverLaps) || (hardDnf && !"Running".equalsIgnoreCase(result.getReasonOut()))) {
                                driver.setStatus(DriverStatus.DID_NOT_FINISH);
                            }

                            if ("Disqualified".equalsIgnoreCase(result.getReasonOut())) {
                                driver.setStatus(DriverStatus.DISQUALIFIED);
                            }

                            session.addDriver(driver);
                        }
                    }

                    sessions.add(session);
                }
            } catch (JsonSyntaxException | JsonIOException | IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return sessions;
    }

    private br.com.rsousa.pojo.iracing.json.Session createSession(File file) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8));

        Gson gson = new Gson();

        return gson.fromJson(in, Session.class);
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

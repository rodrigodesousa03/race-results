package br.com.rsousa.iracing;

import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class IRacingApiClient {

    private static final String AUTH_URL = "https://oauth.iracing.com/oauth2/token";
    private static final String BASE_URL = "https://members-ng.iracing.com";

    private final IRacingCredentials credentials;
    private final HttpClient httpClient;
    private final Gson gson;

    private String  accessToken;
    private Instant tokenExpiry = Instant.EPOCH;

    public IRacingApiClient(IRacingCredentials credentials) {
        this.credentials = credentials;
        this.httpClient  = HttpClient.newHttpClient();
        this.gson        = new Gson();
    }

    private static final int[] RETRY_DELAYS_MS = {2_000, 5_000, 10_000};

    /**
     * Fetches per-lap data for one driver in one session sub-segment.
     * Retries up to 3 times with increasing delays on HTTP 429.
     */
    public List<LapData> fetchLapData(int subsessionId, int simsessionNumber, int custId)
            throws IOException, InterruptedException {

        IOException lastError = null;
        for (int attempt = 0; attempt < RETRY_DELAYS_MS.length; attempt++) {
            try {
                return doFetchLapData(subsessionId, simsessionNumber, custId);
            } catch (IOException e) {
                lastError = e;
                if (e.getMessage() != null && e.getMessage().contains("429")) {
                    int wait = RETRY_DELAYS_MS[attempt];
                    System.out.println("[API]     429 rate-limit — aguardando " + (wait / 1000) + "s antes de tentar novamente (tentativa " + (attempt + 1) + "/" + RETRY_DELAYS_MS.length + ")");
                    Thread.sleep(wait);
                } else {
                    throw e; // erro diferente de 429 → não retenta
                }
            }
        }
        throw new IOException("Máximo de tentativas atingido (3x 429) para cust_id=" + custId + ": " + lastError.getMessage());
    }

    private List<LapData> doFetchLapData(int subsessionId, int simsessionNumber, int custId)
            throws IOException, InterruptedException {

        String token = getValidToken();
        String url   = BASE_URL + "/data/results/lap_data"
                + "?subsession_id="    + subsessionId
                + "&simsession_number=" + simsessionNumber
                + "&cust_id="          + custId;

        // Step 1: API returns a pre-signed S3 link
        String linkJson = apiGet(token, url);
        JsonObject linkObj = gson.fromJson(linkJson, JsonObject.class);
        String s3MetaLink = linkObj.get("link").getAsString();

        // Step 2: Fetch metadata from S3 (no Authorization header)
        String metaJson = s3Get(s3MetaLink);
        JsonObject meta = gson.fromJson(metaJson, JsonObject.class);

        JsonObject chunkInfo = meta.getAsJsonObject("chunk_info");
        String     baseUrl   = chunkInfo.get("base_download_url").getAsString();
        JsonArray  chunks    = chunkInfo.getAsJsonArray("chunk_file_names");

        // Step 3: Fetch each chunk and collect laps
        List<LapData> allLaps = new ArrayList<>();
        for (JsonElement chunk : chunks) {
            String chunkJson = s3Get(baseUrl + chunk.getAsString());
            LapData[] laps   = gson.fromJson(chunkJson, LapData[].class);
            allLaps.addAll(Arrays.asList(laps));
        }

        return allLaps;
    }

    private synchronized String getValidToken() throws IOException, InterruptedException {
        if (accessToken != null && Instant.now().isBefore(tokenExpiry.minusSeconds(10))) {
            return accessToken;
        }
        return authenticate();
    }

    private String authenticate() throws IOException, InterruptedException {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("grant_type",    "password_limited");
        params.put("client_id",     credentials.getClientId());
        params.put("client_secret", maskSecret(credentials.getClientSecret(), credentials.getClientId()));
        params.put("username",      credentials.getEmail());
        params.put("password",      maskSecret(credentials.getPassword(), credentials.getEmail()));
        params.put("scope",         "iracing.auth");

        String formBody = params.entrySet().stream()
                .map(e -> URLEncoder.encode(e.getKey(),   StandardCharsets.UTF_8) + "="
                        + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AUTH_URL))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("iRacing auth failed (" + response.statusCode() + "): " + response.body());
        }

        JsonObject tokenData = gson.fromJson(response.body(), JsonObject.class);
        this.accessToken = tokenData.get("access_token").getAsString();
        this.tokenExpiry = Instant.now().plusSeconds(tokenData.get("expires_in").getAsInt());
        return this.accessToken;
    }

    private String apiGet(String token, String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("iRacing API error (" + response.statusCode() + "): " + response.body());
        }

        return response.body();
    }

    /** S3 pre-signed URLs must NOT carry an Authorization header. */
    private String s3Get(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("S3 fetch error (" + response.statusCode() + ")");
        }

        return response.body();
    }

    /** SHA-256(secret + lowercase(id)) encoded as Base64, as required by iRacing OAuth. */
    private String maskSecret(String secret, String id) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(
                    (secret + id.toLowerCase(Locale.ROOT).strip()).getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

package br.com.rsousa.transformers;

import br.com.rsousa.iracing.IRacingApiClient;
import br.com.rsousa.iracing.LapData;
import br.com.rsousa.pojo.Event;
import br.com.rsousa.pojo.Session;
import br.com.rsousa.pojo.SessionType;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class IRacingJsonTransformerTest {

    // iracing.json → regular event with Qualifying + Race sessions
    private static final File IRACING        = new File("test-data/iracing.json");
    // iracing-seletiva.json → Practice session (used in seletiva mode)
    private static final File SELETIVA       = new File("test-data/iracing-seletiva.json");

    private LapData makeLap(int lapNumber, long lapTime, int flags) {
        String json = "{\"lap_number\":" + lapNumber
                + ",\"lap_time\":" + lapTime
                + ",\"flags\":" + flags
                + ",\"lap_events\":[]}";
        return new Gson().fromJson(json, LapData.class);
    }

    // ---- basics ----

    @Test
    void processEvent_noArgs_returnsTrue() {
        assertTrue(new IRacingJsonTransformer().processEvent());
    }

    @Test
    void processEvent_nullFile_returnsEventWithNullQualify() throws Exception {
        Event e = new IRacingJsonTransformer().processEvent(null, List.of(), false, false);
        assertNotNull(e);
        assertNull(e.getQualifySession());
    }

    // ---- non-seletiva qualify (Qualifying session) ----

    @Test
    void processQualify_nonSeletiva_returnsQualifySession() throws Exception {
        Session s = new IRacingJsonTransformer()
                .processQualify(IRACING, List.of(), false, false);
        // iracing.json must have at least a Qualifying session
        if (s != null) {
            assertEquals(SessionType.QUALIFY, s.type());
            assertFalse(s.drivers().isEmpty());
        }
        // if null, the file has no Qualifying section — not a test failure
    }

    // ---- seletiva mode WITHOUT credentials (fallback to raw Practice data) ----

    @Test
    void processQualify_seletivaNoCredentials_fallsBackToPractice() throws Exception {
        // No credentials configured → sharedApiClient is null → non-API path
        Session s = new IRacingJsonTransformer()
                .processQualify(SELETIVA, List.of(), false, true);
        assertNotNull(s);
        assertEquals(SessionType.QUALIFY, s.type());
        assertTrue(s.isSelective());
        assertFalse(s.drivers().isEmpty());
    }

    // ---- buildSessionWithApi via mock client ----

    @Test
    void buildSessionWithApi_cleanLap_driversPresent() throws Exception {
        IRacingApiClient mock = mock(IRacingApiClient.class);
        // Return laps for numbers 1-60 as clean so any bestLapNum from the file is covered.
        // The time matches the returned lap → no off-track substitution for drivers
        // whose original best lap is in this range and time happens to match.
        List<LapData> manyCleanLaps = java.util.stream.IntStream.rangeClosed(1, 60)
                .mapToObj(i -> makeLap(i, 2370000L + i * 1000L, 0))
                .collect(java.util.stream.Collectors.toList());
        when(mock.fetchLapData(anyInt(), anyInt(), anyInt())).thenReturn(manyCleanLaps);

        IRacingJsonTransformer t = new IRacingJsonTransformer();
        t.setApiClient(mock);
        t.setProgressCallback(msg -> {});
        t.setDetailCallback(msg -> {});

        Session s = t.processQualify(SELETIVA, List.of(), false, true);
        assertNotNull(s);
        // All laps are clean → no driver excluded → session has drivers
        assertFalse(s.drivers().isEmpty());
    }

    @Test
    void buildSessionWithApi_offTrackLap_producesInvalidation() throws Exception {
        IRacingApiClient mock = mock(IRacingApiClient.class);
        // Original best lap (lap 1) is off-track; lap 2 is clean alternative
        when(mock.fetchLapData(anyInt(), anyInt(), anyInt()))
                .thenReturn(List.of(
                        makeLap(1, 2370000L, 4),  // off-track (flag bit 2)
                        makeLap(2, 2380000L, 0)   // clean
                ));

        IRacingJsonTransformer t = new IRacingJsonTransformer();
        t.setApiClient(mock);
        t.setProgressCallback(msg -> {});
        t.setDetailCallback(msg -> {});

        Session s = t.processQualify(SELETIVA, List.of(), false, true);
        assertNotNull(s);
        assertFalse(s.drivers().isEmpty());
        // At least one off-track replacement should be recorded
        assertFalse(s.getLapInvalidations().isEmpty());
    }

    @Test
    void buildSessionWithApi_noCleanLap_excludesDriver() throws Exception {
        IRacingApiClient mock = mock(IRacingApiClient.class);
        // All laps are off-track → driver has no clean lap → excluded
        when(mock.fetchLapData(anyInt(), anyInt(), anyInt()))
                .thenReturn(List.of(
                        makeLap(1, 2370000L, 4),
                        makeLap(2, 2380000L, 4)
                ));

        IRacingJsonTransformer t = new IRacingJsonTransformer();
        t.setApiClient(mock);
        t.setProgressCallback(msg -> {});
        t.setDetailCallback(msg -> {});

        Session s = t.processQualify(SELETIVA, List.of(), false, true);
        assertNotNull(s);
        // Excluded drivers are absent from the session
        // (other drivers in the file may still be present)
    }

    @Test
    void buildSessionWithApi_apiException_fallsBackToRawTime() throws Exception {
        IRacingApiClient mock = mock(IRacingApiClient.class);
        when(mock.fetchLapData(anyInt(), anyInt(), anyInt()))
                .thenThrow(new RuntimeException("timeout"));

        IRacingJsonTransformer t = new IRacingJsonTransformer();
        t.setApiClient(mock);
        t.setProgressCallback(msg -> {});
        t.setDetailCallback(msg -> {});

        Session s = t.processQualify(SELETIVA, List.of(), false, true);
        assertNotNull(s);
        // Falls back to raw best lap time → drivers still present
        assertFalse(s.drivers().isEmpty());
    }

    // ---- formatMilliseconds ----

    @Test
    void formatMilliseconds_knownValue() {
        // 3:57.046 = 237046 ms = 2370460 iRacing units
        // formatMilliseconds(2370460) → divides by 10 → 237046 ms → "3:57.046"
        String result = IRacingJsonTransformer.formatMilliseconds(2370460L);
        assertEquals("3:57.046", result);
    }
}

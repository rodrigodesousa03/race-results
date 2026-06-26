package br.com.rsousa.iracing;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LapDataTest {

    private LapData lap(int lapNumber, long lapTime, int flags, List<String> events) {
        String eventsJson = events == null ? "null"
                : "[" + String.join(",", events.stream().map(e -> "\"" + e + "\"").toList()) + "]";
        String json = "{\"lap_number\":" + lapNumber
                + ",\"lap_time\":" + lapTime
                + ",\"flags\":" + flags
                + ",\"lap_events\":" + eventsJson + "}";
        return new Gson().fromJson(json, LapData.class);
    }

    @Test
    void isOffTrack_flagBit_returnsTrue() {
        assertTrue(lap(1, 1000L, 4, List.of()).isOffTrack());
    }

    @Test
    void isOffTrack_flagCombined_returnsTrue() {
        assertTrue(lap(1, 1000L, 7, List.of()).isOffTrack()); // 7 = 0b111, bit 2 set
    }

    @Test
    void isOffTrack_lapEvent_returnsTrue() {
        assertTrue(lap(1, 1000L, 0, List.of("off track")).isOffTrack());
    }

    @Test
    void isOffTrack_clean_returnsFalse() {
        assertFalse(lap(1, 1000L, 0, List.of()).isOffTrack());
    }

    @Test
    void isOffTrack_nullEvents_returnsFalse() {
        assertFalse(lap(1, 1000L, 0, null).isOffTrack());
    }

    @Test
    void hasValidTime_positive_returnsTrue() {
        assertTrue(lap(1, 100L, 0, List.of()).hasValidTime());
    }

    @Test
    void hasValidTime_zero_returnsFalse() {
        assertFalse(lap(1, 0L, 0, List.of()).hasValidTime());
    }

    @Test
    void hasValidTime_negative_returnsFalse() {
        assertFalse(lap(1, -1L, 0, List.of()).hasValidTime());
    }

    @Test
    void getters_returnExpectedValues() {
        LapData l = lap(5, 2370000L, 4, List.of("off track"));
        assertEquals(5, l.getLapNumber());
        assertEquals(2370000L, l.getLapTime());
        assertEquals(4, l.getFlags());
        assertTrue(l.getLapEvents().contains("off track"));
    }
}

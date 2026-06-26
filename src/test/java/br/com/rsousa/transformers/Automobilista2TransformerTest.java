package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Event;
import br.com.rsousa.pojo.Session;
import br.com.rsousa.pojo.SessionType;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Automobilista2TransformerTest {

    private static final File AMS2 = new File("test-data/ams2.json");

    private final Automobilista2Transformer t = new Automobilista2Transformer();

    @Test
    void processEvent_noArgs_returnsTrue() {
        assertTrue(t.processEvent());
    }

    // ---- processEvent(file) ----

    @Test
    void processEvent_nullFile_returnsEventWithNullSessions() throws Exception {
        Event e = t.processEvent(null, List.of(), false, false);
        assertNotNull(e);
        assertNull(e.getQualifySession());
        assertTrue(e.getRaceSessions().isEmpty());
    }

    @Test
    void processEvent_ams2File_returnsQualifyAndRace() throws Exception {
        Event e = t.processEvent(AMS2, List.of(), false, false);
        assertNotNull(e);
        assertNotNull(e.getQualifySession());
        assertFalse(e.getRaceSessions().isEmpty());
    }

    // ---- processQualify ----

    @Test
    void processQualify_nullFile_returnsNull() throws Exception {
        assertNull(t.processQualify(null, List.of(), false, false));
    }

    @Test
    void processQualify_ams2File_returnsDrivers() throws Exception {
        Session s = t.processQualify(AMS2, List.of(), false, false);
        assertNotNull(s);
        assertEquals(SessionType.QUALIFY, s.type());
        assertFalse(s.drivers().isEmpty());
        assertEquals(1, s.drivers().get(0).getPosition());
    }

    @Test
    void processQualify_isSelective_setsFlag() throws Exception {
        Session s = t.processQualify(AMS2, List.of(), false, true);
        assertTrue(s.isSelective());
    }

    // ---- processRace ----

    @Test
    void processRace_nullFile_returnsNull() throws Exception {
        assertNull(t.processRace(null, List.of(), false));
    }

    @Test
    void processRace_ams2File_returnsDrivers() throws Exception {
        Session s = t.processRace(AMS2, List.of(), false);
        assertNotNull(s);
        assertEquals(SessionType.RACE, s.type());
        assertFalse(s.drivers().isEmpty());
        assertEquals(1, s.drivers().get(0).getPosition());
    }
}

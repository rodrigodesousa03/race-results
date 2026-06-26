package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Session;
import br.com.rsousa.pojo.SessionType;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssettoTransformerTest {

    private static final File QUALIFY = new File("test-data/ac_qualify.json");
    private static final File RACE    = new File("test-data/ac_race.json");

    private final AssettoTransformer t = new AssettoTransformer();

    @Test
    void processEvent_noArgs_returnsFalse() {
        assertFalse(t.processEvent());
    }

    @Test
    void processEvent_file_returnsNull() throws Exception {
        assertNull(t.processEvent(QUALIFY, List.of(), false, false));
    }

    // ---- processQualify ----

    @Test
    void processQualify_nullFile_returnsNull() throws Exception {
        assertNull(t.processQualify(null, List.of(), false, false));
    }

    @Test
    void processQualify_qualifyFile_returnsDrivers() throws Exception {
        Session s = t.processQualify(QUALIFY, List.of(), false, false);
        assertNotNull(s);
        assertEquals(SessionType.QUALIFY, s.type());
        assertFalse(s.drivers().isEmpty());
        assertEquals(1, s.drivers().get(0).getPosition());
    }

    @Test
    void processQualify_isSelective_setsFlag() throws Exception {
        Session s = t.processQualify(QUALIFY, List.of(), false, true);
        assertTrue(s.isSelective());
    }

    @Test
    void processQualify_raceFile_redirectsToRace() throws Exception {
        // ac_race.json has type "RACE" → redirected to processRace
        Session s = t.processQualify(RACE, List.of(), false, false);
        assertNotNull(s);
        assertEquals(SessionType.RACE, s.type());
    }

    // ---- processRace ----

    @Test
    void processRace_nullFile_returnsNull() throws Exception {
        assertNull(t.processRace(null, List.of(), false));
    }

    @Test
    void processRace_raceFile_returnsDrivers() throws Exception {
        Session s = t.processRace(RACE, List.of(), false);
        assertNotNull(s);
        assertEquals(SessionType.RACE, s.type());
        assertFalse(s.drivers().isEmpty());
        assertEquals(1, s.drivers().get(0).getPosition());
    }

    @Test
    void processRace_hardDnf_stillReturnsSession() throws Exception {
        Session s = t.processRace(RACE, List.of(), true);
        assertNotNull(s);
        assertFalse(s.drivers().isEmpty());
    }
}

package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Session;
import br.com.rsousa.pojo.SessionType;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssettoCorsaCompetizioneTransformerTest {

    private static final File QUALIFY = new File("test-data/acc_qualify.json");
    private static final File RACE    = new File("test-data/acc_race.json");

    private final AssettoCorsaCompetizioneTransformer t = new AssettoCorsaCompetizioneTransformer();

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
    void processQualify_qualifyFile_returns3Drivers() throws Exception {
        Session s = t.processQualify(QUALIFY, List.of(), false, false);
        assertNotNull(s);
        assertEquals(SessionType.QUALIFY, s.type());
        assertEquals(3, s.drivers().size());
    }

    @Test
    void processQualify_qualifyFile_firstDriverCorrect() throws Exception {
        Session s = t.processQualify(QUALIFY, List.of(), false, false);
        var first = s.drivers().get(0);
        assertEquals("Federico Siv", first.getName());
        assertEquals(1, first.getPosition());
        assertTrue(first.isPolePosition());
    }

    @Test
    void processQualify_qualifyFile_driverOrder() throws Exception {
        Session s = t.processQualify(QUALIFY, List.of(), false, false);
        assertEquals("Alberto For",  s.drivers().get(1).getName());
        assertEquals("Andrea Mel",   s.drivers().get(2).getName());
    }

    @Test
    void processQualify_isSelective_setsFlag() throws Exception {
        Session s = t.processQualify(QUALIFY, List.of(), false, true);
        assertTrue(s.isSelective());
    }

    @Test
    void processQualify_raceFile_redirectsToRace() throws Exception {
        // acc_race.json has sessionType "R" → redirected to processRace
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
    }

    @Test
    void processRace_hardDnf_stillReturnsSession() throws Exception {
        Session s = t.processRace(RACE, List.of(), true);
        assertNotNull(s);
        assertFalse(s.drivers().isEmpty());
    }
}

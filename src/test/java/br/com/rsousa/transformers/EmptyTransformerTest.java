package br.com.rsousa.transformers;

import br.com.rsousa.pojo.SessionType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmptyTransformerTest {

    private final EmptyTransformer t = new EmptyTransformer();

    @Test
    void processEvent_file_returnsNull() throws Exception {
        assertNull(t.processEvent(null, List.of(), false, false));
    }

    @Test
    void processQualify_returnsEmptyQualifySession() throws Exception {
        var s = t.processQualify(null, List.of(), false, false);
        assertNotNull(s);
        assertEquals(SessionType.QUALIFY, s.type());
        assertTrue(s.drivers().isEmpty());
    }

    @Test
    void processQualify_isSelective_setsFlag() throws Exception {
        var s = t.processQualify(null, List.of(), false, true);
        assertTrue(s.isSelective());
    }

    @Test
    void processRace_returnsEmptyRaceSession() throws Exception {
        var s = t.processRace(null, List.of(), false);
        assertNotNull(s);
        assertEquals(SessionType.RACE, s.type());
        assertTrue(s.drivers().isEmpty());
    }

    @Test
    void processEvent_noArgs_returnsFalse() {
        assertFalse(t.processEvent());
    }
}

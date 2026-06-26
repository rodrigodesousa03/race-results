package br.com.rsousa.transformers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulatorTransformerTest {

    // Anonymous implementation just to access the default method
    private final SimulatorTransformer t = new EmptyTransformer();

    @Test void regularName_isDriver()       { assertTrue(t.isDriver("Matheus Machado")); }
    @Test void emptyName_notDriver()        { assertFalse(t.isDriver("")); }
    @Test void diretor_notDriver()          { assertFalse(t.isDriver("Diretor Brasil")); }
    @Test void comentarista_notDriver()     { assertFalse(t.isDriver("Comentarista Race")); }
    @Test void narrador_notDriver()         { assertFalse(t.isDriver("Narrador F1")); }
    @Test void ellevenTV_notDriver()        { assertFalse(t.isDriver("ellevenTV")); }
}

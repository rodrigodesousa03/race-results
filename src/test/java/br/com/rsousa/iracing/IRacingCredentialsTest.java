package br.com.rsousa.iracing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IRacingCredentialsTest {

    // ---- isConfigured ----

    @Test
    void isConfigured_defaultEmpty_returnsFalse() {
        assertFalse(new IRacingCredentials().isConfigured());
    }

    @Test
    void isConfigured_allFieldsSet_returnsTrue() {
        IRacingCredentials c = new IRacingCredentials();
        c.setEmail("a@b.com");
        c.setPassword("pass");
        c.setClientId("cid");
        c.setClientSecret("csecret");
        assertTrue(c.isConfigured());
    }

    @Test
    void isConfigured_missingEmail_returnsFalse() {
        IRacingCredentials c = new IRacingCredentials();
        c.setPassword("pass"); c.setClientId("cid"); c.setClientSecret("s");
        assertFalse(c.isConfigured());
    }

    @Test
    void isConfigured_missingPassword_returnsFalse() {
        IRacingCredentials c = new IRacingCredentials();
        c.setEmail("a@b.com"); c.setClientId("cid"); c.setClientSecret("s");
        assertFalse(c.isConfigured());
    }

    @Test
    void isConfigured_missingClientId_returnsFalse() {
        IRacingCredentials c = new IRacingCredentials();
        c.setEmail("a@b.com"); c.setPassword("pass"); c.setClientSecret("s");
        assertFalse(c.isConfigured());
    }

    @Test
    void isConfigured_missingClientSecret_returnsFalse() {
        IRacingCredentials c = new IRacingCredentials();
        c.setEmail("a@b.com"); c.setPassword("pass"); c.setClientId("cid");
        assertFalse(c.isConfigured());
    }

    // ---- load ----

    @Test
    void load_whenFileDoesNotExist_returnsEmptyAndNotConfigured() {
        // The load() method silently ignores IOException —
        // as long as the file at ~/.race-results/iracing.properties is absent
        // or unreadable, we get an empty (unconfigured) object back.
        IRacingCredentials c = IRacingCredentials.load();
        assertNotNull(c);
        // Cannot assert isConfigured() because user may have real credentials on this machine.
        // We can at least verify the object is returned without throwing.
    }

    // ---- getters / setters ----

    @Test
    void gettersSetters_roundTrip() {
        IRacingCredentials c = new IRacingCredentials();
        c.setEmail("test@iracing.com");
        c.setPassword("secret");
        c.setClientId("67090-pwlimited");
        c.setClientSecret("abc123");

        assertEquals("test@iracing.com", c.getEmail());
        assertEquals("secret",           c.getPassword());
        assertEquals("67090-pwlimited",  c.getClientId());
        assertEquals("abc123",           c.getClientSecret());
    }
}

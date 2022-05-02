package Global;

import org.junit.Test;

import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class TestConfiguration {

    @Test
    public void testCharger() {
        InputStream in = Configuration.chargerFichier("default.cfg");
        assertNotNull(in);
    }

    @Test
    public void testExceptionChargerVide() {
        UncheckedIOException e = assertThrows(
                UncheckedIOException.class,
                () -> Configuration.chargerFichier("")
        );
        assertTrue(e.getMessage().contains("Impossible de charger le fichier : "));
    }

    @Test
    public void testExceptionChargerInexistant() {
        UncheckedIOException e = assertThrows(
                UncheckedIOException.class,
                () -> Configuration.chargerFichier("abcd")
        );
        assertTrue(e.getMessage().contains("Impossible de charger le fichier : abcd"));
    }

    @Test
    public void testInstance() {
        Configuration instance = Configuration.instance();
        assertNotNull(instance);
    }

    @Test
    public void testLireProprieteLogLevel() {
        switch (Configuration.instance().lirePropriete("LogLevel")) {
            case "ALL":
            case "CONFIG":
            case "FINE":
            case "FINER":
            case "FINEST":
            case "INFO":
            case "OFF":
            case "SEVERE":
            case "WARNING":
                break;
            default:
                fail();
        }
    }

    @Test
    public void testExceptionLireProprieteVide() {
        NoSuchElementException e = assertThrows(
                NoSuchElementException.class,
                () -> Configuration.instance().lirePropriete("")
        );
        assertTrue(e.getMessage().contains("Propriété inexistante : "));
    }

    @Test
    public void testExceptionLireProprieteInexistante() {
        NoSuchElementException e = assertThrows(
                NoSuchElementException.class,
                () -> Configuration.instance().lirePropriete("abcd")
        );
        assertTrue(e.getMessage().contains("Propriété inexistante : abcd"));
    }

    @Test
    public void testLogger() {
        Logger l = Configuration.instance().logger();
        assertNotNull(l);
        assertEquals("Gaufre.Logger", l.getName());
        Level level = Level.parse(Configuration.instance().lirePropriete("LogLevel"));
        assertEquals(level, l.getLevel());
    }
}

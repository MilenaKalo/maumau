package groupone.spieler_management.services;



import groupone.spieler_management.classes.Spieler;
import groupone.spieler_management.implementation.SpielerImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SpielerServiceTest {
    private SpielerService spielerImpl = new SpielerImpl();
    private Spieler spieler = spielerImpl.spielerErstellen(1L, "Player 1");

    @Test
    public void testErhöhePunkt() {
        spielerImpl.erhöhePunkt(spieler);
        assertEquals(1, spieler.getPunkte());
    }

    @Test
    public void testMaumau() {
        spielerImpl.maumau(spieler);
        assertTrue(spieler.isMaumauGesagt());
    }

    @Test
    public void testSageMau() {
        spielerImpl.sageMau(spieler);
        assertTrue(spieler.isMauGesagt());
    }

    @Test
    public void testMauZuruecksetzen() {
        spieler.setMauGesagt(true);
        spielerImpl.mauZuruecksetzen(spieler);
        assertFalse(spieler.isMauGesagt());
    }

    @Test
    public void testSpielerErstellen() {
        Spieler newSpieler = spielerImpl.spielerErstellen(2L, "Player 2");
        assertEquals(2, newSpieler.getId());
        assertEquals("Player 2", newSpieler.getName());
    }
}

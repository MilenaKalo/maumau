package groupone.spieler_management.services;


import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;


import groupone.spieler_management.classes.Spieler;

import groupone.spieler_management.implementation.SpielerImpl;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class SpielerServiceTest {
    private SpielerImpl spielerImpl;
    private Spieler spieler;

    @BeforeEach
    public void setUp() {
        spielerImpl = new SpielerImpl();
        spieler = spielerImpl.spielerErstellen(1, "Player 1", 0);
    }

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
        Spieler newSpieler = spielerImpl.spielerErstellen(2, "Player 2", 5);
        assertEquals(2, newSpieler.getId());
        assertEquals("Player 2", newSpieler.getName());
        assertEquals(5, newSpieler.getPunkte());
    }
}

package groupone.spieler_management.services;


import groupone.kartenstapel_management.classes.Karte;
import groupone.spieler_management.classes.VirtuellerSpieler;
import groupone.spieler_management.implementation.VirtuellerSpielerImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VirtuellerSpielerServiceTest {
    private VirtuellerSpielerService spielerImpl = new VirtuellerSpielerImpl();
    private VirtuellerSpieler spieler = spielerImpl.spielerErstellen();

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
        spieler.getSpielerHand().getKarten().add(new Karte("Pik", "7"));
        spieler.getSpielerHand().setAnzahlKarten(1);
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
        VirtuellerSpieler spieler1 = spielerImpl.spielerErstellen();
        assertNotNull(spieler1);
        assertNotNull(spieler1.getId());
        assertNotNull(spieler1.getName());
    }

    @Test
    public void testSpielerErstellenIdUniqueness() {
        VirtuellerSpieler spieler1 = spielerImpl.spielerErstellen();
        VirtuellerSpieler spieler2 = spielerImpl.spielerErstellen();
        assertNotEquals(spieler1.getId(), spieler2.getId());
    }

    @Test
    public void testSpielerErstellenNameUniqueness() {
        VirtuellerSpieler spieler1 = spielerImpl.spielerErstellen();
        VirtuellerSpieler spieler2 = spielerImpl.spielerErstellen();
        assertNotEquals(spieler1.getName(), spieler2.getName());
    }

}

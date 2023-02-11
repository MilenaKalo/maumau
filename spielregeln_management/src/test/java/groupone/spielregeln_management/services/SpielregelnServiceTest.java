package groupone.spielregeln_management.services;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.spiel_management.classes.Spiel;
import groupone.spiel_management.services.KartenSpielerService;
import groupone.spieler_management.classes.Spieler;
import groupone.spieler_management.classes.SpielerInterface;
import groupone.spieler_management.classes.VirtuellerSpieler;
import groupone.spielregeln_management.implementation.SonderregelnImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.picocontainer.ComponentAdapter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SpielregelnServiceTest {

    @InjectMocks
    SpielregelnService spielregelnService = new SonderregelnImpl();

    @Mock
    KartenSpielerService kartenSpielerService;

    Karte herzSieben = new Karte("Herz", "7");
    Karte herzAcht = new Karte("Herz", "8");
    Karte herzNeun = new Karte("Herz", "9");
    Karte herzZehn = new Karte("Herz", "10");
    Karte herzBube = new Karte("Herz", "Bube");
    Karte karoSieben = new Karte("Karo", "7");
    Karte karoAcht = new Karte("Karo", "8");
    Karte karoNeun = new Karte("Karo", "9");
    Karte karoZehn = new Karte("Karo", "10");

    List<Karte> ablageStapelListe = new ArrayList<>();
    List<Karte> ziehStapelListe = new ArrayList<>();
    List<Karte> spielerHandListe = new ArrayList<>();
    //Erzeugung eines Spiels
    //Runde
    int runde = 1;

    //Ablagestapel
    int anzahlKartenAblage = 0;
    List<Karte> ablageKarten = new ArrayList<>();
    AblageStapel ablageStapel = new AblageStapel(anzahlKartenAblage, ablageKarten);

    //Ziehstapel
    List<Karte> ziehKarten = new ArrayList<>();
    ZiehStapel ziehStapel = new ZiehStapel(32, ziehKarten);

    Karte dummyKarte = new Karte("Karo", "Ass");

    SpielerInterface spieler1 = new Spieler(1, "Max Mustermann");
    SpielerInterface spieler2 = new Spieler(2, "Maxine Musterfrau");
    SpielerInterface spieler3 = new Spieler(3, "Erik Mustermann");
    SpielerInterface spieler4 = new Spieler(4, "Erika Musterfrau");

    List<SpielerInterface> spielerListe = new ArrayList<>();

    @Test
    public void testPrüfeAufSiebenGelegtFalse() {
        Spiel testSpiel = new Spiel();
        Karte obersteKarte = new Karte("Karo", "6");
        ablageStapel.getAblagekarten().add(obersteKarte);
        testSpiel.setAblageStapel(ablageStapel);
        boolean result = spielregelnService.prüfeAufSiebenGelegt(testSpiel);
        assertFalse(result);
    }

    @Test
    public void testPrüfeAufSiebenGelegtTrue() {
        Spiel testSpiel = new Spiel();
        Karte obersteKarte = new Karte("Karo", "7");
        ablageStapel.getAblagekarten().add(obersteKarte);
        testSpiel.setAblageStapel(ablageStapel);
        boolean result = spielregelnService.prüfeAufSiebenGelegt(testSpiel);
        assertTrue(result);
    }

    @Test
    public void mauStrafeTest() {

        //Setup
        //Ziehstapel
        ziehStapelListe.add(herzAcht);
        ziehStapelListe.add(herzNeun);
        ziehStapelListe.add(herzZehn);
        ZiehStapel ziehStapel = new ZiehStapel(3, ziehStapelListe);

        // Spieler
        Spieler spieler = new Spieler(1, "Max Mustermann");

        // Spielerhand
        spielerHandListe.add(karoAcht);
        SpielerHand spielerHand = new SpielerHand(1, spielerHandListe);
        spieler.setSpielerHand(spielerHand);
        //expected
        int erwartet = 2;

        //actual
        spielregelnService.mauStrafe(spieler, ziehStapel);

        //assert
        assertEquals(erwartet, spieler.getSpielerHand().getAnzahlKarten());
        assertEquals(2, ziehStapel.getZiehkarten().size());

    }
    @Test
    public void aussetzenTest(){

        //Setup
        //Spielerliste
        List<SpielerInterface> spielerListe = new ArrayList<>();
        spielerListe.add(spieler1);
        spielerListe.add(spieler2);
        spielerListe.add(spieler3);
        spielerListe.add(spieler4);

        //Ziehkarten
        for (int i = 0; i < 32; i++) {
            ziehKarten.add(dummyKarte);
        }

        // Ablagestapel
        List<Karte> ablageKarten2 = new ArrayList<>();
        ablageKarten2.clear();
        ablageKarten2.add(herzAcht);
        AblageStapel stapel = new AblageStapel(1, ablageKarten2);

        //erwartet
        SpielerInterface erwartet = spieler3;

        //actual

        Spiel spiel = new Spiel (spielerListe, runde, stapel, ziehStapel);
        spiel.setAktiverSpieler(spieler1);
        SpielerInterface actual = spielregelnService.aussetzen(spiel);

        //assert
        assertEquals(erwartet, actual);
    }

    @Test
    public void nächsterSpielerIstDranTest(){
        //Setup
        //Spielerliste
        spielerListe.add(spieler1);
        spielerListe.add(spieler2);
        spielerListe.add(spieler3);
        spielerListe.add(spieler4);

        //Ziehkarten
        for (int i = 0; i < 32; i++) {
            ziehKarten.add(dummyKarte);
        }

        // Ablagestapel
        ablageStapel.getAblagekarten().add(karoNeun);

        //erwartet
        SpielerInterface erwartet = spieler4;

        //actual
        Spiel spiel = new Spiel (spielerListe, runde, ablageStapel, ziehStapel);
        spiel.setAktiverSpieler(spieler1);
        spielregelnService.nächsterSpielerIstDran(spiel);


        //assert
        assertEquals(erwartet, spiel.getAktiverSpieler());
    }

    @Test
    public void pruefeKarteTest(){
        //Setup
        ablageStapelListe.add(herzAcht);
        AblageStapel ablageStapel = new AblageStapel(1, ablageStapelListe);

        SpielerInterface spieler = new Spieler(1, "Max Mustermann");
        spielerHandListe.add(karoNeun);
        SpielerHand spielerHand = new SpielerHand(1, spielerHandListe);
        spieler.setSpielerHand(spielerHand);

        //expected
        boolean erwartet = false;

        //actual
        assertEquals(spielregelnService.pruefeKarte(spieler.getSpielerHand().getKarten().get(0)
                , ablageStapel), erwartet);

    }

    @Test
    public void testPruefeKarte_gleicheFarbe() {
        // Vorbereitung
        Karte karte1 = new Karte("Herz", "Acht");
        Karte karte2 = new Karte("Herz", "Sieben");
        ablageStapel.getAblagekarten().add(karte1);

        // Ausführung
        SpielerInterface  spieler = new VirtuellerSpieler();
        boolean result = spielregelnService.pruefeKarte(karte2, ablageStapel);

        // Überprüfung
        assertTrue(result);
    }

    @Test
    public void testPruefeKarte_gleicherWert() {
        // Vorbereitung
        Karte karte1 = new Karte("Herz", "Acht");
        Karte karte2 = new Karte("Karo", "Acht");
        ablageStapel.getAblagekarten().add(karte1);

        // Ausführung
        SpielerInterface  spieler = new VirtuellerSpieler();
        boolean result = spielregelnService.pruefeKarte(karte2, ablageStapel);

        // Überprüfung
        assertTrue(result);
    }

    @Test
    public void testPruefeKarte_unterschiedlich() {
        // Vorbereitung
        Karte karte1 = new Karte("Herz", "Acht");
        Karte karte2 = new Karte("Karo", "Sieben");
        ablageStapel.getAblagekarten().add(karte1);

        // Ausführung
        SpielerInterface  spieler = new VirtuellerSpieler();
        boolean result = spielregelnService.pruefeKarte(karte2, ablageStapel);

        // Überprüfung
        assertFalse(result);
    }



    @Test
    public void assGelegtTestTrue(){
        //Setup
        //Ablagestapel
        ablageStapelListe.add(dummyKarte);
        AblageStapel ablageStapel = new AblageStapel(1, ablageStapelListe);

        //expected
        boolean erwartet = true;

        //actual
        boolean actual = spielregelnService.assGelegt(ablageStapel);

        //assert
        assertEquals(actual, erwartet);
    }

    @Test
    public void testAssGelegtFalse() {

        Karte keinAssKarte = new Karte("Karo", "5");

        ablageStapel.getAblagekarten().add(keinAssKarte);

        assertFalse(spielregelnService.assGelegt(ablageStapel));
    }


    @Test
    public void mussSichFarbeWuenschenTest() {
        //setup
        Karte herzBube = new Karte("Herz", "Bube");
        ablageStapelListe.add(herzBube);
        AblageStapel ablageStapel = new AblageStapel(1, ablageStapelListe);

        //expected
        boolean expected = true;

        //actual
        boolean actual = spielregelnService.mussSichFarbeWuenschen(ablageStapel);

        //assertion
        assertEquals(expected, actual);
    }

    @Test
    public void mussSichFarbeWuenschen_KeinBubeAufAblageStapel_False() {
        Karte keinBubeKarte = new Karte("Herz", "10");
        ablageStapel.getAblagekarten().add(keinBubeKarte);

        boolean result = spielregelnService.mussSichFarbeWuenschen(ablageStapel);

        assertFalse(result);
    }

    @Test
    public void richtungswechselTest() {

        // Setup
        ablageStapelListe.add(karoNeun);
        AblageStapel ablageStapel = new AblageStapel(1, ablageStapelListe);

        // expected
        boolean expected = true;

        // actual
        boolean actual = spielregelnService.richtungWechsel(ablageStapel);

        // assertion
        assertEquals(expected, actual);

    }

    @Test
    public void testRichtungWechselFalse() {
        // Vorbereitung
        Karte karte = new Karte("Herz", "10");
        ablageStapel.getAblagekarten().add(karte);

        // Ausführung
        boolean erwartetesErgebnis = false;
        boolean tatsaechlichesErgebnis =  spielregelnService.richtungWechsel(ablageStapel);

        assertEquals(erwartetesErgebnis, tatsaechlichesErgebnis);
    }

}

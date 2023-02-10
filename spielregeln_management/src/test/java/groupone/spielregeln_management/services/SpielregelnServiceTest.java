package groupone.spielregeln_management.services;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.spiel_management.classes.Spiel;
import groupone.spiel_management.services.KartenSpielerService;
import groupone.spieler_management.classes.Spieler;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    Spieler spieler1 = new Spieler(1, "Max Mustermann", 0);
    Spieler spieler2 = new Spieler(2, "Maxine Musterfrau", 0);
    Spieler spieler3 = new Spieler(3, "Erik Mustermann", 0);
    Spieler spieler4 = new Spieler(4, "Erika Musterfrau", 0);

    List<Spieler> spielerListe = new ArrayList<>();

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
        Spieler spieler = new Spieler(1, "Max Mustermann", 0);

        // Spielerhand
        spielerHandListe.add(karoAcht);
        SpielerHand spielerHand = new SpielerHand(1, spielerHandListe);
        spieler.setSpielerHand(spielerHand);
        //expected
        int erwartet = 2;

        //actual
        spielregelnService.mauStrafe(spieler, ziehStapel);

        //assert
        Assertions.assertEquals(erwartet, spieler.getSpielerHand().getAnzahlKarten());

    }
    @Test
    public void aussetzenTest(){

        //Setup
        //Spielerliste
        List<Spieler> spielerListe = new ArrayList<>();
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
        Spieler erwartet = spieler3;

        //actual

        Spiel spiel = new Spiel (spielerListe, runde, stapel, ziehStapel);
        spiel.setAktiverSpieler(spieler1);
        Spieler actual = spielregelnService.aussetzen(spiel);

        //assert
        Assertions.assertEquals(erwartet, actual);
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
        Spieler erwartet = spieler4;

        //actual
        Spiel spiel = new Spiel (spielerListe, runde, ablageStapel, ziehStapel);
        spiel.setAktiverSpieler(spieler1);
        spielregelnService.nächsterSpielerIstDran(spiel);


        //assert
        Assertions.assertEquals(erwartet, spiel.getAktiverSpieler());
    }

    @Test
    public void pruefeKarteTest(){
        //Setup
        ablageStapelListe.add(herzAcht);
        AblageStapel ablageStapel = new AblageStapel(1, ablageStapelListe);

        Spieler spieler = new Spieler(1, "Max Mustermann", 0);
        spielerHandListe.add(karoNeun);
        SpielerHand spielerHand = new SpielerHand(1, spielerHandListe);
        spieler.setSpielerHand(spielerHand);

        //expected
        boolean erwartet = false;

        //actual
        Assertions.assertEquals(spielregelnService.pruefeKarte(spieler.getSpielerHand().getKarten().get(0)
                , ablageStapel), erwartet);

    }

    @Test
    public void assGelegtTest(){
        //Setup
        //Ablagestapel
        ablageStapelListe.add(dummyKarte);
        AblageStapel ablageStapel = new AblageStapel(1, ablageStapelListe);

        //expected
        boolean erwartet = true;

        //actual
        boolean actual = spielregelnService.assGelegt(ablageStapel);

        //assert
        Assertions.assertEquals(actual, erwartet);
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
        Assertions.assertEquals(expected, actual);
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
        Assertions.assertEquals(expected, actual);

    }

}

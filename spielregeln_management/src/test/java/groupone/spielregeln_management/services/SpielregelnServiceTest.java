package groupone.spielregeln_management.services;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.spiel_management.classes.Spiel;
import groupone.spiel_management.implementation.SpielImpl;
import groupone.spiel_management.services.SpielService;
import groupone.spieler_management.classes.Spieler;
import groupone.spielregeln_management.implementation.SonderregelnImpl;
import groupone.spielregeln_management.implementation.SpielregelnImpl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;


public class SpielregelnServiceTest {

    private SpielService spielService = new SpielImpl();
    private SpielregelnService spielregelnService = new SonderregelnImpl();

    Karte herzSieben = new Karte("Herz", "Sieben");
    Karte herzAcht = new Karte("Herz", "Acht");
    Karte herzNeun = new Karte("Herz", "Neun");
    Karte herzZehn = new Karte("Herz", "Zehn");
    Karte karoAcht = new Karte("Karo", "Acht");
    Karte karoNeun = new Karte("Karo", "Neun");
    Karte karoZehn = new Karte("Karo", "Zehn");

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
    public void siebenGelegtTest() {

        //Setup
        //Ablagestapel
        ablageStapelListe.add(herzSieben);
        AblageStapel ablageStapel = new AblageStapel(1, ablageStapelListe);

        //Ziehstapel
        ziehStapelListe.add(herzAcht);
        ziehStapelListe.add(herzNeun);
        ziehStapelListe.add(herzZehn);
        ZiehStapel ziehStapel = new ZiehStapel(3, ziehStapelListe);

        //Spieler
        Spieler spieler = new Spieler(1, "Max Mustermann", 0);

        //Spielerhand
        spielerHandListe.add(karoAcht);
        spielerHandListe.add(karoNeun);
        spielerHandListe.add(karoZehn);
        SpielerHand spielerHand = new SpielerHand(3, spielerHandListe);
        spieler.setSpielerHand(spielerHand);

        //expected
        int anzahlKartenErwartet = 5;

        //actual
     //   spielregelnService.siebenGelegt(spieler.getSpielerHand(), ziehStapel);

        //assert
        Assertions.assertEquals(anzahlKartenErwartet, spieler.getSpielerHand().getAnzahlKarten());

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

        //erwartet
        Spieler erwartet = spieler2;

        //actual
        Spiel spiel = spielService.erstelleSpiel(spielerListe, runde, ablageStapel, ziehStapel);
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

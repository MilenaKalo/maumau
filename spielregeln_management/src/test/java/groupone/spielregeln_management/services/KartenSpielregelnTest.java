package groupone.spielregeln_management.services;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.spielregeln_management.implementation.KartenSpielregelnImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class KartenSpielregelnTest {

    KartenSpielregelnService kartenSpielregelnService = new KartenSpielregelnImpl();

    @Test
    public void prüfeKarteTest(){

        //Setup
        Karte herzZehn = new Karte("Herz", "Zehn");
        Karte herzNeun = new Karte("Herz", "Neun");

        int anzahlAblageKarten = 1;
        List<Karte> kartenAblageListe = new ArrayList<>();
        kartenAblageListe.add(herzZehn);
        AblageStapel ablageStapel = new AblageStapel(anzahlAblageKarten, kartenAblageListe);

        //erwartet
        boolean erwartet = true;

        //actual

        Assertions.assertEquals(erwartet, kartenSpielregelnService.prüfeKarte(herzNeun, ablageStapel));

    }

    @Test
    public void prüfeKarteTestWunschFarbe(){

        //Setup
        Karte herzBube = new Karte("Herz", "Bube");
        Karte herzNeun = new Karte("Herz", "Neun");

        int anzahlAblageKarten = 1;
        List<Karte> kartenAblageListe = new ArrayList<>();
        kartenAblageListe.add(herzBube);
        AblageStapel ablageStapel = new AblageStapel(anzahlAblageKarten, kartenAblageListe);
        ablageStapel.setWunschFarbe("Herz");

        //erwartet
        boolean erwartet = true;

        //actual
        Assertions.assertEquals(erwartet, kartenSpielregelnService.prüfeKarte(herzNeun, ablageStapel));

    }





}

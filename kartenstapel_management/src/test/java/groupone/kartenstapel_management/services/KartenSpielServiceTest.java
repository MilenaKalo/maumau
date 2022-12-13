package groupone.kartenstapel_management.services;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.kartenstapel_management.implementation.KartenSpielImpl;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;


public class KartenSpielServiceTest {


    private KartenSpielService kartenSpielService = new KartenSpielImpl();

    Karte herzBube = new Karte("Herz", "Bube");
    Karte pikAss = new Karte("Pik", "Ass");
    Karte karoZehn = new Karte("Karo", "Zehn");
    Karte karoNeun = new Karte("Karo", "Neun");

    List<Karte> ziehStapelKarten = new ArrayList<>();
    List<Karte> ablageStapelKarten = new ArrayList<>();

    @Test
    public void legeErsteKarteAufAblagestapelTest() {

        // Setup
        ziehStapelKarten.add(herzBube);
        ziehStapelKarten.add(pikAss);
        ziehStapelKarten.add(karoNeun);
        ziehStapelKarten.add(karoZehn);
        int anzahlZiehStapel = 4;
        int anzahlAblageStapel = 0;

        ZiehStapel ziehStapel = new ZiehStapel(anzahlZiehStapel, ziehStapelKarten);
        AblageStapel ablageStapel = new AblageStapel(anzahlAblageStapel, ablageStapelKarten);

        //erwartet
        int anzahlZiehStapelErwartet = 3;
        int anzahlAblageStapelErwartet = 1;
        Karte erwarteteKarte = herzBube;

        kartenSpielService.legeErsteKarteAufAblagestapel(ziehStapel, ablageStapel);

        Assertions.assertEquals(anzahlZiehStapelErwartet, ziehStapel.getAnzahlKarten());
        Assertions.assertEquals(anzahlAblageStapelErwartet, ablageStapel.getAnzahlKarten());
        Assertions.assertEquals(erwarteteKarte, ablageStapel.getAblagekarten().get(0));

    }

    @Test
    public void mischeKarten() {

        //Setup
        List<Karte> kartenliste = new ArrayList<>();
        kartenliste.add(herzBube);
        kartenliste.add(pikAss);
        kartenliste.add(karoNeun);
        kartenliste.add(karoZehn);

        //erwartet
        boolean erwartet = true;

        //actual
        List<Karte> neuekartenliste = kartenSpielService.mischeKarten(kartenliste);

        Assertions.assertEquals(erwartet, neuekartenliste != kartenliste);

    }

    @Test
    public void kartenDesAblagestapelsDemZiehstapelUebergebenTest() {

        //Setup
        ablageStapelKarten.add(herzBube);
        ablageStapelKarten.add(pikAss);
        ablageStapelKarten.add(karoNeun);
        ablageStapelKarten.add(karoZehn);

        int anzahlAblageStapel = 4;
        AblageStapel ablageStapel = new AblageStapel(anzahlAblageStapel, ablageStapelKarten);

        int anzahlZiehStapel = 0;
        ZiehStapel ziehStapel = new ZiehStapel(anzahlZiehStapel, ziehStapelKarten);

        //erwartet
        int anzahlAblageStapelErwartet = 0;
        int anzahlZiehStapelErwartet = 0;

        //actual
        kartenSpielService.kartenDesAblagestapelsDemZiehstapelUebergeben(ablageStapel, ziehStapel);

        Assertions.assertEquals(anzahlAblageStapelErwartet, ablageStapel.getAnzahlKarten());
        Assertions.assertEquals(anzahlZiehStapelErwartet, ziehStapel.getAnzahlKarten());
    }

}
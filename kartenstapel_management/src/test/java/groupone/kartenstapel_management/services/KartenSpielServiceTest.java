package groupone.kartenstapel_management.services;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.kartenstapel_management.implementation.KartenSpielImpl;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KartenSpielServiceTest {

    @InjectMocks
    KartenSpielService kartenSpielService = new KartenSpielImpl();

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
        Karte erwarteteKarte = karoZehn;

        kartenSpielService.legeErsteKarteAufAblagestapel(ziehStapel, ablageStapel);

        Assertions.assertEquals(anzahlZiehStapelErwartet, ziehStapel.getAnzahlKarten());
        Assertions.assertEquals(anzahlAblageStapelErwartet, ablageStapel.getAnzahlKarten());
        Assertions.assertEquals(erwarteteKarte, ablageStapel.getAblagekarten().get(0));

    }

    @Test
    public void legeErsteKarteAufAblagestapel_SadPath_ZiehStapelLeer() {
        ZiehStapel ziehStapel = new ZiehStapel();
        AblageStapel ablageStapel = new AblageStapel();

        assertThrows(Exception.class, () -> {
            kartenSpielService.legeErsteKarteAufAblagestapel(ziehStapel, ablageStapel);
        });
    }

    @Test
    public void sadPathTestMischeKarten() {

        assertThrows(Exception.class, () -> {
            kartenSpielService.mischeKarten(null);
        });
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
        boolean actual = neuekartenliste.equals(kartenliste);

        // Assertions.assertEquals(erwartet, neuekartenliste != kartenliste);
        Assertions.assertEquals(erwartet,actual);
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
        int anzahlAblageStapelErwartet = 1;
        int anzahlZiehStapelErwartet = 3;

        //actual
        kartenSpielService.kartenDesAblagestapelsDemZiehstapelUebergeben(ablageStapel, ziehStapel);

        Assertions.assertEquals(anzahlAblageStapelErwartet, ablageStapel.getAnzahlKarten());
        Assertions.assertEquals(anzahlZiehStapelErwartet, ziehStapel.getAnzahlKarten());
    }

    @Test
    void erstelleZiehstapelTest() {

    	//Setup
    	ziehStapelKarten.add(herzBube);
        ziehStapelKarten.add(pikAss);
        ziehStapelKarten.add(karoNeun);
        ziehStapelKarten.add(karoZehn);

        //erwartet
        int anzahlZiehStapelErwartet = 4;

        //actual
        ZiehStapel ziehStapel = kartenSpielService.erstelleZiehStapel(4, ziehStapelKarten);

        Assertions.assertEquals(anzahlZiehStapelErwartet, ziehStapel.getAnzahlKarten());
    }

    void erstelleZiehstapelTest2() {

        //Setup
        ziehStapelKarten.add(herzBube);
        ziehStapelKarten.add(pikAss);
        ziehStapelKarten.add(karoNeun);
        ziehStapelKarten.add(karoZehn);
        ziehStapelKarten.add(karoNeun);

        //erwartet
        int anzahlZiehStapelErwartet = 5;

        //actual
        ZiehStapel ziehStapel = kartenSpielService.erstelleZiehStapel(5, ziehStapelKarten);

        Assertions.assertEquals(anzahlZiehStapelErwartet, ziehStapel.getAnzahlKarten());
    }


    @Test
    void erstelleAblagestapelTest(){

     	//Setup
     	ablageStapelKarten.add(herzBube);
          ablageStapelKarten.add(pikAss);
          ablageStapelKarten.add(karoNeun);
          ablageStapelKarten.add(karoZehn);

          //erwartet
          int anzahlAblageStapelErwartet = 4;

          //actual
          AblageStapel ablageStapel = kartenSpielService.erstelleAblageStapel(4, ablageStapelKarten);

          Assertions.assertEquals(anzahlAblageStapelErwartet, ablageStapel.getAnzahlKarten());
    }

    @Test
    void erstelleSpielerhandTest(){

     	//Setup
     	List<Karte> spielerHandKarten = new ArrayList<>();
     	spielerHandKarten.add(herzBube);
     	spielerHandKarten.add(pikAss);
     	spielerHandKarten.add(karoNeun);
     	spielerHandKarten.add(karoZehn);

     	//erwartet
     	int anzahlSpielerHandErwartet = 4;

     	//actual
     	SpielerHand spielerHand = kartenSpielService.erstelleSpielerHand(4, spielerHandKarten);

     	Assertions.assertEquals(anzahlSpielerHandErwartet, spielerHand.getAnzahlKarten());

    }
    @Test
    void testErstelleKarten() {
        List<Karte> kartenDeck = kartenSpielService.erstelleKarten();
        assertEquals(32, kartenDeck.size());

    }

}

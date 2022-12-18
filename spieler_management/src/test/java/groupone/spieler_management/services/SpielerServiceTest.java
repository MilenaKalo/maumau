package groupone.spieler_management.services;


import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;

import groupone.spiel_management.services.KartenSpielerService;
import groupone.spieler_management.classes.Spieler;

import groupone.spieler_management.implementation.SpielerImpl;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;


public class SpielerServiceTest {

    private SpielerService spielerService = new SpielerImpl();


    private KartenSpielerService kartenSpielerImpl;

    @Test
    public void erhöhePunktTest() {
        //setup
        Spieler spieler = new Spieler(1L, "Max Muster", 1);

        //erwartet
        int punkteErwartet = 2;

         //actual
        spielerService.erhöhePunkt(spieler);

        // assert
        Assertions.assertEquals(punkteErwartet, spieler.getPunkte());
    }

    @Test
    public void maumauTest() {
       //Setup
        Spieler spieler = new Spieler(1L, "Max Mustermann", 0);
        List<Karte> kartenListe = new ArrayList<>();
        Karte herzBube = new Karte("Herz", "Bube");
        Karte pikAss = new Karte("Pik", "Ass");
        kartenListe.add(herzBube);
        kartenListe.add(pikAss);
        SpielerHand spielerHand = new SpielerHand(2, kartenListe);
        spieler.setSpielerHand(spielerHand);

        Karte kreuzVier = new Karte("Kreuz", "Vier");

        List<Karte> ablageListe = new ArrayList<>();
        ablageListe.add(kreuzVier);

        int anzahlAblageKarten = 1;

        AblageStapel ablageStapel = new AblageStapel(anzahlAblageKarten, ablageListe);

        //Erwartet
        boolean t = true;

        //actual
        Mockito.doNothing().when(kartenSpielerImpl).legeKarteAb(spieler.getSpielerHand(),
                spieler.getSpielerHand().getKarten().get(1), ablageStapel);
        spielerService.sageMau(spieler);

        //assert
        Assertions.assertEquals(t, spieler.isMauGesagt());
    }


    @Test
    public void sageMauTest() {
        //setup
        Spieler spieler = new Spieler(1L, "Max Muster", 1);
        spieler.setMauGesagt(false);
        //erwartet

        boolean erwartet = true;
        //actual

        spielerService.sageMau(spieler);

        // assert
        Assertions.assertEquals(erwartet, spieler.isMauGesagt());
    }

    @Test
    public void mauZurückTest() {
        //setup
        Spieler spieler = new Spieler(1L, "Max Muster", 1);
        spieler.setMauGesagt(true);
        //erwartet

        boolean erwartet = false;
        //actual

        spielerService.mauZuruecksetzen(spieler);

        // assert
        Assertions.assertEquals(erwartet, spieler.isMauGesagt());
    }

    @Test
    public void spielerErstellenTest() {
        //setup
        Spieler spieler = new Spieler(1L, "Max Muster", 1);
        //erwartet

        Spieler erwartet = spieler;
        //actual

        Spieler actual = spielerService.spielerErstellen(1L, "Max Muster", 1);

        // assert
        Assertions.assertEquals(erwartet.getName(), actual.getName());
    }
}

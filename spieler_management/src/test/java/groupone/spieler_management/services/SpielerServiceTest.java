package groupone.spieler_management.services;


import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;


import groupone.spieler_management.classes.Spieler;

import groupone.spieler_management.implementation.SpielerImpl;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;


public class SpielerServiceTest {
    // InjectMocks nur wennn ein anderes Attribut in der Klasse verwendet wird von einer anderen Komponente
    private SpielerService spielerService = new SpielerImpl();

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
        //setup
        Spieler spieler = new Spieler(1L, "Max Muster", 1);
        List<Karte> kartenListe = new ArrayList<>();
        int anzahl = 0;
        SpielerHand spielerHand = new SpielerHand(anzahl , kartenListe);
        spieler.setSpielerHand(spielerHand);
        spieler.setMauGesagt(false);
        //erwartet

        boolean erwartet = true;
        //actual

        spielerService.maumau(spieler);

        // assert
        Assertions.assertEquals(erwartet, spieler.isMaumauGesagt());
    }


    @Test
    public void sageMauTest() {
        //setup
        Spieler spieler = new Spieler(1L, "Max Muster", 1);
        Karte karte = new Karte("Herz", "9");
        List<Karte> kartenListe = new ArrayList<>();
        kartenListe.add(karte);
        int anzahl = 1;
        SpielerHand spielerHand = new SpielerHand(anzahl , kartenListe);
        spieler.setSpielerHand(spielerHand);
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

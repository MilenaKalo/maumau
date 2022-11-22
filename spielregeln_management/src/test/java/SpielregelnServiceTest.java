import classes.*;
import implementation.KartenSpielerImpl;
import implementation.SpielregelnImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import services.SpielregelnService;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SpielregelnServiceTest {

    @InjectMocks
    private SpielregelnService spielregelnService = new SpielregelnImpl();

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
        spielregelnService.siebenGelegt(spieler.getSpielerHand(), ziehStapel);

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
        int erwartet = 3;

        //actual
        spielregelnService.mauStrafe(spieler, ziehStapel);

        //assert
        Assertions.assertEquals(erwartet, spieler.getSpielerHand().getAnzahlKarten());

    }

    @Test
    public void prüfeWunschfarbeVorhandenTest() {
        //Setup
        //Spieler
        Spieler spieler = new Spieler(1, "Max Mustermann", 0);
        spieler.setWunschfarbe("Herz");

        //expected
        String erwartet = "Herz";

        //actual
        spielregelnService.prüfeWunschfarbe(spieler);

        //assert
        Assertions.assertEquals(erwartet, spieler.getWunschfarbe());

    }

    @Test
    public void prüfeWunschfarbeNichtVorhandenTest2() {

    }
}

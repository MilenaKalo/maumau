import classes.Karte;
import classes.SpielerHand;
import classes.ZiehStapel;
import implementation.KartenSpielerImpl;
import services.KartenSpielerService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class KartenSpielerServiceTest {

    @InjectMocks
    private KartenSpielerService kartenSpielerService = new KartenSpielerImpl();

    @Test
    public void legeKarteAbTest(){

        // Setup
        Karte herzBube = new Karte("Herz", "Bube");
        Karte pikAss = new Karte("Pik", "Ass");
        Karte karoZehn = new Karte("Karo", "Zehn");

        List<Karte> kartenListe = new ArrayList<>();
        kartenListe.add(herzBube);
        kartenListe.add(pikAss);
        kartenListe.add(karoZehn);

        int anzahlKarten = 3;

        SpielerHand spielerHand = new SpielerHand(anzahlKarten, kartenListe);

        // erwartet
        int erwartet = 2;

        // actual
        kartenSpielerService.legeKarteAb(spielerHand, kartenListe.get(2));

        Assertions.assertEquals(erwartet, spielerHand.getAnzahlKarten());
    }

    @Test
    public void zieheKarteTest(){

        // Setup
        Karte herzBube = new Karte("Herz", "Bube");
        Karte pikAss = new Karte("Pik", "Ass");
        Karte karoZehn = new Karte("Karo", "Zehn");
        Karte karoNeun = new Karte("Karo", "Neun");
        Karte karoAcht = new Karte("Karo", "Acht");

        int anzahlSpielerKarten = 3;
        List<Karte> kartenSpielerListe = new ArrayList<>();
        kartenSpielerListe.add(herzBube);
        kartenSpielerListe.add(pikAss);
        kartenSpielerListe.add(karoZehn);
        SpielerHand spielerHand = new SpielerHand(anzahlSpielerKarten, kartenSpielerListe);

        int anzahlZiehKarten = 2;
        List<Karte> kartenZiehListe = new ArrayList<>();
        kartenZiehListe.add(karoNeun);
        kartenZiehListe.add(karoAcht);
        ZiehStapel ziehStapel = new ZiehStapel(anzahlZiehKarten, kartenZiehListe);

        // erwartet
        int erwartetAnzahlKartenSpielerhand = 4;
        int erwartetAnzahlKartenZiehStapel = 1;

        // actual
        kartenSpielerService.zieheKarte(spielerHand, ziehStapel);

        Assertions.assertEquals(erwartetAnzahlKartenSpielerhand, spielerHand.getAnzahlKarten());
        Assertions.assertEquals(erwartetAnzahlKartenZiehStapel, ziehStapel.getAnzahlKarten());

    }


}

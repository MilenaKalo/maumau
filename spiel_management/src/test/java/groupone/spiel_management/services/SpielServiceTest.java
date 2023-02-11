package groupone.spiel_management.services;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.kartenstapel_management.services.KartenSpielService;
import groupone.spiel_management.classes.Spiel;
import groupone.spiel_management.implementation.SpielImpl;
import groupone.spieler_management.classes.Spieler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SpielServiceTest {

    @InjectMocks
    private SpielService spielService = new SpielImpl();

    @Mock
    private KartenSpielService kartenSpielService;

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
    public void erstelleSpielTest() {
        //Setup
        //Spielerliste
        spielerListe.add(spieler1);
        spielerListe.add(spieler2);
        spielerListe.add(spieler3);
        spielerListe.add(spieler4);

        //Runde
        int runde = 1;

        //Ablagestapel
        int anzahlKartenAblage = 0;
        List<Karte> ablageKarten = new ArrayList<>();
        AblageStapel ablageStapel = new AblageStapel(anzahlKartenAblage, ablageKarten);

       // Hier mocken
        //Mockito.doReturn(ziehKarten).when(kartenSpielService).findById(itemId);
        List<Karte> ziehKarten = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            ziehKarten.add(dummyKarte);
        }
        Mockito.when(kartenSpielService.erstelleKarten()).thenReturn(ziehKarten);
        Mockito.when(kartenSpielService.mischeKarten(ziehKarten)).thenAnswer(i -> {
            Collections.shuffle(ziehKarten);
            return ziehKarten;
        });

        ZiehStapel ziehStapel = new ZiehStapel(32, ziehKarten);

        //erwartet
        // erstelleSpiel verteilt die Karten sofort. Zu Beginn hat der Ziehstapel 32 Karten - 1 Karte für Ablagestapel - Anzahl Spieler * 5
        int ziehStapelAnzahl = 11;
        int ablagestapelAnzahl = 1;
        int spieler1KartenAnzahl = 5;
        int spieler2KartenAnzahl = 5;
        int spieler3KartenAnzahl = 5;
        int spieler4KartenAnzahl = 5;

        //actual
        spielService.erstelleSpiel(spielerListe, runde, ablageStapel, ziehStapel);

        //assert
        Assertions.assertEquals(ziehStapelAnzahl, ziehStapel.getZiehkarten().size());
        Assertions.assertEquals(ablagestapelAnzahl, ablageStapel.getAblagekarten().size());
        Assertions.assertEquals(spieler1KartenAnzahl, spieler1.getSpielerHand().getKarten().size());
        Assertions.assertEquals(spieler2KartenAnzahl, spieler2.getSpielerHand().getKarten().size());
        Assertions.assertEquals(spieler3KartenAnzahl, spieler3.getSpielerHand().getKarten().size());
        Assertions.assertEquals(spieler4KartenAnzahl, spieler4.getSpielerHand().getKarten().size());
    }

    @Test
    public void erstelleSpielerReihenfolgeTest(){
    // hinweis wenn man eine ArrayList shufflen will = Collections.shuffle(list);
        //Setup
        spielerListe.add(spieler1);
        spielerListe.add(spieler2);
        spielerListe.add(spieler3);
        spielerListe.add(spieler4);

        // erwartet
        List<SpielerInterface> spielerListe2 = new ArrayList<>();

        spielerListe2.add(spieler1);
        spielerListe2.add(spieler2);
        spielerListe2.add(spieler3);
        spielerListe2.add(spieler4);

        //actual
        spielService.erstelleSpielerReihenfolge(spielerListe);

        //assertion
        Assertions.assertNotEquals(spielerListe, spielerListe2);

    }

    @Test
    public void beendeSpielTest(){
        //Setup
        //Spielerliste
        spielerListe.add(spieler1);
        spielerListe.add(spieler2);
        spielerListe.add(spieler3);
        spielerListe.add(spieler4);

        // Hier mocken
        //Mockito.doReturn(ziehKarten).when(kartenSpielService).findById(itemId);
        List<Karte> ziehKarten = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            ziehKarten.add(dummyKarte);
        }
        Mockito.when(kartenSpielService.erstelleKarten()).thenReturn(ziehKarten);
        Mockito.when(kartenSpielService.mischeKarten(ziehKarten)).thenAnswer(i -> {
            Collections.shuffle(ziehKarten);
            return ziehKarten;
        });

        ZiehStapel ziehStapel = new ZiehStapel(32, ziehKarten);
        //erwartet
        String erwartet = "Das Spiel wurde beendet!";

        //actual
        Spiel spiel = spielService.erstelleSpiel(spielerListe, runde, ablageStapel, ziehStapel);
        String actual = spielService.beendeSpiel(spiel);

        //assert
        Assertions.assertEquals(erwartet, actual);
    }

    @Test
    public void gibGewinnerAusTest(){
        //Setup
        //Spielerliste
        spielerListe.add(spieler1);
        spielerListe.add(spieler2);
        spielerListe.add(spieler3);
        spielerListe.add(spieler4);
        spieler1.setPunkte(1);


        // Hier mocken
        //Mockito.doReturn(ziehKarten).when(kartenSpielService).findById(itemId);
        List<Karte> ziehKarten = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            ziehKarten.add(dummyKarte);
        }
        Mockito.when(kartenSpielService.erstelleKarten()).thenReturn(ziehKarten);
        Mockito.when(kartenSpielService.mischeKarten(ziehKarten)).thenAnswer(i -> {
            Collections.shuffle(ziehKarten);
            return ziehKarten;
        });

        ZiehStapel ziehStapel = new ZiehStapel(32, ziehKarten);
        //erwartet
        SpielerInterface erwartet = spieler1;

        //actual
        Spiel spiel = spielService.erstelleSpiel(spielerListe, runde, ablageStapel, ziehStapel);
        Spieler actual = spielService.gibGewinneraus(spiel);

        //assert
        Assertions.assertEquals(erwartet, actual);

    }

}

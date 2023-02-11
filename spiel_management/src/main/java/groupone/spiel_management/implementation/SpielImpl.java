package groupone.spiel_management.implementation;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.services.KartenSpielService;
import groupone.spiel_management.classes.Spiel;
import groupone.spieler_management.classes.Spieler;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.spiel_management.services.SpielService;
import groupone.spieler_management.classes.SpielerInterface;

import java.util.*;

public class SpielImpl implements SpielService {

    // das hier mocken ohne new KartenspielImpl
    //hier den Service nehmen
    // Injecten mit @Inject hier nicht da es i Constructor übergeben wird

    KartenSpielService kartenSpiel;
    // ConstructorInjection hier constructor übergeben
    //Tests
    // importierte komponente @ mock hier kartenspiel
    // service wird injectmocks verwenden
    public SpielImpl(KartenSpielService kartenSpiel) {
        this.kartenSpiel = kartenSpiel;
    }

    public SpielImpl() {
    }

    @Override
    public List<SpielerInterface> erstelleSpielerReihenfolge(List<SpielerInterface> spielerliste) {
        Collections.shuffle(spielerliste);
        return spielerliste;
    }

    @Override
    public String beendeSpiel(Spiel spiel) {
        return "Das Spiel wurde beendet!";
    }

    @Override
    public Spieler gibGewinneraus(Spiel spiel) {
        Map<Spieler, Integer> spielerpunkte = new HashMap<>();
        for (Spieler spieler : spiel.getSpielerListe()) {
              int i = spieler.getPunkte();
              spielerpunkte.put(spieler, i);
        }
        var x = spielerpunkte.entrySet().stream().max(Map.Entry.comparingByValue());
        return x.get().getKey();

    }

    @Override
    public Spiel erstelleSpiel(List<SpielerInterface> spielerListe, int runde, AblageStapel ablageStapel, ZiehStapel ziehStapel) {
        int anzahlSpieler = spielerListe.size();
        Spiel spiel = new Spiel(spielerListe, runde, ablageStapel, ziehStapel);

        switch(anzahlSpieler) {
            case 2 :
                List<Karte> kartenListe1 = new ArrayList<>();
                SpielerHand spielerHand1 = new SpielerHand(0, kartenListe1);
                spiel.getSpielerListe().get(0).setSpielerHand(spielerHand1);
                List<Karte> kartenListe2 = new ArrayList<>();
                SpielerHand spielerHand2 = new SpielerHand(0, kartenListe2);
                spiel.getSpielerListe().get(1).setSpielerHand(spielerHand2);
            break;
            case 3 :
                List<Karte> kartenListe3 = new ArrayList<>();
                SpielerHand spielerHand3 = new SpielerHand(0, kartenListe3);
                spiel.getSpielerListe().get(0).setSpielerHand(spielerHand3);
                List<Karte> kartenListe4 = new ArrayList<>();
                SpielerHand spielerHand4 = new SpielerHand(0, kartenListe4);
                spiel.getSpielerListe().get(1).setSpielerHand(spielerHand4);
                List<Karte> kartenListe5 = new ArrayList<>();
                SpielerHand spielerHand5 = new SpielerHand(0, kartenListe5);
                spiel.getSpielerListe().get(2).setSpielerHand(spielerHand5);
            break;
            case 4 :
                List<Karte> kartenListe6 = new ArrayList<>();
                SpielerHand spielerHand6 = new SpielerHand(0, kartenListe6);
                spiel.getSpielerListe().get(0).setSpielerHand(spielerHand6);
                List<Karte> kartenListe7 = new ArrayList<>();
                SpielerHand spielerHand7 = new SpielerHand(0, kartenListe7);
                spiel.getSpielerListe().get(1).setSpielerHand(spielerHand7);
                List<Karte> kartenListe8 = new ArrayList<>();
                SpielerHand spielerHand8 = new SpielerHand(0, kartenListe8);
                spiel.getSpielerListe().get(2).setSpielerHand(spielerHand8);
                List<Karte> kartenListe9 = new ArrayList<>();
                SpielerHand spielerHand9 = new SpielerHand(0, kartenListe9);
                spiel.getSpielerListe().get(3).setSpielerHand(spielerHand9);
            break;
        }
       // SpielImpl spielImpl = new SpielImpl(kartenSpiel = new KartenSpielImpl());

        List<Karte> kartenDeck = kartenSpiel.erstelleKarten(); // do return when hier mocken
        kartenSpiel.mischeKarten(kartenDeck);
        List<Karte> ziehKarten = new ArrayList<>();
        for (int i = 31; i >= anzahlSpieler * 5 + 1; i--) {
            ziehKarten.add(kartenDeck.get(i));
            kartenDeck.remove(kartenDeck.get(i));
        }

        spiel.getZiehStapel().setZiehkarten(ziehKarten);
        spiel.getAblageStapel().setAblagekarten(new ArrayList<>());
        spiel.getAblageStapel().getAblagekarten().add(kartenDeck.get(kartenDeck.size() - 1));
        kartenDeck.remove(kartenDeck.get(kartenDeck.size() - 1));
        int uebrigekarten = kartenDeck.size();
        int uebrigekartenIndex = uebrigekarten - 1;

        for (int l = anzahlSpieler - 1; l >= 0; l--) {
                for (int k = 5; k > 0; k--) {
                    spiel.getSpielerListe().get(l).getSpielerHand().getKarten().add(kartenDeck.get(uebrigekartenIndex));
                    spiel.getSpielerListe().get(l).getSpielerHand().setAnzahlKarten(spiel.getSpielerListe().get(l).getSpielerHand().getAnzahlKarten() + 1);
                    kartenDeck.remove(kartenDeck.get(uebrigekartenIndex));
                    uebrigekartenIndex = uebrigekartenIndex - 1;
                }
        }

        return spiel;
    }

}

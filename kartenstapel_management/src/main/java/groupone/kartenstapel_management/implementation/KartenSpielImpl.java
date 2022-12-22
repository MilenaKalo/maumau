package groupone.kartenstapel_management.implementation;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.kartenstapel_management.services.KartenSpielService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class KartenSpielImpl implements KartenSpielService {
    @Override
    public void legeErsteKarteAufAblagestapel(ZiehStapel ziehStapel, AblageStapel ablageStapel) {
        Karte karte = ziehStapel.getZiehkarten().get(ziehStapel.getZiehkarten().size()-1);
        ablageStapel.getAblagekarten().add(karte);
        ziehStapel.getZiehkarten().remove(karte);
        ziehStapel.setAnzahlKarten(ziehStapel.getAnzahlKarten() - 1);
        ablageStapel.setAnzahlKarten(ablageStapel.getAnzahlKarten() + 1);
    }

    @Override
    public List<Karte> mischeKarten(List<Karte> karteList) {
       Collections.shuffle(karteList);
       return karteList;
    }

    @Override
    public void kartenDesAblagestapelsDemZiehstapelUebergeben(AblageStapel ablageStapel, ZiehStapel ziehStapel) {
        var x = ablageStapel.getAblagekarten().size() -1; // oberste Karte des Ablagestapels
        Karte karte = ablageStapel.getAblagekarten().get(x);

        List<Karte> hilfsStapel = new ArrayList<>();
        for (int i = 0; i < ablageStapel.getAblagekarten().size(); i++) {
            hilfsStapel.add(ablageStapel.getAblagekarten().get(i));
        }

        ziehStapel.setZiehkarten(hilfsStapel); // bekommt alle Karten
        ziehStapel.getZiehkarten().remove(karte);// oberste Karte vom Ablagestapel entfernt

        ablageStapel.getAblagekarten().clear(); // Ablagestapel wird geleert
        ablageStapel.getAblagekarten().add(karte); //oberste Karte vom Ablagestapel wird auf den Ablagestapel gelegt

        ablageStapel.setAnzahlKarten(ablageStapel.getAblagekarten().size());
        ziehStapel.setAnzahlKarten(ziehStapel.getZiehkarten().size());
    }

    @Override
    public ZiehStapel erstelleZiehStapel(int anzahlKarten, List<Karte> ziehkarten) {
        ZiehStapel ziehStapel = new ZiehStapel(anzahlKarten, ziehkarten);
        return ziehStapel;
    }

    @Override
    public AblageStapel erstelleAblageStapel(int anzahlKarten, List<Karte> ablagekarten) {
        AblageStapel ablageStapel = new AblageStapel(anzahlKarten, ablagekarten);
        return ablageStapel;
    }

    @Override
    public SpielerHand erstelleSpielerHand(int anzahlKarten, List<Karte> spielerhandkarten) {
        SpielerHand spielerHand = new SpielerHand(anzahlKarten, spielerhandkarten);
        return spielerHand;
    }

    @Override
    public List<Karte> erstelleKarten(){
        List<Karte> kartenDeck = new ArrayList<>();

        String[] karteFarbe = {"Herz", "Karo", "Pik", "Kreuz"};
        for(int f = 0; f < karteFarbe.length; f++) {
            for(int i = 0; i < 8; i++) {
                String kartenWert;
                switch(i) {
                    case 0:
                        kartenWert = "7";
                        break;
                    case 1:
                        kartenWert = "8";
                        break;
                    case 2:
                        kartenWert = "9";
                        break;
                    case 3:
                        kartenWert = "10";
                        break;
                    case 4:
                        kartenWert = "Bube";
                        break;
                    case 5:
                        kartenWert = "Dame";
                        break;
                    case 6:
                        kartenWert = "König";
                        break;
                    case 7:
                        kartenWert = "Ass";
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + i);
                }
                Karte karte = new Karte(karteFarbe[f], kartenWert);
                kartenDeck.add(karte);
            }
        }

        /*
        for(int i = 0; i < 8; i++) {
            String kartenWert;
            switch(i) {
                case 0:
                    kartenWert = "7";
                    break;
                case 1:
                    kartenWert = "8";
                    break;
                case 2:
                    kartenWert = "9";
                    break;
                case 3:
                    kartenWert = "10";
                    break;
                case 4:
                    kartenWert = "Bube";
                    break;
                case 5:
                    kartenWert = "Dame";
                    break;
                case 6:
                    kartenWert = "König";
                    break;
                case 7:
                    kartenWert = "Ass";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + i);
            }
            Karte karte = new Karte("Herz", kartenWert);
            kartenDeck.add(karte);
        }

        for(int i = 0; i < 8; i++) {
            String kartenWert;
            switch(i) {
                case 0:
                    kartenWert = "7";
                    break;
                case 1:
                    kartenWert = "8";
                    break;
                case 2:
                    kartenWert = "9";
                    break;
                case 3:
                    kartenWert = "10";
                    break;
                case 4:
                    kartenWert = "Bube";
                    break;
                case 5:
                    kartenWert = "Dame";
                    break;
                case 6:
                    kartenWert = "König";
                    break;
                case 7:
                    kartenWert = "Ass";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + i);
            }
            Karte karte = new Karte("Karo", kartenWert);
            kartenDeck.add(karte);
        }

        for(int i = 0; i < 8; i++) {
            String kartenWert;
            switch(i) {
                case 0:
                    kartenWert = "7";
                    break;
                case 1:
                    kartenWert = "8";
                    break;
                case 2:
                    kartenWert = "9";
                    break;
                case 3:
                    kartenWert = "10";
                    break;
                case 4:
                    kartenWert = "Bube";
                    break;
                case 5:
                    kartenWert = "Dame";
                    break;
                case 6:
                    kartenWert = "König";
                    break;
                case 7:
                    kartenWert = "Ass";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + i);
            }
            Karte karte = new Karte("Pik", kartenWert);
            kartenDeck.add(karte);
        }

        for(int i = 0; i < 8; i++) {
            String kartenWert;
            switch(i) {
                case 0:
                    kartenWert = "7";
                    break;
                case 1:
                    kartenWert = "8";
                    break;
                case 2:
                    kartenWert = "9";
                    break;
                case 3:
                    kartenWert = "10";
                    break;
                case 4:
                    kartenWert = "Bube";
                    break;
                case 5:
                    kartenWert = "Dame";
                    break;
                case 6:
                    kartenWert = "König";
                    break;
                case 7:
                    kartenWert = "Ass";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + i);
            }
            Karte karte = new Karte("Kreuz", kartenWert);
            kartenDeck.add(karte);
        }
        */
        return kartenDeck;
    }

}

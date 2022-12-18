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
        int indexKarte = ziehStapel.getAnzahlKarten()-1;
        Karte karte = ziehStapel.getZiehkarten().get(indexKarte);
        ablageStapel.getAblagekarten().add(karte);
        ziehStapel.getZiehkarten().remove(indexKarte);
    }

    @Override
    public List<Karte> mischeKarten(List<Karte> karteList) {
       Collections.shuffle(karteList);
       return karteList;
    }

    @Override
    public void kartenDesAblagestapelsDemZiehstapelUebergeben(AblageStapel ablageStapel, ZiehStapel ziehStapel) {
        var x = ablageStapel.getAblagekarten().size(); // oberste Karte des Ablagestapels
        Karte karte = ablageStapel.getAblagekarten().get(x);

        ziehStapel.setZiehkarten(ablageStapel.getAblagekarten()); // bekommt alle Karten
        ziehStapel.getZiehkarten().remove(karte);// oberste Karte vom Ablagestapel entfernt

        ablageStapel.getAblagekarten().clear(); // Ablagestapel wird geleert
        ablageStapel.getAblagekarten().add(karte); //oberste Karte vom Ablagestapel wird auf den Ablagestapel gelegt
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

}

package groupone.spiel_management.services;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;

public interface KartenSpielerService {

    /**
     * Spieler können Karten ablegen
     * @param spielerhand Hand des Spielers, die eine Karte ablegen soll
     * @param ablagestapel der Stapel der die abgelegte Karte erhält
     */
    void legeKarteAb(SpielerHand spielerhand, Karte karte, AblageStapel ablagestapel);

    /**
     * Spieler muss Karte ziehen weil er keine passende Karte mehr hat.
     * @param spielerhand Hand des Spielers, die eine Karte aufnehmen muss
     * @param ziehStapel Stapel, von dem eine Karte entnommen werden soll
     */
    void zieheKarte(SpielerHand spielerhand, ZiehStapel ziehStapel);

}

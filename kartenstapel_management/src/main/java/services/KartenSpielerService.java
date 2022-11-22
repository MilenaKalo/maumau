package services;

import classes.Karte;
import classes.SpielerHand;
import classes.ZiehStapel;

public interface KartenSpielerService {

    /**
     *Spieler können Karten ablegen die die gleiche Farbe oder Wert haben
     * @param spielerhand Hand des Spielers, die eine Karte ablegen soll
     * @param karte die Karte die abgelegt wird
     */
    void legeKarteAb(SpielerHand spielerhand, Karte karte);

    /**
     * Spieler muss Karte ziehen weil er keine passende Karte mehr hat.
     * @param spielerhand Hand des Spielers, die eine Karte aufnehmen muss
     * @param ziehStapel Stapel, von dem eine Karte entnommen werden soll
     */
    void zieheKarte(SpielerHand spielerhand, ZiehStapel ziehStapel);

}

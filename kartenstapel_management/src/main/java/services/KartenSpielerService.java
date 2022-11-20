package services;

import classes.Karte;
import classes.SpielerHand;
import classes.ZiehStapel;

public interface KartenSpielerService {
    void legeKarteAb(SpielerHand spielerhand, Karte karte);

    void zieheKarte(SpielerHand spielerhand, ZiehStapel ziehStapel);
    /**
     *Spieler können Karten ablegen die die gleiche Farbe oder Wert haben
     * @param spiel Das Spiel wo die Karte von der Spielerhand in den Ablagestapel geht
     * @param spieler Spieler der die Karte ablegen muss
     * @param karte die Karte die abgelegt wird
     */
     // void legeKarteAb(SpielerHand spielerhand, Karte karte);

    /**
     * Spieler muss Karte ziehen weil er keine passende Karte mehr hat.
     * @param spiel Spiel wo die Karte vom Spieler vom Ziehstapel gezogen wird
     */
     // void zieheKarte(SpielerHand spielerhand, ZiehStapel ziehStapel);

}

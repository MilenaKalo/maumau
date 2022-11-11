package services;

import classes.Karte;

public interface KartenSpielerService {
    /**
     *Spieler können Karten ablegen die die gleiche Farbe oder Wert haben
     * @param spiel Das Spiel wo die Karte von der Spielerhand in den Ablagestapel geht
     * @param spieler Spieler der die Karte ablegen muss
     * @param karte die Karte die abgelegt wird
     */
     // void legeKarteAb(Spiel spiel, Spieler spieler, Karte karte);

    /**
     * Spieler muss Karte ziehen weil er keine passende Karte mehr hat.
     * @param spiel Spiel wo die Karte vom Spieler vom Ziehstapel gezogen wird
     */
     // void zieheKarte(Spiel spiel);

}

package services;

import classes.Spiel;
import classes.Spieler;

import java.util.List;

public interface SpielService {

    /**
     *Erstellt eine zufällige Reihenflge in der die Spieler zum Zug kommen
     * (Methode wird der Klasse Spiel zugeorndet)
     * @return  Liste der Reihenfolge der Spieler
     */
    List<Spieler> erstelleSpielerReihenfolge();

    /**
     * Jeder Spieler der am Spiel teilnimmt erhält 5 Karten am Anfang
     * (Methode der KLasse Spiel)
     * @param spiel Spiel wo die Karte am Anfang ausgegeben wird
     */
    void gebeKartenAusAmAnfang(Spiel spiel);

    /**
     * Nächster Spieler dran
     * @param spiel Spiel wo der nächste Spieler in der Liste genommen wird
     * @return der Spieler der nun an der Reihe ist
     */
     Spieler nächsterSpielerIstDran(Spiel spiel);

    /**
     * Spiel wird beendet
     * (Methode die in der KLasse Spiel angesiedelt wird)
     * @param spiel Spiel das beendet wird
     */
     void beendeSpiel(Spiel spiel);


}

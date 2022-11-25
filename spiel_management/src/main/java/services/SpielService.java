package services;

import classes.AblageStapel;
import classes.Spiel;
import classes.Spieler;
import classes.ZiehStapel;

import java.util.List;

public interface SpielService {

    /**
     *Erstellt eine zufällige Reihenfolge in der die Spieler zum Zug kommen
     * (Methode wird der Klasse Spiel zugeorndet)
     * @return  Liste der Reihenfolge der Spieler
     */
    List<Spieler> erstelleSpielerReihenfolge(List<Spieler> spielerliste);

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
     String beendeSpiel(Spiel spiel);

    /**
     * zeigt den Gewinner des Spiels an
     * @param spiel das Spiel wo der Gewinner ermittelt werden soll
     */
    Spieler gibGewinneraus(Spiel spiel);

    /**
     * gibt den Spieler zurück der als nächstes dran ist
     * @param spiel Spiel in dem die Spielerreihenfole geprüft wird
     * @return der Spieler der nun dran ist
     */
    Spieler aussetzen(Spiel spiel);

    /**
     * das Spiel wird erzeugt und die Karten werden verteilt
     * @param spielerListe Liste mit Spielern die Karten erhalten
     * @param runde die Runde "0" wird gesetzt
     * @param ablageStapel der Ablagestapel erhält Karten
     * @param ziehStapel Ziehstapel der Karten erhält
     * @return das vorbereitete Spiel
     */
    Spiel erstelleSpiel(List<Spieler> spielerListe, int runde, AblageStapel ablageStapel, ZiehStapel ziehStapel);
}

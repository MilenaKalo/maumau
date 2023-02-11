package groupone.spiel_management.services;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.spiel_management.classes.Spiel;
import groupone.spieler_management.classes.Spieler;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.spieler_management.classes.SpielerInterface;

import java.util.List;

public interface SpielService {

    /**
     *Erstellt eine zufällige Reihenfolge in der die Spieler zum Zug kommen
     * (Methode wird der Klasse Spiel zugeorndet)
     * @param spielerliste Liste der Spieler, die an dem Spiel teilnehmen
     * @return  Liste der Reihenfolge der Spieler
     */
    List<SpielerInterface> erstelleSpielerReihenfolge(List<SpielerInterface> spielerliste);


    /**
     * Spiel wird beendet
     * @param spiel Spiel das beendet wird
     * @return String mit der Meldung, dass das Spiel beendet wurde
     */
     String beendeSpiel(Spiel spiel);

    /**
     * zeigt den Gewinner des Spiels an
     * @param spiel das Spiel wo der Gewinner ermittelt werden soll
     * @return Spieler der das Spiel gewonnen hat
     */
    Spieler gibGewinneraus(Spiel spiel);


    /**
     * das Spiel wird erzeugt und die Karten werden verteilt
     * @param spielerListe Liste mit Spielern die Karten erhalten
     * @param runde die Runde "0" wird gesetzt
     * @param ablageStapel der Ablagestapel erhält Karten
     * @param ziehStapel Ziehstapel der Karten erhält
     * @return das vorbereitete Spiel
     */
    Spiel erstelleSpiel(List<SpielerInterface> spielerListe, int runde, AblageStapel ablageStapel, ZiehStapel ziehStapel);
}

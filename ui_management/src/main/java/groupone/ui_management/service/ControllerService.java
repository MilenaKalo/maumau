package groupone.ui_management.service;

import groupone.spiel_management.classes.Spiel;
import groupone.spieler_management.classes.Spieler;

public interface ControllerService {


    /**
     * Die Methode startet das Hauptmenü des Spiels und verarbeitet die Eingaben des Nutzers.
     * Je nachdem was der Nutzer eingibt, wird eine neue View-Methode ausgeführt.
     */
    void hauptMenue();

    /**
     * Methode läd ein existierendes Spiel aus der Datenbank oder erstellt ein neues Spiel
     */
    void starteSpiel();

    /**
     * Methode die die oberste Karte des Ablagestapels zeigt
     *
     * @param spiel - Spiel das den Ablagestapel liefert
     */
    private void anzeigeObersteKarte(Spiel spiel) {

    }

    /**
     * fragt nach welcher Regel gespielt werden soll
     */
    private void frageNachSpielregeln() {

    }

    /**
     * Methode gibt Spieler Gelegenheit, "Mau" oder "MauMau" zu sagen
     *
     * @param spieler Spieler, der "Mau" oder "MauMau" sagen möchte
     */
    private void spielerHatWasGesagt(Spieler spieler) {

    }

    /**
     * Methode die die Karten der Spielerhand des Spielers anzeigt
     *
     * @param spieler - Spieler dessen Hand angezeigt werden soll
     */
    private void zeigeKartenSpielerHand(Spieler spieler) {

    }

    /**
     * Methode die eine Karte zur SpielerHand inzufügt und die Karte vom ZiehStapel entfernt
     */
    private void zieheKarte(Spieler spieler) {

    }

    /**
     * Methode die prüft eine Fehlermeldung zur falschen Karte gibt
     *
     * @param spiel, in welchem man sich gerade befindet
     */
    private void prüfeKarte(Spiel spiel) {

    }


}

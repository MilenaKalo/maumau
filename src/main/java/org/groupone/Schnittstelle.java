package org.groupone;

import java.util.List;

public interface Schnittstelle {

    /**
     *Erstellt eine zufällige Reihenflge in der die Spieler zum Zug kommen
     * (Methode wird der Klasse Spiel zugeorndet)
     * @return  Liste der Reihenfolge der Spieler
     */
    List<Spieler> erstelleSpielerReihenfolge();

    /**
     * Jeder Spieler der am Spiel teilnimmt erhält 5 Karten am Anfang
     * (Methode der KLasse Spiel)
     */
    void gebeKartenAusAmAnfang();

    /**
     * Oberste Karte des Ziehstapels wird geöffnet
     * (Methode der KLasse Spiel)
     */
    void ersteKarteAufBrett();

    /**
     *Spieler können Karten ablegen die die gleiche Farbe oder Wert haben
     * @param karte die Karte die abgelegt wird
     */
    void legeKarteAb(Karte karte);

    /**
     * Spieler muss Karte ziehen weil er keine passende Karte mehr hat.
     * Es wurde eine 7 gelegt: nächster Spieler der keine passende Karte hat muss 2 ziehen.
     * Wird auf eine gelegte Sieben noch einmal 7 gelegt muss der Spieler der keine
     * passende Karte hat 4 Karten ziehen.
     * @param spieler der Spieler der in seiner Spielerhand eine Karte hinzugefügt bekommt
     * @param ziehStapel der Stapel an Karten wo eine Karte gezogen wird
     */
    void zieheKarte(Spieler spieler, ZiehStapel ziehStapel);

    /**
     * Nächster Spieler dran
     * @param spiel Spiel wo der nächste Spieler in der Liste genommen wird
     * @return der Spieler der nun an der Reihe ist
     */
     Spieler nächsterSpielerIstDran(Spiel spiel);

    /**
     * bei der vorletzten Karte muss Mau gesagt werden
     */
    void sageMau();

    /**
     * Der Spieler hat vergessen Mau zu sagen und muss jetzt 2 Strafkarten ziehen
     * @param spieler Spieler der zwei Karten ziehen muss
     * @param ziehStapel Stapel aus dem 2 Karten gezogen werden
     */
     void mauStrafe(Spieler spieler, ZiehStapel ziehStapel);

    /**
     * Die letzte Karte wurd abgelegt und der Spieler muss MauMau sagen.
     */
    void maumau();

    /**
     * Karten werden gemischt
     * @param karteList Liste aus Karten die gemischt werden sollen
     * @return die fertig gemischte Kartenliste
     */
     List<Karte> mischeKarten(List<Karte> karteList );

    /**
     * Spiel wird beendet
     * (Methode die in der KLasse Spiel angesiedelt wird)
     */
     void beendeSpiel();

    /**
     * Spieler bekommt einen Punkt dafür dass er gewonnen hat dazu.
     * (Methode die der Klasse Spieler gehört)
     */
     void erhöhePunkt();

    /**
     * zeigt den Gewinner des Spiels an
     * @param spieler der Spieler der gewonnen hat
     */
     void gibGewinneraus(Spieler spieler);
}

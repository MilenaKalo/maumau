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
     * @param spiel Spiel wo die Karte am Anfang ausgegeben wird
     */
    void gebeKartenAusAmAnfang(Spiel spiel);

    /**
     * Oberste Karte des Ziehstapels wird geöffnet
     * (Methode der KLasse Spiel)
     * @param spiel Spiel wo die obersteKarte aufgedeckt wird
     */
    void ersteKarteAufBrett(Spiel spiel);

    /**
     *Spieler können Karten ablegen die die gleiche Farbe oder Wert haben
     * @param spiel Das Spiel wo die Karte von der Spielerhand in den Ablagestapel geht
     * @param spieler Spieler der die Karte ablegen muss
     * @param karte die Karte die abgelegt wird
     */
    void legeKarteAb(Spiel spiel,Spieler spieler,Karte karte);

    /**
     * Spieler muss Karte ziehen weil er keine passende Karte mehr hat.
     * Es wurde eine 7 gelegt: nächster Spieler der keine passende Karte hat muss 2 ziehen.
     * Wird auf eine gelegte Sieben noch einmal 7 gelegt muss der Spieler der keine
     * passende Karte hat 4 Karten ziehen.
     * @param spiel Spiel wo die Karte vom Spieler vom Ziehstapel gezogen wird
     */
    void zieheKarte(Spiel spiel);

    /**
     * Nächster Spieler dran
     * @param spiel Spiel wo der nächste Spieler in der Liste genommen wird
     * @return der Spieler der nun an der Reihe ist
     */
     Spieler nächsterSpielerIstDran(Spiel spiel);

    /**
     * bei der vorletzten Karte muss Mau gesagt werden
     * @param spieler Spieler der mau sagt
     */
    void sageMau(Spieler spieler);

    /**
     * Der Spieler hat vergessen Mau zu sagen und muss jetzt 2 Strafkarten ziehen
     * @param spieler Spieler der zwei Karten ziehen muss
     * @param ziehStapel Stapel aus dem 2 Karten gezogen werden
     */
     void mauStrafe(Spieler spieler, ZiehStapel ziehStapel);

    /**
     * Die letzte Karte wurd abgelegt und der Spieler muss MauMau sagen.
     * @param spieler Spieler der  maumau sagt
     */
    void maumau(Spieler spieler);

    /**
     * Karten werden gemischt
     * @param karteList Liste aus Karten die gemischt werden sollen
     * @return die fertig gemischte Kartenliste
     */
     List<Karte> mischeKarten(List<Karte> karteList );

    /**
     * Spiel wird beendet
     * (Methode die in der KLasse Spiel angesiedelt wird)
     * @param spiel Spiel das beendet wird
     */
     void beendeSpiel(Spiel spiel);

    /**
     * Spieler bekommt einen Punkt dafür dass er gewonnen hat dazu.
     * (Methode die der Klasse Spieler gehört)
     * @param spieler Spieler der einen Pnkt bekommt
     */
     void erhöhePunkt(Spieler spieler);

    /**
     * zeigt den Gewinner des Spiels an
     * @param spieler der Spieler der gewonnen hat
     */
     void gibGewinneraus(Spieler spieler);

    /**
     * Prüft ob die Karte gelegt werden kann
     * @param karte die Karte die in der Spielerhand liegt
     * @param ablageStapel der Stapel wo die Karte draufgelegt werden soll
     * @return true wenn die Karte abgelegt werden kann
     */
     boolean prüfeKarte(Karte karte, AblageStapel ablageStapel);

     /**
      * das Spiel wird erzeugt
      * bereitet das spiel vor und mischt die karten und verteilt
      * @param ablageStapel
      * @param ziehStapel
      * @param spieler
      */
     void spielErzeugt(AblageStapel ablageStapel, ZiehStapel ziehStapel, Spieler spieler);

    /**
     * Eine einzelne Karte wird mit dieser Methode erzeugt
     * @param kartenFarben -> Die Farben der Karten
     * @param kartenWerte -> Der Werte der Karten
     * @return karte -> Die erzeugte Karte
     */
    Karte karteErzeugt(List<String> kartenFarben, List<String> kartenWerte);

    /**
     * Setzt das Mau-Attribut auf false zurück
     */
    void mauZueruecksetzen();

    /**
     * prüft ob es eine Wunschfarbe bei dem Spiel existiert
     * @param spiel das Spiel in dem geprüft werden soll
     */
    void prüfeWunschfarbe(Spiel spiel);
}

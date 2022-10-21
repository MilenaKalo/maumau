package org.groupone;

public interface Schnittstelle {

    /**
     *Erstelle Spiel
     */
     void erstelleSpiel();

    /**
     *Erstelle Spielerreihenfolge
     */
    void erstelleSpielerReihenfolge();

    /**
     *Jeder Spieler erhält 7 Karten am Anfang
     */
    void gebeKartenAusAmAnfang();

    /**
     *Oberste Karte Ziehstapel geöffnet
     */
    void ersteKarteAufBrett();

    /**
     *Spieler können Karten ablegen gleiche Farbe oder Wert
     */
    public void legeKarteab();

    /**
     * Spieler muss Karte ziehen weil keine passende
     */
    void zieheKarte();

    /**
     * Nächster Spieler dran
     */
     void nächsterSpielerIstDran();
    /**
     * Vorletzte Karte Mau sagen
     */
    void sageMau();

    /**
     * Mau vergessen dann 2 Strafkarten ziehen
     */
     void mauStrafe();

    /**
     * Letzte Karte abgelegt Mau mau sagen
     */
    void maumau();

    /**
     * 7 gelegt: nächster Spieler muss 2 ziehen
     * Wieder auf 7 7 gelegt nächste Spieler muss 4 ziehen
     */
     void siebenGelegt();

    /**
     * Bube: Farbwunsch
     */
    void bubeGelegt();

    /**
     * Ablagestapel mischt Karten und gibt an Deck ab
     */
     void gibAnDeckZurück();

    /**
     * Spiel wird beendet
     */
     void beendeSpiel();

    /**
     * addiere Punkte auf
     */
     void punkte();

    /**
     * zeigt den Gewinner
     */
     void gibGewinneraus();
}

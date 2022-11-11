package services;

public interface SpielregelnService {
    /**   Es wurde eine 7 gelegt: nächster Spieler der keine passende Karte hat muss 2 ziehen.
     * Wird auf eine gelegte Sieben noch einmal 7 gelegt muss der Spieler der keine
     * passende Karte hat 4 Karten ziehen. */
    void siebenGelegt();


    /**
     * Der Spieler hat vergessen Mau zu sagen und muss jetzt 2 Strafkarten ziehen
     * @param spieler Spieler der zwei Karten ziehen muss
     * @param ziehStapel Stapel aus dem 2 Karten gezogen werden
     */
    //  void mauStrafe(Spieler spieler, ZiehStapel ziehStapel);

    /**
     * prüft ob es eine Wunschfarbe bei dem Spiel existiert
     *  das Spiel in dem geprüft werden soll
     */
    //  void prüfeWunschfarbe(Spiel spiel);

}

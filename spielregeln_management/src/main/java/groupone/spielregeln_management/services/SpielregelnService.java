package groupone.spielregeln_management.services;


import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.spiel_management.classes.Spiel;
import groupone.spieler_management.classes.Spieler;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;

public interface SpielregelnService {

    /**   Es wurde eine 7 gelegt: nächster Spieler der keine passende Karte hat muss 2 ziehen.
     * Wird auf eine gelegte Sieben noch einmal 7 gelegt muss der Spieler der keine
     * passende Karte hat 4 Karten ziehen. */
    void siebenGelegt(SpielerHand spielerhand, ZiehStapel ziehStapel);

    /**
     * Der Spieler hat vergessen Mau zu sagen und muss jetzt 2 Strafkarten ziehen
     * @param spieler Spieler der zwei Karten ziehen muss
     * @param ziehStapel Stapel aus dem 2 Karten gezogen werden
     */
     void mauStrafe(Spieler spieler, ZiehStapel ziehStapel);

    /**
     * Nächster Spieler dran
     * @param spiel Spiel wo der nächste Spieler in der Liste genommen wird
     * @return der Spieler der nun an der Reihe ist
     */
    Spieler nächsterSpielerIstDran(Spiel spiel);

    /**
     * gibt den Spieler zurück der als nächstes dran ist bei Kartenwert 8
     * @param spiel Spiel in dem die Spielerreihenfole geprüft wird
     * @return der Spieler der nun dran ist
     */
    Spieler aussetzen(Spiel spiel);

    /**
     * gibt dem Spieler zurück, ob eine Karte gelget werden kann.
     * @param karte Karte, die der Spieler legen möchte
     * @param ablageStapel Der Ablagestapel, auf welchen die Karte gelegt werden soll
     * @return Der Wahrheitswert, ob die Karte gelegt werden kann
     */
    boolean pruefeKarte(Karte karte, AblageStapel ablageStapel);

    /**
     * prüft ob die letzte Karte auf dem Ablagestapel ein Bube ist und so eine Farbwahl noetig ist
     *
     * @param ablageStapel - um die letze Karte zu prüfen
     * @return true bei Farbwunsch und false bei keinem Farbwunsch
     */
    boolean mussSichFarbeWuenschen(AblageStapel ablageStapel);


    /**
     *  prüft den Richtungswechsel bei Kartenwert 9
     * @param ablageStapel - um die letze Karte zu prüfen
     * @return true bei Richtungswechsel und false bei keinem Richtungswechsel
     */
    boolean richtungWechsel(AblageStapel ablageStapel);

    /**
     * prüft ob der jetztige Spieler ein Ass gelegt hat und somit nochmal legen darf
     * @param ablageStapel
     * @return true wenn er nochmal legen darf und false wenn nicht
     */
    boolean assGelegt(AblageStapel ablageStapel);


}

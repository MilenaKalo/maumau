package services;

import classes.Karte;
import classes.Spieler;

public interface SpielerService {

    /**
     * Spieler bekommt einen Punkt dafür dass er gewonnen hat dazu.
     * (Methode die der Klasse Spieler gehört)
     * @param spieler Spieler der einen Pnkt bekommt
     */
    void erhöhePunkt(Spieler spieler);

    /**
     * Die letzte Karte wurd abgelegt und der Spieler muss MauMau sagen.
     * @param spieler Spieler der  maumau sagt
     */
    void maumau(Spieler spieler);

    /**
     * bei der vorletzten Karte muss Mau gesagt werden
     * @param spieler Spieler der mau sagt
     */
    void sageMau(Spieler spieler);

    /**
     * Setzt das Mau-Attribut auf false zurück
     * @param spieler Spieler der sein Mau verspielt hat
     */
    void mauZuruecksetzen(Spieler spieler);

}

package groupone.spieler_management.services;

import groupone.spieler_management.classes.VirtuellerSpieler;

public interface VirtuellerSpielerService {

    /**
     * Bei der vorletzten Karte muss Mau gesagt werden.
     * @param spieler Spieler der mau sagt
     */
    void sageMau(VirtuellerSpieler spieler);

    /**
     * Setzt das Mau-Attribut auf false zurück.
     * @param spieler Spieler, bei dem das Mau-Attribut zurückgesetzt wird
     */
    void mauZuruecksetzen(VirtuellerSpieler spieler);

    /**
     * Die letzte Karte wurde abgelegt und der Spieler muss MauMau sagen.
     * @param spieler Spieler der maumau sagt
     */
    void maumau(VirtuellerSpieler spieler);

    /**
     * Spieler bekommt einen Punkt dafür dass er gewonnen hat dazu.
     * (Methode die der Klasse Spieler gehört)
     * @param spieler Spieler der einen Pnkt bekommt
     */
    void erhöhePunkt(VirtuellerSpieler spieler);

    /**
     * Ein Spieler wird erstellt.
     */
    VirtuellerSpieler spielerErstellen();

}

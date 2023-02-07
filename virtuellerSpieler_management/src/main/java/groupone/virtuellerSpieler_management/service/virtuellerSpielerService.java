package groupone.virtuellerSpieler_management.service;

import groupone.virtuellerSpieler_management.classes.virtuellerSpieler;

public interface virtuellerSpielerService {


    /**
     * Spieler bekommt einen Punkt dafür dass er gewonnen hat dazu.
     * (Methode die der Klasse Spieler gehört)
     * @param spieler Spieler der einen Pnkt bekommt
     */
    void erhöhePunkt(virtuellerSpieler spieler);

    /**
     * Die letzte Karte wurd abgelegt und der Spieler muss MauMau sagen.
     * @param spieler Spieler der  maumau sagt
     */
    void maumau(virtuellerSpieler spieler);

    /**
     * bei der vorletzten Karte muss Mau gesagt werden
     * @param spieler Spieler der mau sagt
     */
    void sageMau(virtuellerSpieler spieler);

    /**
     * Setzt das Mau-Attribut auf false zurück
     * @param spieler Spieler der sein Mau verspielt hat
     */
    void mauZuruecksetzen(virtuellerSpieler spieler);

    /**
     * Spieler wird erstellt
     * @param id Id des Spielers
     * @param name Name des Spielers
     * @param punkte Punkte des Spielers
     *
     * @return Spieler der erstellt wurde
     */
    virtuellerSpieler spielerErstellen(long id, String name, int punkte);

}

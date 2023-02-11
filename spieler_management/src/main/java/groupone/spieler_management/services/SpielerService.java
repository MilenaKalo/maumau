package groupone.spieler_management.services;

import groupone.spieler_management.classes.Spieler;

public interface SpielerService {

    /**
     * Bei der vorletzten Karte muss Mau gesagt werden.
     * @param spieler Spieler der mau sagt
     */
    void sageMau(Spieler spieler);

    /**
     * Setzt das Mau-Attribut auf false zurück.
     * @param spieler Spieler, bei dem das Mau-Attribut zurückgesetzt wird
     */
    void mauZuruecksetzen(Spieler spieler);

    /**
     * Die letzte Karte wurde abgelegt und der Spieler muss MauMau sagen.
     * @param spieler Spieler der maumau sagt
     */
    void maumau(Spieler spieler);

    /**
     * Spieler bekommt einen Punkt dafür dass er gewonnen hat dazu.
     * (Methode die der Klasse Spieler gehört)
     * @param spieler Spieler der einen Pnkt bekommt
     */
    void erhöhePunkt(Spieler spieler);

    /**
     * Ein Spieler wird erstellt.
     * @param id Die id des Spielers
     * @param name Der Name des Spielers
     * @return spieler der Spieler der erstellt wurde
     */
    Spieler spielerErstellen(long id, String name);

}

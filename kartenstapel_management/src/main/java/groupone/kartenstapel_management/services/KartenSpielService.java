package groupone.kartenstapel_management.services;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;

import java.util.List;

public interface KartenSpielService {

    /**
     * Oberste Karte des Ziehstapels wird geöffnet
     * @param ziehStapel Stapel, von dem die oberste Karte entommen wird
     * @param ablageStapel Stapel, auf den die oberste Karte vom Ziehstapel abgelegt wird
     */
    void legeErsteKarteAufAblagestapel(ZiehStapel ziehStapel, AblageStapel ablageStapel);

    /**
     * Karten werden gemischt
     * @param karteList Liste aus Karten die gemischt werden sollen
     * @return die fertig gemischte Kartenliste
     */
      List<Karte> mischeKarten(List<Karte> karteList);

    /**
     * Wenn im Ziehstapel keine Karten mehr enthalten sind, so werden alle Karten aus dem Ablagestapel
     * entnommen und dem Ziehstapel übergeben
     * @param ablageStapel Stapel, dessen Karten entnommen werden
     * @param ziehStapel Stapel, der die Karten übergeben bekommt
     */
    void kartenDesAblagestapelsDemZiehstapelUebergeben(AblageStapel ablageStapel, ZiehStapel ziehStapel);

    /**
     * erstellt einen neuen Ziehstapel
     * @param anzahlKarten Anzahl der Karten die im Ziehstapel enthalten sein sollen
     * @param ziehkarten Liste aus Karten die im Ziehstapel enthalten sein sollen
     * @return der neue Ziehstapel
     */
    ZiehStapel erstelleZiehStapel(int anzahlKarten, List<Karte> ziehkarten);

    /**
     * erstellt einen neuen Ablagestapel
     * @param anzahlKarten Anzahl der Karten die im Ablagestapel enthalten sein sollen
     * @param ablagekarten Liste aus Karten die im Ablagestapel enthalten sein sollen
     * @return der neue Ablagestapel
     */
    AblageStapel erstelleAblageStapel(int anzahlKarten, List<Karte> ablagekarten);

    /**
     * erstellt einen neue Spielerhand
     * @param anzahlKarten Anzahl der Karten die in der Spielerhand enthalten sein sollen
     * @param spielerhandkarten Liste aus Karten die in der Spielerhand enthalten sein sollen
     * @return der neue Spielerhand
     */
    SpielerHand erstelleSpielerHand(int anzahlKarten, List<Karte> spielerhandkarten);

    /**
     * erstellt das Kartendeck (insgesamt 32)
     * @return Das erstellte Kartendeck
     */
    List<Karte> erstelleKarten();

}

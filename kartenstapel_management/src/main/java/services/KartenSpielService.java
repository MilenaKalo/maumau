package services;

import classes.AblageStapel;
import classes.Karte;
import classes.ZiehStapel;

import java.util.List;

public interface KartenSpielService {

    /**
     * Oberste Karte des Ziehstapels wird geöffnet
     * (Methode der KLasse Spiel)
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

}
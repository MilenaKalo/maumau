package services;

import classes.AblageStapel;
import classes.Karte;

public interface KartenSpielregelnService {
    /**
     * Prüft ob die Karte gelegt werden kann
     * @param karte die Karte die in der Spielerhand liegt
     * @param ablageStapel der Stapel wo die Karte draufgelegt werden soll
     * @return true wenn die Karte abgelegt werden kann
     */
      boolean prüfeKarte(Karte karte, AblageStapel ablageStapel);

}

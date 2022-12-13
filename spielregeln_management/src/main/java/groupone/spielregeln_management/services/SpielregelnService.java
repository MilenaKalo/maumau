package groupone.spielregeln_management.services;

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

}

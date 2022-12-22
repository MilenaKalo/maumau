package groupone.spiel_management.implementation;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;

import groupone.spiel_management.services.KartenSpielerService;

public class KartenSpielerImpl implements KartenSpielerService {
    @Override
    public void legeKarteAb(SpielerHand spielerhand, Karte karte, AblageStapel ablagestapel) {
        spielerhand.getKarten().remove(karte);
        ablagestapel.getAblagekarten().add(karte);
        spielerhand.setAnzahlKarten(spielerhand.getAnzahlKarten() - 1);
        ablagestapel.setAnzahlKarten(ablagestapel.getAnzahlKarten() + 1);
        if(karte.getKartenWert().equals("Bube")) {
            ablagestapel.setWunschFarbe(karte.getKartenFarbe());
        }
    }

    @Override
    public void zieheKarte(SpielerHand spielerhand, ZiehStapel ziehStapel) {
        Karte karte = ziehStapel.getZiehkarten().get(ziehStapel.getZiehkarten().size()-1);
        spielerhand.getKarten().add(karte);
        ziehStapel.getZiehkarten().remove(karte);
        spielerhand.setAnzahlKarten(spielerhand.getAnzahlKarten() + 1);
        ziehStapel.setAnzahlKarten(ziehStapel.getAnzahlKarten() - 1);
    }
}

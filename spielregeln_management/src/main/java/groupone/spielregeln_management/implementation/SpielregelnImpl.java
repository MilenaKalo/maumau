package groupone.spielregeln_management.implementation;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.spiel_management.classes.Spiel;
import groupone.spieler_management.classes.Spieler;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.spieler_management.classes.SpielerInterface;
import groupone.spielregeln_management.services.SpielregelnService;

public class SpielregelnImpl implements SpielregelnService {

    public SpielregelnImpl() {

    }

    @Override
    public void mauStrafe(Spieler spieler, ZiehStapel ziehStapel) {
        //Sonderregel

    }
    public SpielerInterface aussetzen(Spiel spiel) {
        return null; //Sonderregel
    }


    @Override
    public boolean pruefeKarte(Karte karte, AblageStapel ablageStapel) {
        int letzteKarteIndex = ablageStapel.getAblagekarten().size() - 1;
        Karte letzteKarte = ablageStapel.getAblagekarten().get(letzteKarteIndex);
        if(letzteKarte.getKartenWert().equalsIgnoreCase(karte.getKartenWert()) || letzteKarte.getKartenFarbe().equalsIgnoreCase(karte.getKartenFarbe())) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean mussSichFarbeWuenschen(AblageStapel ablageStapel) {
        return false; //Sonderregel
    }

    @Override
    public boolean richtungWechsel(AblageStapel ablageStapel) {
        return false; //Sonderregel
    }

    @Override
    public boolean assGelegt(AblageStapel ablageStapel) {
        return false; // Sonderregel
    }

    @Override
    public boolean prüfeAufSiebenGelegt(Spiel spiel) {
        return false; //Sonderregel
    }

    @Override
    public SpielerInterface nächsterSpielerIstDran(Spiel spiel) {
        SpielerInterface aktiverSpieler = spiel.getAktiverSpieler();
        int aktiverSpielerIndex = spiel.getSpielerListe().indexOf(aktiverSpieler);

        int naechsterSpielerIndex;

    if (aktiverSpielerIndex + 1 > spiel.getSpielerListe().size() -1) {
        naechsterSpielerIndex = aktiverSpielerIndex+1-spiel.getSpielerListe().size();

        // nächster Spieler ist dran (Fall: nicht am Ende der Spielerliste)
    } else {
        naechsterSpielerIndex = aktiverSpielerIndex+1;

    }
        spiel.setAktiverSpieler(spiel.getSpielerListe().get(naechsterSpielerIndex));

        return spiel.getAktiverSpieler();
    }
}

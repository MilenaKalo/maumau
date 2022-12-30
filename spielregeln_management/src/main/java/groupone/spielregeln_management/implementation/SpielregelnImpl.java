package groupone.spielregeln_management.implementation;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.spiel_management.classes.Spiel;
import groupone.spieler_management.classes.Spieler;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.spielregeln_management.services.SpielregelnService;

public class SpielregelnImpl implements SpielregelnService {

    @Override
    public void siebenGelegt(Spiel spiel) {
    //Sonderregel
    }

    @Override
    public void mauStrafe(Spieler spieler, ZiehStapel ziehStapel) {
        //Sonderregel

    }
    public Spieler aussetzen(Spiel spiel) {
        return null; //Sonderregel
    }


    @Override
    public boolean pruefeKarte(Karte karte, AblageStapel ablageStapel) {
        int letzteKarteIndex = ablageStapel.getAblagekarten().size() - 1;
        Karte letzteKarte = ablageStapel.getAblagekarten().get(letzteKarteIndex);
        if(letzteKarte.getKartenFarbe().equals(ablageStapel.getWunschFarbe()) || letzteKarte.getKartenWert().equals(karte.getKartenWert()) || letzteKarte.getKartenFarbe().equals(karte.getKartenFarbe())) {
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
    public Spieler nächsterSpielerIstDran(Spiel spiel) {
        Spieler aktiverSpieler = spiel.getAktiverSpieler();
        int aktiverSpielerIndex = spiel.getSpielerListe().indexOf(aktiverSpieler);
        System.out.println(aktiverSpielerIndex);
        int naechsterSpielerIndex;
        if (aktiverSpielerIndex+1 > spiel.getSpielerListe().size()) {
            naechsterSpielerIndex = aktiverSpielerIndex+1-spiel.getSpielerListe().size();
        } else {
            naechsterSpielerIndex = aktiverSpielerIndex+1;
        }
        System.out.println(naechsterSpielerIndex);
        spiel.setAktiverSpieler(spiel.getSpielerListe().get(naechsterSpielerIndex));
        return spiel.getAktiverSpieler();
    }
}

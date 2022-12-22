package groupone.spielregeln_management.implementation;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.spiel_management.classes.Spiel;
import groupone.spieler_management.classes.Spieler;
import groupone.spielregeln_management.services.SpielregelnService;

public class SonderregelnImpl implements SpielregelnService {

    @Override
    public void siebenGelegt(SpielerHand spielerhand, ZiehStapel ziehStapel) {
    //muss noch gemacht werden
    }

    @Override
    public boolean assGelegt(AblageStapel ablageStapel) {
        int anzahl = ablageStapel.getAblagekarten().size();
        Karte karte = ablageStapel.getAblagekarten().get(anzahl);
        if (karte.getKartenWert().equals("Ass")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void mauStrafe(Spieler spieler, ZiehStapel ziehStapel) {
        Karte karte = ziehStapel.getZiehkarten().get(ziehStapel.getZiehkarten().size());
        spieler.getSpielerHand().getKarten().add(karte);
    }

    @Override
    public Spieler nächsterSpielerIstDran(Spiel spiel) {
        Spieler aktiverSpieler = spiel.getAktiverSpieler();
        int aktiverSpielerIndex = spiel.getSpielerListe().indexOf(aktiverSpieler);
        int naechsterSpielerIndex;
        if (aktiverSpielerIndex+1 > spiel.getSpielerListe().size()) {
            naechsterSpielerIndex = aktiverSpielerIndex+1-spiel.getSpielerListe().size();
        } else {
            naechsterSpielerIndex = aktiverSpielerIndex+1;
        }
        return spiel.getSpielerListe().get(naechsterSpielerIndex);
    }

    @Override
    public Spieler aussetzen(Spiel spiel) {
       AblageStapel ablageStapel =  spiel.getAblageStapel();
       int anzahl = ablageStapel.getAblagekarten().size();
       Karte karte = ablageStapel.getAblagekarten().get(anzahl);
       if (karte.getKartenWert().equals("8")) {
           Spieler aktiverSpieler = spiel.getAktiverSpieler();
           int aktiverSpielerIndex =spiel.getSpielerListe().indexOf(aktiverSpieler);
           int naechsterSpielerIndex;
           if (aktiverSpielerIndex+2 > spiel.getSpielerListe().size()) {
               naechsterSpielerIndex = aktiverSpielerIndex+2-spiel.getSpielerListe().size();
           } else {
               naechsterSpielerIndex = aktiverSpielerIndex+2;
           }
           return spiel.getSpielerListe().get(naechsterSpielerIndex);
       } else {
           return nächsterSpielerIstDran(spiel);
       }
    }

    @Override
    public boolean pruefeKarte(Karte karte, AblageStapel ablageStapel) {
        int anzahl = ablageStapel.getAblagekarten().size();
        Karte karte2 = ablageStapel.getAblagekarten().get(anzahl);
        if(karte2.getKartenWert().equals(karte.getKartenWert()) || karte2.getKartenFarbe().equals(karte.getKartenFarbe())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean mussSichFarbeWuenschen(AblageStapel ablageStapel) {
        int anzahl = ablageStapel.getAblagekarten().size();
        Karte karte = ablageStapel.getAblagekarten().get(anzahl);
        if (karte.getKartenWert().equals("Bube")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean richtungWechsel(AblageStapel ablageStapel) {
        int anzahl = ablageStapel.getAblagekarten().size();
        Karte karte = ablageStapel.getAblagekarten().get(anzahl);
        if (karte.getKartenWert().equals("9")) {
            return true;
        } else {
            return false;
        }
    }
}

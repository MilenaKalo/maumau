package groupone.spielregeln_management.implementation;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.spiel_management.classes.Spiel;
import groupone.spieler_management.classes.Spieler;
import groupone.spielregeln_management.services.SpielregelnService;

import java.util.List;

public class SonderregelnImpl implements SpielregelnService {

    @Override
    public void siebenGelegt(Spiel spiel) {

    //prüfen ob der Ablagestapel eine 7 enthält
       AblageStapel ablageStapel = spiel.getAblageStapel();
       int anzahl = ablageStapel.getAblagekarten().size()-1;
       Karte karte = ablageStapel.getAblagekarten().get(anzahl);
    //wenn er eine 7 hat
       if(karte.getKartenWert().equals("Sieben")){
           System.out.println("Sieben gelegt");
              //wenn der Spieler eine 7 hat
              Spieler aktiverSpieler = spiel.getAktiverSpieler();
              List<Karte> kartenListe= aktiverSpieler.getSpielerHand().getKarten();
              for(int i = 0; i<kartenListe.size(); i++) {
                  //wenn der Spieler eine 7 hat, muss er diese legen
                  if (kartenListe.get(i).getKartenWert().equals("Sieben")) {
                      System.out.println("Spieler hat eine Sieben");
                      Karte k = kartenListe.get(i);
                      spiel.getAblageStapel().getAblagekarten().add(k);
                      aktiverSpieler.getSpielerHand().getKarten().remove(k);
                      nächsterSpielerIstDran(spiel);

                  }
                  //wenn er keine 7 hat, muss er eine Karte ziehen
                  else{
                      System.out.println("Spieler hat keine Sieben");
                      ZiehStapel ziehStapel = spiel.getZiehStapel();
                      SpielerHand spielerHand = aktiverSpieler.getSpielerHand();
                      //erste Karte vom Ziehstapel wird gezogen
                      spielerHand.getKarten().add(ziehStapel.getZiehkarten().get(ziehStapel.getZiehkarten().size()-1));
                      ziehStapel.getZiehkarten().remove(ziehStapel.getZiehkarten().get(ziehStapel.getZiehkarten().size()-1));
                      // zweite Karte vom Ziehstapel wird gezogen
                      spielerHand.getKarten().add(ziehStapel.getZiehkarten().get(ziehStapel.getZiehkarten().size()-1));
                      ziehStapel.getZiehkarten().remove(ziehStapel.getZiehkarten().get(ziehStapel.getZiehkarten().size()-1));
                      nächsterSpielerIstDran(spiel);
                  }
              }
       }
       else{
           System.out.println("Sieben wurde nicht gelegt");
       }
    }

    @Override
    public boolean assGelegt(AblageStapel ablageStapel) {
        int anzahl = ablageStapel.getAblagekarten().size()-1;
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

    @Override
    public Spieler aussetzen(Spiel spiel) {
       AblageStapel ablageStapel =  spiel.getAblageStapel();
       int anzahl = ablageStapel.getAblagekarten().size();
       Karte karte = ablageStapel.getAblagekarten().get(anzahl);
       if (karte.getKartenWert().equals("8")) {
           Spieler aktiverSpieler = spiel.getAktiverSpieler();

           int aktiverSpielerIndex =spiel.getSpielerListe().indexOf(aktiverSpieler);
           System.out.println("aktiver: "+ aktiverSpielerIndex);
           int naechsterSpielerIndex;
           if (aktiverSpielerIndex+2 > spiel.getSpielerListe().size()) {
               naechsterSpielerIndex = aktiverSpielerIndex+2-spiel.getSpielerListe().size();
           } else {
               naechsterSpielerIndex = aktiverSpielerIndex+2;
           }
           System.out.println("naechster: "+ naechsterSpielerIndex);
           return spiel.getSpielerListe().get(naechsterSpielerIndex);
       } else {
           System.out.println("keine Acht");
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
        int anzahl = ablageStapel.getAblagekarten().size()-1;
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

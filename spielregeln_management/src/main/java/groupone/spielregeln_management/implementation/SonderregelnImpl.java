package groupone.spielregeln_management.implementation;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.spiel_management.classes.Spiel;
import groupone.spieler_management.classes.Spieler;
import groupone.spielregeln_management.services.SpielregelnService;
import groupone.spiel_management.implementation.KartenSpielerImpl;

import java.util.Collections;
import java.util.List;

public class SonderregelnImpl implements SpielregelnService {

    KartenSpielerImpl kartenSpielerImpl = new KartenSpielerImpl();

    @Override
    public void siebenGelegt(Spiel spiel) {

    //Prüfen, ob der Ablagestapel eine 7 enthält
       AblageStapel ablageStapel = spiel.getAblageStapel();
       int letzteKarteIndex = ablageStapel.getAblagekarten().size() - 1;
       Karte letzteKarte = ablageStapel.getAblagekarten().get(letzteKarteIndex);
       System.out.println(letzteKarte.getKartenWert());

    //Die Anzahl der Karten, die gezogen werden müssen
        int anzahlZiehen = 0;

    //Wenn letzteKarte = "Sieben"
       if(letzteKarte.getKartenWert().equals("Sieben")) {
           System.out.println("Eine Sieben wurde gelegt.");
           anzahlZiehen = 2;
           boolean weitermachen = true;

           while(weitermachen) {
               Spieler aktiverSpieler = spiel.getAktiverSpieler();
               List<Karte> aktiverSpielerKarten = aktiverSpieler.getSpielerHand().getKarten();

               //Prüfen, ob der Spieler eine Sieben hat
               boolean hatSieben = false;
               for (int i = 0; i < aktiverSpielerKarten.size(); i++) {

                   if (aktiverSpielerKarten.get(i).getKartenWert().equals("Sieben")) {
                       hatSieben = true;
                   }

               }

               //Wenn der Spieler keine 7 hat
               if (hatSieben == false) {

                   for (int i = 0; i < anzahlZiehen; i++) {
                       kartenSpielerImpl.zieheKarte(aktiverSpieler.getSpielerHand(), spiel.getZiehStapel());
                   }
                   weitermachen = false;

               }

               //Wenn der Spieler eine 7 hat
               if (hatSieben == true) {
                   for (int i = 0; i < aktiverSpielerKarten.size(); i++) {
                       //Wenn der Spieler eine 7 hat, muss er diese legen
                       if (aktiverSpielerKarten.get(i).getKartenWert().equals("Sieben")) {
                           System.out.println("Spieler hat eine Sieben!");
                           Karte spielerSieben = aktiverSpielerKarten.get(i);
                           spiel.getAblageStapel().getAblagekarten().add(spielerSieben);
                           aktiverSpieler.getSpielerHand().getKarten().remove(spielerSieben);
                           anzahlZiehen += 2;
                           i = aktiverSpielerKarten.size();
                           nächsterSpielerIstDran(spiel);
                       }
                   }
               }
           }
       } else {
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
        Karte karte = ziehStapel.getZiehkarten().get(ziehStapel.getZiehkarten().size()-1);
        spieler.getSpielerHand().getKarten().add(karte);
        spieler.getSpielerHand().setAnzahlKarten(spieler.getSpielerHand().getAnzahlKarten()+1);
    }

    @Override
    public Spieler nächsterSpielerIstDran(Spiel spiel) {
        Spieler aktiverSpieler = spiel.getAktiverSpieler();
        int aktiverSpielerIndex = spiel.getSpielerListe().indexOf(aktiverSpieler);
        System.out.println(aktiverSpielerIndex);
        int naechsterSpielerIndex;
        boolean richtungsWechsel = richtungWechsel(spiel.getAblageStapel());

        // aussetzen
        if(spiel.getAblageStapel().getAblagekarten().get(spiel.getAblageStapel().getAblagekarten().size() - 1)
                .getKartenWert().equals("Acht")){
            Spieler naechsterSpieler = aussetzen(spiel);
            naechsterSpielerIndex = spiel.getSpielerListe().indexOf(naechsterSpieler);
        // Richtungswechsel
        } else if (richtungsWechsel) {
            Collections.reverse(spiel.getSpielerListe());
            naechsterSpielerIndex = spiel.getSpielerListe().indexOf(aktiverSpieler)+1;
        // nächster Spieler ist dran (Fall: aktiver Spieler ist Letzter in der Spielerliste)
        } else if (aktiverSpielerIndex + 1 > spiel.getSpielerListe().size()) {
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
       System.out.println(ablageStapel.getAblagekarten());
       int anzahl = ablageStapel.getAblagekarten().size();
       Karte karte = ablageStapel.getAblagekarten().get(anzahl - 1);
       System.out.println(karte.getKartenWert());
       System.out.println(karte.getKartenFarbe());
       System.out.println(anzahl);
       if (karte.getKartenWert().equals("Acht")) {
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
        int letzteKarteIndex = ablageStapel.getAblagekarten().size() - 1;
        if(ablageStapel.getAblagekarten().get(letzteKarteIndex).getKartenWert().equals("Neun")){
            return true;
        } else {
            return false;
        }
    }

}

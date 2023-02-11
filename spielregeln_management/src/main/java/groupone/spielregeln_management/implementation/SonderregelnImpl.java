package groupone.spielregeln_management.implementation;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.spiel_management.classes.Spiel;
import groupone.spiel_management.services.KartenSpielerService;
import groupone.spieler_management.classes.Spieler;
import groupone.spieler_management.classes.SpielerInterface;
import groupone.spielregeln_management.services.SpielregelnService;

import java.util.Collections;

public class SonderregelnImpl implements SpielregelnService {

    KartenSpielerService kartenSpielerImpl;

    public SonderregelnImpl(){

    }
    int anzahlZiehen = 0;

    public SonderregelnImpl(KartenSpielerService kartenSpielerImpl){
        this.kartenSpielerImpl = kartenSpielerImpl;
    }

    @Override
    public boolean prüfeAufSiebenGelegt(Spiel spiel){
        AblageStapel ablageStapel = spiel.getAblageStapel();
        Karte obersteKarte = ablageStapel.getAblagekarten().get(ablageStapel.getAblagekarten().size()-1);
        if(obersteKarte.getKartenWert().equalsIgnoreCase("7")){
            return true;
        }
        return false;
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
        ziehStapel.getZiehkarten().remove(karte);
    }

    @Override
    public SpielerInterface nächsterSpielerIstDran(Spiel spiel) {
        SpielerInterface aktiverSpieler = spiel.getAktiverSpieler();
        int aktiverSpielerIndex = spiel.getSpielerListe().indexOf(aktiverSpieler);

        int naechsterSpielerIndex;

        boolean richtungsWechsel = richtungWechsel(spiel.getAblageStapel());


        // aussetzen
        if(spiel.getAblageStapel().getAblagekarten().get(spiel.getAblageStapel().getAblagekarten().size() - 1)
                .getKartenWert().equals("8")) {
            SpielerInterface naechsterSpieler = aussetzen(spiel);
            naechsterSpielerIndex = spiel.getSpielerListe().indexOf(naechsterSpieler);
            // Richtungswechsel
        } else if (richtungsWechsel) {

            Collections.reverse(spiel.getSpielerListe());
            aktiverSpielerIndex = spiel.getSpielerListe().indexOf(aktiverSpieler);

            if(aktiverSpielerIndex + 1 > spiel.getSpielerListe().size() - 1) {

                naechsterSpielerIndex = aktiverSpielerIndex - spiel.getSpielerListe().size() + 1;
            }else {

                naechsterSpielerIndex = spiel.getSpielerListe().indexOf(aktiverSpieler) + 1;
            }
        // nächster Spieler ist dran (Fall: aktiver Spieler ist Letzter in der Spielerliste)
        } else if (aktiverSpielerIndex + 1 > spiel.getSpielerListe().size() -1) {
            naechsterSpielerIndex = aktiverSpielerIndex+1-spiel.getSpielerListe().size();

        // nächster Spieler ist dran (Fall: nicht am Ende der Spielerliste)
        } else {
            naechsterSpielerIndex = aktiverSpielerIndex+1;

        }

        spiel.setAktiverSpieler(spiel.getSpielerListe().get(naechsterSpielerIndex));
        return spiel.getAktiverSpieler();
    }

    @Override
    public SpielerInterface aussetzen(Spiel spiel) {
       AblageStapel ablageStapel =  spiel.getAblageStapel();

       int anzahl = ablageStapel.getAblagekarten().size();
       Karte karte = ablageStapel.getAblagekarten().get(anzahl - 1);

       if (karte.getKartenWert().equals("8")) {
           SpielerInterface aktiverSpieler = spiel.getAktiverSpieler();

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
        int letzteKarteIndex = ablageStapel.getAblagekarten().size() - 1;
        Karte letzteKarte = ablageStapel.getAblagekarten().get(letzteKarteIndex);
        System.out.println("kartenfarbe equals" +letzteKarte.getKartenFarbe().equalsIgnoreCase(karte.getKartenFarbe()));
        System.out.println("kartenwert equals"+letzteKarte.getKartenWert().equalsIgnoreCase(karte.getKartenWert()));
        if( letzteKarte.getKartenWert().equalsIgnoreCase(karte.getKartenWert()) || letzteKarte.getKartenFarbe().equalsIgnoreCase(karte.getKartenFarbe())) {
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
        if(ablageStapel.getAblagekarten().get(letzteKarteIndex).getKartenWert().equals("9")){
            return true;
        } else {
            return false;
        }
    }

}

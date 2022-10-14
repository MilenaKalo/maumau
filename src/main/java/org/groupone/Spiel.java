package org.groupone;

import java.util.List;

public class Spiel {

    //Attribute
    private List<Spieler> spielerListe;
    private int runde;
    private AblageStapel ablageStapel;
    private ZiehStapel ziehStapel;

    //Getter und Setter
    public List<Spieler> getSpielerListe() {
        return spielerListe;
    }

    public void setSpielerListe(List<Spieler> spielerListe) {
        this.spielerListe = spielerListe;
    }

    public int getRunde() {
        return runde;
    }

    public void setRunde(int runde) {
        this.runde = runde;
    }

    public AblageStapel getAblageStapel() {
        return ablageStapel;
    }

    public void setAblageStapel(AblageStapel ablageStapel) {
        this.ablageStapel = ablageStapel;
    }

    public ZiehStapel getZiehStapel() {
        return ziehStapel;
    }

    public void setZiehStapel(ZiehStapel ziehStapel) {
        this.ziehStapel = ziehStapel;
    }

    //Konstruktor
    public Spiel(List<Spieler> spielerListe, int runde, AblageStapel ablageStapel, ZiehStapel ziehStapel) {
        this.spielerListe = spielerListe;
        this.runde = runde;
        this.ablageStapel = ablageStapel;
        this.ziehStapel = ziehStapel;
    }
}

package org.groupone;

import java.util.List;

public class SpielerHand {

    //Attribute
    private int anzahlKarten;
    private List<Karte> karten;
    private Spieler spieler;

    //Getter und Setter
    public int getAnzahlKarten() {
        return anzahlKarten;
    }

    public void setAnzahlKarten(int anzahlKarten) {
        this.anzahlKarten = anzahlKarten;
    }

    public List<Karte> getKarten() {
        return karten;
    }

    public void setKarten(List<Karte> karten) {
        this.karten = karten;
    }

    public Spieler getSpieler() {
        return spieler;
    }

    public void setSpieler(Spieler spieler) {
        this.spieler = spieler;
    }

    //Konstruktor
    public SpielerHand(int anzahlKarten, List<Karte> karten, Spieler spieler) {
        this.anzahlKarten = anzahlKarten;
        this.karten = karten;
        this.spieler = spieler;
    }

}

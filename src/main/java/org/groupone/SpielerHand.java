package org.groupone;

import java.util.List;

public class SpielerHand {

    //Attribute
    private int anzahlKarten;
    private List<Karte> karten;
    private Mensch mensch;

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

    public Mensch getMensch() {
        return mensch;
    }

    public void setMensch(Mensch mensch) {
        this.mensch = mensch;
    }

    //Konstruktor
    public SpielerHand(int anzahlKarten, List<Karte> karten, Mensch mensch) {
        this.anzahlKarten = anzahlKarten;
        this.karten = karten;
        this.mensch = mensch;
    }

}

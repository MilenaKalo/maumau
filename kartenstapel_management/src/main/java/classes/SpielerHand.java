package classes;

import classes.Karte;


import java.util.List;

public class SpielerHand {

    //Attributem
    private int anzahlKarten;
    private List<Karte> karten;


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



    //Konstruktor
    public SpielerHand(int anzahlKarten, List<Karte> karten) {
        this.anzahlKarten = anzahlKarten;
        this.karten = karten;

    }

}

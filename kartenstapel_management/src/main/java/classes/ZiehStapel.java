package classes;

import classes.Karte;

import java.util.List;

public class ZiehStapel {

    //Attribute
    private int anzahlKarten;
    private List<Karte> ziehkarten;

    //Getter und Setter
    public int getAnzahlKarten() {
        return anzahlKarten;
    }

    public void setAnzahlKarten(int anzahlKarten) {
        this.anzahlKarten = anzahlKarten;
    }

    public List<Karte> getZiehkarten() {
        return ziehkarten;
    }

    public void setZiehkarten(List<Karte> ziehkarten) {
        this.ziehkarten = ziehkarten;
    }

    //Konstruktor
    public ZiehStapel(int anzahlKarten, List<Karte> ziehkarten) {
        this.anzahlKarten = anzahlKarten;
        this.ziehkarten = ziehkarten;
    }
}

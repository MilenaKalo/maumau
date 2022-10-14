package org.groupone;

public class Spieler {

    //Attribute
    private long id;
    private String name;
    private int punkte;
    private SpielerHand spielerHand;

    //Getter und Setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    //Konstruktor
    public Spieler(long id, String name, int punkte) {
        this.id = id;
        this.name = name;
        this.punkte = punkte;
    }

}

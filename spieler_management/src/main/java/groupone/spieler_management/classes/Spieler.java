package groupone.spieler_management.classes;

import groupone.kartenstapel_management.classes.SpielerHand;

public class Spieler extends SpielerInterface {


    // Konstruktor
    public Spieler() {
    }

    public Spieler(long id, String name) {
        this.id = id;
        this.name = name;
        this.punkte = 0;
        this.istVirtuell = false;
    }


}

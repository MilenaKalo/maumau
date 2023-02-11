package groupone.spieler_management.classes;

import groupone.kartenstapel_management.classes.SpielerHand;
import javax.persistence.*;

public class VirtuellerSpieler extends SpielerInterface {

    // Konstruktor
    public VirtuellerSpieler() {
    }

    public VirtuellerSpieler(long id, String name) {
        this.id = id;
        this.name = name;
        this.punkte = 0;
        this.istVirtuell = true;
    }


}

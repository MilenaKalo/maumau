package groupone.spieler_management.classes;



public class VirtuellerSpieler extends SpielerInterface {


    public VirtuellerSpieler() {
    }

    public VirtuellerSpieler(long id, String name) {
        this.id = id;
        this.name = name;
        this.punkte = 0;
        this.istVirtuell = true;
    }


}

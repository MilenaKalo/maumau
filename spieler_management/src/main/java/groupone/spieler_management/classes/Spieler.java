package groupone.spieler_management.classes;



public class Spieler extends SpielerInterface {



    public Spieler() {
    }

    public Spieler(long id, String name) {
        this.id = id;
        this.name = name;
        this.punkte = 0;
        this.istVirtuell = false;
    }


}

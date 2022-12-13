package groupone.spieler_management.classes;

import groupone.kartenstapel_management.classes.SpielerHand;

public class Spieler {

    //Attribute
    private long id;
    private String name;
    private int punkte;
    private SpielerHand spielerHand;
    private boolean mauGesagt;
    private int strafziehen;
    private String wunschfarbe;
    private boolean aussetzen;

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

    public SpielerHand getSpielerHand() {
        return spielerHand;
    }

    public void setSpielerHand(SpielerHand spielerHand) {
        this.spielerHand = spielerHand;
    }

    public boolean isMauGesagt() {
        return mauGesagt;
    }

    public void setMauGesagt(boolean mauGesagt) {
        this.mauGesagt = mauGesagt;
    }

    //Konstruktor
    public Spieler(long id, String name, int punkte) {
        this.id = id;
        this.name = name;
        this.punkte = punkte;
    }


    public int getStrafziehen() {
        return strafziehen;
    }

    public void setStrafziehen(int strafziehen) {
        this.strafziehen = strafziehen;
    }

    public String getWunschfarbe() {
        return wunschfarbe;
    }

    public void setWunschfarbe(String wunschfarbe) {
        this.wunschfarbe = wunschfarbe;
    }

    public boolean isAussetzen() {
        return aussetzen;
    }

    public void setAussetzen(boolean aussetzen) {
        this.aussetzen = aussetzen;
    }
}

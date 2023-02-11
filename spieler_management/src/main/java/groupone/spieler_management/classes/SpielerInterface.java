package groupone.spieler_management.classes;

import groupone.kartenstapel_management.classes.SpielerHand;

import javax.persistence.*;

@Entity
@Table(name = "SPIELER")
public class SpielerInterface {

    @Id
    long id = 0;

    @Column(nullable = false)
    String name = null;

    @Column(nullable = false)
    int punkte = 0;

    @OneToOne
    SpielerHand spielerHand = null;

    @Column
    boolean mauGesagt = false;

    @Column
    boolean maumauGesagt = false;

    @Column
    int strafziehen = 0;

    @Column
    String wunschfarbe = "";

    @Column
    boolean istVirtuell = false;

    @Column
    boolean aussetzen = false;

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

    public boolean isMaumauGesagt() {
        return maumauGesagt;
    }

    public void setMaumauGesagt(boolean maumauGesagt) {
        this.maumauGesagt = maumauGesagt;
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

    public boolean isIstVirtuell() {
        return istVirtuell;
    }

    public void setIstVirtuell(boolean istVirtuell) {
        this.istVirtuell = istVirtuell;
    }

}

package groupone.spieler_management.classes;

import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.spiel_management.classes.Spiel;

import javax.persistence.*;

@Entity
@Table(name = "SPIELER")
public class Spieler {

    //Attribute
    @Id
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "punkte")
    private int punkte;

    @ManyToOne
    @JoinColumn(name="SID", nullable = false)
    private Spiel spiel;

    @OneToOne
    @MapsId
    @JoinColumn(name="SID", nullable = false)
    private Spiel spiel2;

    @OneToOne
    private SpielerHand spielerHand;
    @Column
    private boolean mauGesagt;
    @Column
    private boolean maumauGesagt;
    @Column
    private int strafziehen;
    @Column
    private String wunschfarbe;
    @Column
    private boolean aussetzen;

    public Spieler() {

    }

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

    public boolean isMaumauGesagt() {
        return maumauGesagt;
    }

    public void setMaumauGesagt(boolean maumauGesagt) {
        this.maumauGesagt = maumauGesagt;
    }
}

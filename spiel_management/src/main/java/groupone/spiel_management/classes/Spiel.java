package groupone.spiel_management.classes;

import groupone.spieler_management.classes.Spieler;
import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.ZiehStapel;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "SPIEL")
public class Spiel {
    @Id
    @Column(name = "SID", nullable = false)
    private Long id;

    //Attribute
    @OneToMany(mappedBy="spiel")
    private List<Spieler> spielerListe;

    @Column
    private int runde;

    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "spiel", cascade = CascadeType.ALL)
    private AblageStapel ablageStapel;

    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "spiel", cascade = CascadeType.ALL)
    private ZiehStapel ziehStapel;

    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "spiel2", cascade = CascadeType.ALL)
    private Spieler aktiverSpieler;

    public Spiel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Getter und Setter
    public List<Spieler> getSpielerListe() {
        return spielerListe;
    }

    public void setSpielerListe(List<Spieler> spielerListe) {
        this.spielerListe = spielerListe;
    }

    public int getRunde() {
        return runde;
    }

    public void setRunde(int runde) {
        this.runde = runde;
    }

    public AblageStapel getAblageStapel() {
        return ablageStapel;
    }

    public void setAblageStapel(AblageStapel ablageStapel) {
        this.ablageStapel = ablageStapel;
    }

    public ZiehStapel getZiehStapel() {
        return ziehStapel;
    }

    public void setZiehStapel(ZiehStapel ziehStapel) {
        this.ziehStapel = ziehStapel;
    }

    public Spieler getAktiverSpieler() {
        return aktiverSpieler;
    }

    public void setAktiverSpieler(Spieler aktiverSpieler) {
        this.aktiverSpieler = aktiverSpieler;
    }

    //Konstruktor
    public Spiel(List<Spieler> spielerListe, int runde, AblageStapel ablageStapel, ZiehStapel ziehStapel) {
        this.spielerListe = spielerListe;
        this.runde = runde;
        this.ablageStapel = ablageStapel;
        this.ziehStapel = ziehStapel;
    }
}

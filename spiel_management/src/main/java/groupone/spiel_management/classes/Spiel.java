package groupone.spiel_management.classes;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.spieler_management.classes.SpielerInterface;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SPIEL")
public class Spiel {

    @Id
    @Column(name = "SID", nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SpielerInterface> spielerListe;

    @Column(name ="runde")
    private int runde;

    @OneToOne(cascade = CascadeType.ALL)
    private AblageStapel ablageStapel;


    @OneToOne(cascade = CascadeType.ALL)
    private ZiehStapel ziehStapel;


    @OneToOne( cascade = CascadeType.ALL)
    private SpielerInterface aktiverSpieler;

    public Spiel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Getter und Setter
    public List<SpielerInterface> getSpielerListe() {
        return spielerListe;
    }

    public void setSpielerListe(List<SpielerInterface> spielerListe) {
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

    public SpielerInterface getAktiverSpieler() {
        return aktiverSpieler;
    }

    public void setAktiverSpieler(SpielerInterface aktiverSpieler) {
        this.aktiverSpieler = aktiverSpieler;
    }

    //Konstruktor
    public Spiel(List<SpielerInterface> spielerListe, int runde, AblageStapel ablageStapel, ZiehStapel ziehStapel) {
        this.spielerListe = spielerListe;
        this.runde = runde;
        this.ablageStapel = ablageStapel;
        this.ziehStapel = ziehStapel;
    }

}

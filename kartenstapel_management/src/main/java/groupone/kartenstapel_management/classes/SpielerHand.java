package groupone.kartenstapel_management.classes;

import groupone.kartenstapel_management.classes.Karte;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="SPIELERHAND")
public class SpielerHand {
    @Id
    @Column(name = "SHID", nullable = false)
    private Long id;

    //Attribute
    @Column
    private int anzahlKarten;

    @OneToMany(mappedBy="spielerHand")
    private List<Karte> karten;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    //Getter und Setter
    public int getAnzahlKarten() {
        return anzahlKarten;
    }

    public void setAnzahlKarten(int anzahlKarten) {
        this.anzahlKarten = anzahlKarten;
    }

    public List<Karte> getKarten() {
        return karten;
    }

    public void setKarten(List<Karte> karten) {
        this.karten = karten;
    }



    //Konstruktor
    public SpielerHand(int anzahlKarten, List<Karte> karten) {
        this.anzahlKarten = anzahlKarten;
        this.karten = karten;

    }

    public SpielerHand(){

    }

}

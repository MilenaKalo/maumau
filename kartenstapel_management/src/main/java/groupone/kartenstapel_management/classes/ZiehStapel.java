package groupone.kartenstapel_management.classes;

import groupone.kartenstapel_management.classes.Karte;


import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "ZIEHSTAPEL")
public class ZiehStapel {
    @Id
    @Column(name = "ZID", nullable = false)
    private Long id;

    //Attribute
    @Column
    private int anzahlKarten;

    @OneToMany(mappedBy="ziehStapel")
    private List<Karte> ziehkarten;

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

    public List<Karte> getZiehkarten() {
        return ziehkarten;
    }

    public void setZiehkarten(List<Karte> ziehkarten) {
        this.ziehkarten = ziehkarten;
    }

    //Konstruktor
    public ZiehStapel(int anzahlKarten, List<Karte> ziehkarten) {
        this.anzahlKarten = anzahlKarten;
        this.ziehkarten = ziehkarten;
    }

    public ZiehStapel(){

    }
}

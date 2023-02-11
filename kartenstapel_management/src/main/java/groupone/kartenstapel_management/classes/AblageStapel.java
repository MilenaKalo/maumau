package groupone.kartenstapel_management.classes;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ABLAGESTAPEL")
public class AblageStapel {
    @Id
    @Column(name = "AID", nullable = false)
    private Long id;

    //Attribute
    @Column
    private int anzahlKarten;

    @Column
    private String wunschFarbe;

    @OneToMany(mappedBy="ablageStapel")
    private List<Karte> ablagekarten;

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

    public List<Karte> getAblagekarten() {
        return ablagekarten;
    }

    public void setAblagekarten(List<Karte> ablagekarten) {
        this.ablagekarten = ablagekarten;
    }

    public String getWunschFarbe() {
        return wunschFarbe;
    }

    public void setWunschFarbe(String wunschFarbe) {
        this.wunschFarbe = wunschFarbe;
    }

    //Konstruktor
    public AblageStapel(int anzahlKarten, List<Karte> ablagekarten) {
        this.anzahlKarten = anzahlKarten;
        this.ablagekarten = ablagekarten;
    }

    public AblageStapel(){

    }

}

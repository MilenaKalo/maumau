package groupone.kartenstapel_management.classes;

import javax.persistence.*;

@Entity
@Table(name = "KARTE")
public class Karte {


    //Attribute
    @Id
    @Column(name = "KID", nullable = false)
    private String kartenFarbe;

    @Column(name = "kartenWert")
    private String kartenWert;

    @ManyToOne
    @JoinColumn(name="AID", nullable = false)
    private AblageStapel ablageStapel;

    @ManyToOne
    @JoinColumn(name="ZID", nullable = false)
    private ZiehStapel ziehStapel;

    @ManyToOne
    @JoinColumn(name="SHID", nullable = false)
    private SpielerHand spielerHand;

    public Karte() {

    }
    //Getter und Setter
    public String getKartenFarbe() {
        return kartenFarbe;
    }

    public void setKartenFarbe(String kartenFarbe) {
        this.kartenFarbe = kartenFarbe;
    }

    public String getKartenWert() {
        return kartenWert;
    }

    public void setKartenWert(String kartenWert) {
        this.kartenWert = kartenWert;
    }

    //Konstruktor
    public Karte(String kartenFarbe, String kartenWert) {
        this.kartenFarbe = kartenFarbe;
        this.kartenWert = kartenWert;
    }

}

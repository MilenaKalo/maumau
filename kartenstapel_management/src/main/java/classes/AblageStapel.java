package classes;

import java.util.List;
public class AblageStapel {

    //Attribute
    private int anzahlKarten;
    private List<Karte> ablagekarten;
    private String zulaessigeFarbe;

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

    public String getZulaessigeFarbe() {
        return zulaessigeFarbe;
    }

    public void setZulaessigeFarbe(String zulaessigeFarbe) {
        this.zulaessigeFarbe = zulaessigeFarbe;
    }

    //Konstruktor
    public AblageStapel(int anzahlKarten, List<Karte> ablagekarten) {
        this.anzahlKarten = anzahlKarten;
        this.ablagekarten = ablagekarten;
    }

}

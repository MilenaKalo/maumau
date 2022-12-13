package groupone.kartenstapel_management.classes;
public class Karte {

    //Attribute
    private String kartenFarbe;
    private String kartenWert;


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

package implementation;

import classes.AblageStapel;
import classes.Spiel;
import classes.Spieler;
import classes.ZiehStapel;
import services.SpielService;

import java.util.List;

public class SpielImpl implements SpielService {

    @Override
    public List<Spieler> erstelleSpielerReihenfolge(List<Spieler> spielerliste) {
        return null;
    }

    @Override
    public Spieler nächsterSpielerIstDran(Spiel spiel) {
        return null;
    }

    @Override
    public String beendeSpiel(Spiel spiel) {
        return "Das Spiel wurde beendet!";
    }

    @Override
    public Spieler gibGewinneraus(Spiel spiel) {
        return null;
    }

    @Override
    public Spiel erstelleSpiel(List<Spieler> spielerListe, int runde, AblageStapel ablageStapel, ZiehStapel ziehStapel){
        return null;
    }

    public Spieler aussetzen(Spiel spiel) {
        return null;
    }

}

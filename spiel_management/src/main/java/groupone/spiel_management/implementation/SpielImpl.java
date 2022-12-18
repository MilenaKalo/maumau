package groupone.spiel_management.implementation;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.spiel_management.classes.Spiel;
import groupone.spieler_management.classes.Spieler;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.spiel_management.services.SpielService;

import java.util.List;

public class SpielImpl implements SpielService {

    @Override
    public List<Spieler> erstelleSpielerReihenfolge(List<Spieler> spielerliste) {
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



}

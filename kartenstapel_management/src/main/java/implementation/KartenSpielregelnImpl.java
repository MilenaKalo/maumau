package implementation;

import classes.AblageStapel;
import classes.Karte;
import services.KartenSpielregelnService;

public class KartenSpielregelnImpl implements KartenSpielregelnService {
    @Override
    public boolean prüfeKarte(Karte karte, AblageStapel ablageStapel) {
        return false;
    }
}

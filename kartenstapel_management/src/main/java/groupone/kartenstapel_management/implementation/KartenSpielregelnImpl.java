package groupone.kartenstapel_management.implementation;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.services.KartenSpielregelnService;

public class KartenSpielregelnImpl implements KartenSpielregelnService {
    @Override
    public boolean prüfeKarte(Karte karte, AblageStapel ablageStapel) {
        return true;
    }
}

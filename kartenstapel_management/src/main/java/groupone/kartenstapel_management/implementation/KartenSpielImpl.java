package groupone.kartenstapel_management.implementation;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.kartenstapel_management.services.KartenSpielService;
import java.util.List;
public class KartenSpielImpl implements KartenSpielService {
    @Override
    public void legeErsteKarteAufAblagestapel(ZiehStapel ziehStapel, AblageStapel ablageStapel) {

    }

    @Override
    public List<Karte> mischeKarten(List<Karte> karteList) {
        return null;
    }

    @Override
    public void kartenDesAblagestapelsDemZiehstapelUebergeben(AblageStapel ablageStapel, ZiehStapel ziehStapel) {

    }

}

package implementation;

import classes.AblageStapel;
import classes.SpielerHand;
import classes.ZiehStapel;
import org.mockito.Mock;
import services.KartenSpielerService;
import classes.Karte;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

public class KartenSpielerImpl implements KartenSpielerService {

    @Override
    public void legeKarteAb(SpielerHand spielerhand, Karte karte, AblageStapel ablageStapel) {

    }

    @Override
    public void zieheKarte(SpielerHand spielerhand, ZiehStapel ziehStapel) {

    }
}

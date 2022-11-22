import classes.AblageStapel;
import classes.Karte;
import implementation.KartenSpielregelnImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import services.KartenSpielregelnService;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class KartenSpielregelnTest {

    @InjectMocks
    private KartenSpielregelnService kartenSpielregelnService = new KartenSpielregelnImpl();
    @Test
    public void prüfeKarteTest(){

        //Setup
        Karte herzZehn = new Karte("Herz", "Zehn");
        Karte herzNeun = new Karte("Herz", "Neun");

        int anzahlAblageKarten = 1;
        List<Karte> kartenAblageListe = new ArrayList<>();
        kartenAblageListe.add(herzZehn);
        AblageStapel ablageStapel = new AblageStapel(anzahlAblageKarten, kartenAblageListe);

        //erwartet
        boolean erwartet = true;

        //actual
        Assertions.assertEquals(erwartet, kartenSpielregelnService.prüfeKarte(herzNeun, ablageStapel));

    }





}

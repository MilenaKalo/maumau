import classes.Spieler;
import implementation.SpielerImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import services.SpielerService;


@RunWith(MockitoJUnitRunner.class)
public class SpielerServiceTest {
    @InjectMocks
    private SpielerService spielerService = new SpielerImpl();

    @Test
    public void erhöhePunktTest() {
        //setup
        Spieler spieler = new Spieler(1L, "Max Muster", 1);

        //erwartet
        int punkteErwartet = 2;

         //actual

        spielerService.erhöhePunkt(spieler);

        // assert
        Assertions.assertEquals(punkteErwartet, spieler.getPunkte());
    }

    @Test
    public void maumauTest() {
       // was sollen wir hier testen ?
    }

    @Test
    public void gibGewinnerAusTest(){
        // vielleicht eher in Spiel ??
    }

    @Test
    public void sageMauTest() {
        //setup
        Spieler spieler = new Spieler(1L, "Max Muster", 1);
        spieler.setMauGesagt(false);
        //erwartet

        boolean erwartet = true;
        //actual

        spielerService.sageMau(spieler);

        // assert
        Assertions.assertEquals(erwartet, spieler.isMauGesagt());
    }

    @Test
    public void mauZurückTest() {
        //setup
        Spieler spieler = new Spieler(1L, "Max Muster", 1);
        spieler.setMauGesagt(true);
        //erwartet

        boolean erwartet = false;
        //actual

        spielerService.mauZuruecksetzen(spieler);

        // assert
        Assertions.assertEquals(erwartet, spieler.isMauGesagt());
    }
}

package implementation;

import classes.Spieler;
import services.SpielerService;

public class SpielerImpl implements SpielerService {

    private SpielerService spielerService;

    @Override
    public void erhöhePunkt(Spieler spieler) {
        spieler.setPunkte(spieler.getPunkte() + 1);
    }

    @Override
    public void maumau(Spieler spieler) {
        spieler.setMauGesagt(true);
    }

    @Override
    public void sageMau(Spieler spieler) {
        boolean b = spieler.getSpielerHand().getAnzahlKarten() <= 1;

        if (b == true) {
            spieler.setMauGesagt(true);
        }
    }

    @Override
    public void mauZuruecksetzen(Spieler spieler) {
        spieler.setMauGesagt(false);
    }


}

package groupone.spieler_management.implementation;

import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.spieler_management.classes.Spieler;
import groupone.spieler_management.services.SpielerService;

public class SpielerImpl implements SpielerService {

    public SpielerImpl(){

    }

    @Override
    public void erhöhePunkt(Spieler spieler) {

        spieler.setPunkte(spieler.getPunkte() + 1);
    }

    @Override
    public void maumau(Spieler spieler) {
        boolean b = spieler.getSpielerHand().getAnzahlKarten() == 0;
        if (b) {
            spieler.setMaumauGesagt(true);
        }
    }

    @Override
    public void sageMau(Spieler spieler) {
        boolean b = spieler.getSpielerHand().getAnzahlKarten() == 1;

        if (b) {
            spieler.setMauGesagt(true);
        }
    }

    @Override
    public void mauZuruecksetzen(Spieler spieler) {
        spieler.setMauGesagt(false);
    }

    @Override
    public Spieler spielerErstellen(long id, String name, int punkte) {
        Spieler spieler = new Spieler(id, name, punkte);
        return spieler;
    }


}

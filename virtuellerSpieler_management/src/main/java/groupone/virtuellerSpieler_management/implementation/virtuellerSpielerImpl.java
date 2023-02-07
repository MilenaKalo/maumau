package groupone.virtuellerSpieler_management.implementation;

import groupone.virtuellerSpieler_management.classes.virtuellerSpieler;
import groupone.virtuellerSpieler_management.service.virtuellerSpielerService;

public class virtuellerSpielerImpl implements virtuellerSpielerService {


    public virtuellerSpielerImpl(){

    }

    @Override
    public void erhöhePunkt(virtuellerSpieler spieler) {

        spieler.setPunkte(spieler.getPunkte() + 1);
    }

    @Override
    public void maumau(virtuellerSpieler spieler) {
        boolean b = spieler.getSpielerHand().getAnzahlKarten() == 0;
        if (b) {
            spieler.setMaumauGesagt(true);
        }
    }

    @Override
    public void sageMau(virtuellerSpieler spieler) {
        boolean b = spieler.getSpielerHand().getAnzahlKarten() == 1;

        if (b) {
            spieler.setMauGesagt(true);
        }
    }

    @Override
    public void mauZuruecksetzen(virtuellerSpieler spieler) {
        spieler.setMauGesagt(false);
    }

    @Override
    public virtuellerSpieler spielerErstellen(long id, String name, int punkte) {
        //random id erzeugen
        // random name erzeugen
        virtuellerSpieler spieler = new virtuellerSpieler(id, name, punkte);
        return spieler;
    }


}

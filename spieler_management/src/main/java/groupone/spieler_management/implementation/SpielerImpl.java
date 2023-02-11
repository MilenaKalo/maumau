package groupone.spieler_management.implementation;

import groupone.spieler_management.classes.Spieler;
import groupone.spieler_management.services.SpielerService;

public class SpielerImpl implements SpielerService {

    public SpielerImpl(){
    }

    @Override
    public void sageMau(Spieler spieler) {
        System.out.println("Der Spieler \"" + spieler.getName() + "\" hat Mau gesagt!");
        spieler.setMauGesagt(true);
    }

    @Override
    public void mauZuruecksetzen(Spieler spieler) {
        spieler.setMauGesagt(false);
    }

    @Override
    public void maumau(Spieler spieler) {
        System.out.println("Der Spieler \"" + spieler.getName() + "\" hat MauMau gesagt!");
        spieler.setMaumauGesagt(true);
    }

    @Override
    public void erhöhePunkt(Spieler spieler) {
        spieler.setPunkte(spieler.getPunkte() + 1);
        System.out.println("Der Punktestand von \"" + spieler.getName() + "\" ist auf " + spieler.getPunkte() + " gestiegen!");
    }

    @Override
    public Spieler spielerErstellen(long id, String name) {
        Spieler spieler = new Spieler(id, name);
        return spieler;
    }

}

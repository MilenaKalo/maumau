package groupone.spieler_management.implementation;

import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.spieler_management.classes.VirtuellerSpieler;
import groupone.spieler_management.services.VirtuellerSpielerService;

import java.util.ArrayList;
import java.util.List;

public class VirtuellerSpielerImpl implements VirtuellerSpielerService {

    public VirtuellerSpielerImpl(){
    }

    @Override
    public void sageMau(VirtuellerSpieler spieler) {
        if(spieler.getSpielerHand().getAnzahlKarten() == 1){
            System.out.println("Der Spieler \"" + spieler.getName() + "\" hat Mau gesagt!");
            spieler.setMauGesagt(true);
        }
    }

    @Override
    public void mauZuruecksetzen(VirtuellerSpieler spieler) {
        spieler.setMauGesagt(false);
    }

    @Override
    public void maumau(VirtuellerSpieler spieler) {
        if(spieler.getSpielerHand().getAnzahlKarten() == 0) {
            System.out.println("Der Spieler \"" + spieler.getName() + "\" hat MauMau gesagt!");
            spieler.setMaumauGesagt(true);
        }
    }

    @Override
    public void erhöhePunkt(VirtuellerSpieler spieler) {
        spieler.setPunkte(spieler.getPunkte() + 1);
        System.out.println("Der Punktestand von \"" + spieler.getName() + "\" ist auf " + spieler.getPunkte() + " gestiegen!");
    }

    @Override
    public VirtuellerSpieler spielerErstellen(){
        long id = java.util.UUID.randomUUID().getMostSignificantBits();
        String name = java.util.UUID.randomUUID().toString();
        VirtuellerSpieler spieler = new VirtuellerSpieler(id, name);
        List<Karte> karten = new ArrayList<Karte>();
        SpielerHand spielerHand = new SpielerHand(0, karten);
        spieler.setSpielerHand(spielerHand);
        return spieler;
    }

}

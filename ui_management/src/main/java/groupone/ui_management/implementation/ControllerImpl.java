package groupone.ui_management.implementation;

import groupone.exception_management.AnwendungsException;
import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.spiel_management.classes.Spiel;
import groupone.spiel_management.services.KartenSpielerService;
import groupone.spiel_management.services.SpielService;
import groupone.spieler_management.classes.Spieler;
import groupone.spieler_management.services.SpielerService;
import groupone.spielregeln_management.services.SpielregelnService;
import groupone.ui_management.service.ControllerService;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements ControllerService {

    //Controller - kennt die schnittstellen der anwendungslogik
    private Spiel spiel;
    private View view;
    private List<Spieler> spielerList = new ArrayList<>();
    private AblageStapel ablageStapel;
    private ZiehStapel ziehStapel;
    private SpielService spielService;
    private SpielerService spielerService;
    private KartenSpielerService kartenSpielerService;
    private SpielregelnService spielregelnService;

    // DB

    public ControllerImpl() {
    }

    /**
     * Die Methode startet das Hauptmenü des Spiels und verarbeitet die Eingaben des Nutzers.
     * Je nachdem was der Nutzer eingibt, wird eine neue View-Methode ausgeführt.
     */
    public void hauptMenue() {
        String x = view.hauptMenue();
        if (x.equals("s")) {
            view.startGame();
        } else if (x.equals("d")) {
            view.loescheSpiel();
        } else if (x.equals("e")) {
            System.exit(0);
        }
    }

    /**
     * Methode läd ein existierendes Spiel aus der Datenbank oder erstellt ein neues Spiel
     */
    public void starteSpiel() {
        //
        String x = view.startGame();

        if (x.equals("n")) {
            String spielername = view.spielerName();
            long id = view.spielerId();
            Spieler spieler = spielerService.spielerErstellen(id, spielername, 0);
            spielerList.add(spieler);
            while (view.spielerhinzufügen().equals("ja")) {
                spielername = view.spielerName();
                id = view.spielerId();
                spieler = spielerService.spielerErstellen(id, spielername, 0);
                spielerList.add(spieler);
                if (spielerList.size() == 4) {
                    break;
                }
            }
            spiel = spielService.erstelleSpiel(spielerList, 0, ablageStapel, ziehStapel);
        } else if (x.equals("l")) {
            // lade aus DB und starte spiel
        } else if (x.equals("h")) {
            view.hauptMenue();
        } else {
           view.hauptMenue();
        }
    }

    /**
     * Methode die die oberste Karte des Ablagestapels zeigt
     *
     * @param spiel - Spiel das den Ablagestapel liefert
     */
    private void anzeigeObersteKarte(Spiel spiel) {

        String wert = spiel.getAblageStapel().getAblagekarten().get(spiel.getAblageStapel().getAblagekarten().size() - 1).getKartenWert();
        String farbe = spiel.getAblageStapel().getAblagekarten().get(spiel.getAblageStapel().getAblagekarten().size() - 1).getKartenFarbe();

        if (wert.equals("Bube")) {
            view.farbeWählen();
        }
        view.karteAnzeigenAblagestapel(wert, farbe);
    }

    /**
     * fragt nach welcher Regel gespielt werden soll
     */
    private void frageNachSpielregeln() throws AnwendungsException {
        String x = view.regelnAnzeigenundAuswählen();

        if (x.equals("e")) {
            // TODO
        } else if (x.equals("s")) {
            // TODO
        } else {
            // Exception
            view.regelnAnzeigenundAuswählen();
            throw new AnwendungsException("Falsche Eingabe.");
        }
    }

    /**
     * Methode gibt Spieler Gelegenheit, "Mau" oder "MauMau" zu sagen
     * @param spieler Spieler, der "Mau" oder "MauMau" sagen möchte
     */
    private void spielerHatWasGesagt(Spieler spieler) {
        if(view.wasSagen(spieler).equals("mau")){
            spielerService.sageMau(spieler);
            view.spielerHatMauGesagt();
        }else if(view.wasSagen(spieler).equals("maumau")) {
            spielerService.maumau(spieler);
            view.spielerHatMauMauGesagt();
        }
    }

    /**
     * Methode die die Karten der Spielerhand des Spielers anzeigt
     * @param spieler - Spieler dessen Hand angezeigt werden soll
     */
    private void zeigeKartenSpielerHand(Spieler spieler) throws AnwendungsException {
        String x = view.kartenAnzeigenSpielerHand();
        if (x.equals("y")){
            for (int i = 0; i < spieler.getSpielerHand().getKarten().size(); i++) {
                String wert = spieler.getSpielerHand().getKarten().get(i).getKartenWert();
                String farbe = spieler.getSpielerHand().getKarten().get(i).getKartenFarbe();
                view.infoZurKarte(wert, farbe);
            }
        } else if (x.equals("n")){
            //TODO
        } else {
            // TODO
            view.kartenAnzeigenSpielerHand();
            // Exception
            throw new AnwendungsException("Falsche Eingabe.");


        }

    }

    /**
     * Methode die eine Karte zur SpielerHand inzufügt und die Karte vom ZiehStapel entfernt
     */
    private void zieheKarte(Spieler spieler) {
        view.karteZiehen();
        //zieheKarte(SpielerHand spielerhand, ZiehStapel ziehStapel)
        kartenSpielerService.zieheKarte(spieler.getSpielerHand(), spiel.getZiehStapel());

    }

    /**
     * Methode die prüft eine Fehlermeldung zur falschen Karte gibt
     * @param spiel, in welchem man sich gerade befindet
     */
    private void prüfeKarte(Spiel spiel) {
        boolean i = true;
        while (i) {
            List<String> kartendetails;
            kartendetails = view.karteAblegen();
            String wert = kartendetails.get(0);
            String farbe = kartendetails.get(1);
            Karte karte = new Karte(wert, farbe);
            Spieler spieler = spiel.getAktiverSpieler();
            SpielerHand spielerHand = spieler.getSpielerHand();
            AblageStapel ablage = spiel.getAblageStapel();
            if (spielerHand.getKarten().contains(karte)) {
                spielregelnService.pruefeKarte(karte, ablage);
                i = false;
            } else {
                view.falscheKarte();
               i = true;
            }

        }
    }
}

package groupone.ui_management.implementation;

import groupone.virtuellerSpieler_management.classes.virtuellerSpieler;
import groupone.virtuellerSpieler_management.implementation.virtuellerSpielerImpl;
import groupone.virtuellerSpieler_management.service.virtuellerSpielerService;
import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.spiel_management.classes.Spiel;
import groupone.spiel_management.services.KartenSpielerService;
import groupone.spiel_management.services.SpielService;
import groupone.spieler_management.classes.Spieler;
import groupone.spieler_management.services.SpielerService;
import groupone.spielregeln_management.implementation.SonderregelnImpl;
import groupone.spielregeln_management.implementation.SpielregelnImpl;
import groupone.spielregeln_management.services.SpielregelnService;
import groupone.ui_management.service.ControllerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ControllerImpl implements ControllerService {

    //Controller - kennt die schnittstellen der anwendungslogik
    private Spiel spiel;
    private View view = new View();
    private List<Spieler> spielerList = new ArrayList<>();
    private KartenSpielService kartenSpiel;
    private AblageStapel ablageStapel;
    private ZiehStapel ziehStapel;
    private SpielService spielService;
    private SpielerService spielerService;
    private KartenSpielerService kartenSpielerService;
    private SpielregelnService spielregelnService;
    private int anzahlZiehen;


    // DB

    public ControllerImpl() {
    }

    //TODO heute
    // virtueller Spieler implementieren - Max
    // Exceptions abfangen und weitereinbauen


    //TODO morgen
    // Testabdeckung erfüllen
    // JavaDoc überprüfen und erstellen
    // Diagramme aktualisieren - prüfen
    // Dokumentation abschließen - Max


    /**
     * Die Methode startet das Hauptmenü des Spiels und verarbeitet die Eingaben des Nutzers.
     * Je nachdem was der Nutzer eingibt, wird eine neue View-Methode ausgeführt.
     */
    public void hauptMenue() {
        String x = view.hauptMenue();
        x.toLowerCase();
        if (x.equals("s")) {
            starteSpiel();
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
            this.ablageStapel = new AblageStapel();
            this.ziehStapel = new ZiehStapel();
            this.kartenSpiel = new KartenSpielImpl();
            this.spielService = new SpielImpl(this.kartenSpiel);
            this.spielerService = new SpielerImpl();
            this.kartenSpielerService = new KartenSpielerImpl();

            // erster Spieler wird hinzugefügt
            String spielername = view.spielerName();
            long id =view.spielerId();
            Spieler spieler = spielerService.spielerErstellen(id, spielername, 0);
            spielerList.add(spieler);


            //weiterer Spieler wird hinzugefügt
            boolean weitermachen = true;
            while(weitermachen) {
                String frage = view.spielerhinzufügen();

                    if (frage.equals("nein")) {
                        if (spielerList.size() < 2) {
                            view.zuWenigeSpieler(spielerList.size());
                        } else {
                            weitermachen = false;
                        }
                    } else if (frage.equals("m")) {
                         spielername = view.spielerName();
                         id =  view.spielerId();

                        spieler = spielerService.spielerErstellen(id, spielername, 0);
                        spielerList.add(spieler);
                        if (spielerList.size() == 4) {
                            break;
                        }
                    } else if (frage.equals("v")) {
                        id =  view.spielerId();
                        spielername = view.spielerName();
                        virtuellerSpieler virtuellerSpieler = new virtuellerSpieler(id, spielername, 0);
                        //spielerList.add(virtuellerSpieler);
                    }

            }
             spiel = spielService.erstelleSpiel(spielerList, 0, ablageStapel, ziehStapel);
             String z = frageNachSpielregeln();
             if(z.equals("einfach")){
                 spieleMitEinfachenRegeln(spiel);
             }
             else if(z.equals("sonder")){
                 spieleMitSonderRegeln(spiel);
             }
        } else if (x.equals("l")) {
            // lade aus DB und starte spiel
        } else if (x.equals("h")) {
            hauptMenue();
        } else {
           hauptMenue();

        }
    }

    /**
     * Spielablauf bei einfachen Regeln
     * @param spiel Spiel welches gespielt werden soll mit einfachen Regeln
     */
    private void spieleMitEinfachenRegeln(Spiel spiel){
        SpielregelnService spielregelnEinfach = new SpielregelnImpl();
        spiel.setAktiverSpieler((Spieler) spiel.getSpielerListe().get(0));
        view.zeigeAktivenSpieler(spiel.getAktiverSpieler());
        boolean boo = true;
        boolean ersteRunde = true;
        while (boo) {
            anzeigeObersteKarte(spiel);

            zeigeKartenSpielerHand(spiel.getAktiverSpieler());
            boolean machWeiter = true;
            while (machWeiter) {
                boolean u =  spielregelnEinfach.prüfeAufSiebenGelegt(spiel);
                if (u){
                    anzahlZiehen = 2;
                    for (int i = 0; i < anzahlZiehen; i++) {
                        kartenSpielerService.zieheKarte(spiel.getAktiverSpieler().getSpielerHand(), spiel.getZiehStapel());
                    }
                }
                String antwortFrage = view.frageKarteZiehen();
                antwortFrage.toLowerCase();
                if (antwortFrage.equals("a")) {
                    boolean r =  prüfeKarte(spiel, spielregelnEinfach);
                    if (r){
                        wunschFarbe(spiel, spielregelnEinfach);
                        boolean nurFuerAss = true;
                        if(spielregelnEinfach.assGelegt(spiel.getAblageStapel())){
                            while(nurFuerAss) {
                                String antwortFrage2 = view.frageKarteZiehen();
                                antwortFrage2.toLowerCase();
                                if (antwortFrage2.equals("a")) {
                                    boolean t =  prüfeKarte(spiel, spielregelnEinfach);
                                    if(t){
                                        wunschFarbe(spiel, spielregelnEinfach);
                                        if(spielregelnEinfach.assGelegt(spiel.getAblageStapel())){
                                        }
                                        else{
                                            nurFuerAss = false;
                                        }
                                    }
                                    else {
                                        view.falscheKarte();
                                        // nurFuerAss = false;
                                    }
                                } else if (antwortFrage.equals("z")) {
                                    zieheKarte(spiel.getAktiverSpieler());
                                    nurFuerAss = false;
                                } else {
                                    view.falscheAntwort();
                                }
                            }}
                        machWeiter = false;
                    }
                    else{
                        view.falscheKarte();
                    }
                }else if (antwortFrage.equals("z")) {
                    zieheKarte(spiel.getAktiverSpieler());
                    machWeiter = false;
                } else {
                    view.falscheAntwort();
                }
            }
            prüfeMauUndMauMau(spiel);
            spielregelnEinfach.nächsterSpielerIstDran(spiel);
            System.out.println("Als nächstes gehe ich in die view");
            view.zeigeAktivenSpieler(spiel.getAktiverSpieler());
            System.out.println("Ich war in der View");

        }
    }





        /*
        SpielregelnService spielregelnEinfach = new SpielregelnImpl();
        spiel.setAktiverSpieler((Spieler) spiel.getSpielerListe().get(0));
        view.zeigeAktivenSpieler(spiel.getAktiverSpieler());
        boolean boo = true;
        while(boo) {
            anzeigeObersteKarte(spiel);
            zeigeKartenSpielerHand(spiel.getAktiverSpieler());
            boolean machWeiter = true;
            while (machWeiter) {
                String antwortFrage = view.frageKarteZiehen();
                if (antwortFrage.equalsIgnoreCase("A")) {
                    prüfeKarte(spiel, spielregelnEinfach);
                    machWeiter = false;
                } else if (antwortFrage.equalsIgnoreCase("Z")) {
                    zieheKarte(spiel.getAktiverSpieler());
                    machWeiter = false;
                } else {
                    view.falscheAntwort();
                }
            }
            prüfeMauUndMauMau(spiel);
            spielregelnEinfach.nächsterSpielerIstDran(spiel);
            view.zeigeAktivenSpieler(spiel.getAktiverSpieler());
        }
    }
*/

    /**
     * der Spielablauf mit den Sonderregeln
     * @param spiel welches gespielt wird
     */
    private void spieleMitSonderRegeln(Spiel spiel) {
        SpielregelnService spielregelnSonder = new SonderregelnImpl();
        spiel.setAktiverSpieler((Spieler) spiel.getSpielerListe().get(0));
        view.zeigeAktivenSpieler(spiel.getAktiverSpieler());
        boolean boo = true;
        boolean ersteRunde = true;
        while (boo) {
            anzeigeObersteKarte(spiel);

            zeigeKartenSpielerHand(spiel.getAktiverSpieler());
            boolean machWeiter = true;
            while (machWeiter) {
               boolean u =  spielregelnSonder.prüfeAufSiebenGelegt(spiel);
                if (u){
                    anzahlZiehen = 2;
                    for (int i = 0; i < anzahlZiehen; i++) {
                        kartenSpielerService.zieheKarte(spiel.getAktiverSpieler().getSpielerHand(), spiel.getZiehStapel());
                    }
                }
                String antwortFrage = view.frageKarteZiehen();
                antwortFrage.toLowerCase();
                if (antwortFrage.equals("a")) {
                  boolean r =  prüfeKarte(spiel, spielregelnSonder);
                  if (r){
                      wunschFarbe(spiel, spielregelnSonder);
                      boolean nurFuerAss = true;
                    if(spielregelnSonder.assGelegt(spiel.getAblageStapel())){
                        while(nurFuerAss) {
                        String antwortFrage2 = view.frageKarteZiehen();
                        antwortFrage2.toLowerCase();
                        if (antwortFrage2.equals("a")) {
                                boolean t =  prüfeKarte(spiel, spielregelnSonder);
                                if(t){
                                    wunschFarbe(spiel, spielregelnSonder);
                                    if(spielregelnSonder.assGelegt(spiel.getAblageStapel())){
                                    }
                                    else{
                                        nurFuerAss = false;
                                    }
                                }
                                else {
                                    view.falscheKarte();
                                   // nurFuerAss = false;
                                }
                        } else if (antwortFrage.equals("z")) {
                            zieheKarte(spiel.getAktiverSpieler());
                            nurFuerAss = false;
                        } else {
                            view.falscheAntwort();
                        }
                    }}
                    machWeiter = false;
                }
                  else{
                        view.falscheKarte();
                  }
                }else if (antwortFrage.equals("z")) {
                    zieheKarte(spiel.getAktiverSpieler());
                    machWeiter = false;
                } else {
                    view.falscheAntwort();
                }
            }
            prüfeMauUndMauMau(spiel);
            spielregelnSonder.nächsterSpielerIstDran(spiel);
            view.zeigeAktivenSpieler(spiel.getAktiverSpieler());
        }
    }

    /**
     * prüft ob zum richtigen Zeitpunkt Mau oder MauMau gesagt wurde
     * @param spiel in dem es geprüft werden soll
     */
    private void prüfeMauUndMauMau(Spiel spiel){
        String aussage = view.wasSagen();

        if(aussage.equalsIgnoreCase("mau") || aussage.equalsIgnoreCase("maumau")){
            spielerHatWasGesagt(aussage, spiel.getAktiverSpieler());
        }
        if(aussage.equalsIgnoreCase("nein")) {
           System.out.println("ok dann nicht");
        }

        //wenn Mau Mau gesagt wurde und rechtmäßig ist wird das Spiel beendet
        if(spiel.getAktiverSpieler().isMaumauGesagt() & spiel.getAktiverSpieler().getSpielerHand().getKarten().size() == 0){
            view.spielerHatGewonnen();
            System.exit(0);
        }

        // Strafziehen, falls unrechtmäßig "Mau" gesagt
        if(spiel.getAktiverSpieler().isMauGesagt() & spiel.getAktiverSpieler().getSpielerHand().getKarten().size() != 1) {
            zieheKarte(spiel.getAktiverSpieler());
        }

        // Strafziehen, falls unrechtmäßig "MauMau" gesagt
        if(spiel.getAktiverSpieler().isMaumauGesagt() & spiel.getAktiverSpieler().getSpielerHand().getKarten().size() != 0) {
            zieheKarte(spiel.getAktiverSpieler());
        }

        // Strafziehen, falls "Mau" vergessen
        if(!spiel.getAktiverSpieler().isMauGesagt() & spiel.getAktiverSpieler().getSpielerHand().getKarten().size() == 1) {
            zieheKarte(spiel.getAktiverSpieler());
        }

        // Strafziehen, falls "MauMau" vergessen
        if(!spiel.getAktiverSpieler().isMaumauGesagt() & spiel.getAktiverSpieler().getSpielerHand().getKarten().size() == 0) {
            zieheKarte(spiel.getAktiverSpieler());
        }
    }

    /**
     * prüft ob sich eine Wunschfarbe gewünscht werden kann und setzt diese
     * @param spiel Spiel in dem es geprüft werden soll
     * @param spielregelnSonder Sonderregeln zum Spiel
     */
    private void wunschFarbe(Spiel spiel, SpielregelnService spielregelnSonder) {
        boolean wuenscheDirWas = true;
        if (spielregelnSonder.mussSichFarbeWuenschen(spiel.getAblageStapel())) {
            spiel.getAblageStapel().setWunschFarbe("");
            while(wuenscheDirWas) {
                String gewuenschteFarbe = view.farbeWählen();
                if (gewuenschteFarbe.equals("pik") || gewuenschteFarbe.equals("herz") || gewuenschteFarbe.equals("kreuz") || gewuenschteFarbe.equals("karo")) {
                    spiel.getAblageStapel().setWunschFarbe(gewuenschteFarbe);
                    Karte karte = new Karte(gewuenschteFarbe, "_____");
                    spiel.getAblageStapel().getAblagekarten().add(karte);
                    wuenscheDirWas = false;
                } else {
                    view.falscheAntwort2();
                }
            }
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
        view.karteAnzeigenAblagestapel(wert, farbe);
    }

    /**
     * fragt nach welcher Regel gespielt werden soll
     */
    private String frageNachSpielregeln() {
        boolean weitermachen = true;
        String returnwert = null;
        while (weitermachen) {
            String x = view.regelnAnzeigenundAuswählen();
            if (x.equals("e")) {
                weitermachen = false;
                returnwert = "einfach";
            } else if (x.equals("s")) {
                weitermachen = false;
                returnwert = "sonder";
            } else {
                returnwert = "nichts";
            }
        }
        return returnwert;
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
    private void zeigeKartenSpielerHand(Spieler spieler) {
        String x = view.kartenAnzeigenSpielerHand();
        x.toLowerCase(); // falls der Spieler Großbuchstaben eingibt
        if (x.equals("y")){
            System.out.println(spieler.getSpielerHand().getKarten().size());
            for (int i = 0; i < spieler.getSpielerHand().getKarten().size(); i++) {
                String wert = spieler.getSpielerHand().getKarten().get(i).getKartenWert();
                String farbe = spieler.getSpielerHand().getKarten().get(i).getKartenFarbe();
                view.infoZurKarte(wert, farbe);
            }
        } else if (x.equals("n")){
        } else {
            view.kartenAnzeigenSpielerHand();
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
    private boolean prüfeKarte(Spiel spiel, SpielregelnService spielregelnService) {
        boolean i = true;
        boolean rückgabe = false;
        while (i) {
            int wert = view.karteAblegen();
            Spieler spieler = spiel.getAktiverSpieler();
            SpielerHand spielerHand = spieler.getSpielerHand();
            Karte karte = spielerHand.getKarten().get(wert - 1);
            AblageStapel ablage = spiel.getAblageStapel();
            System.out.println(spielerHand.getKarten().contains(karte));
            if (spielerHand.getKarten().contains(karte)) {
                boolean x = spielregelnService.pruefeKarte(karte, ablage);
                System.out.println("pruefekarte ist" + x);
                if (x) {
                    kartenSpielerService.legeKarteAb(spielerHand, karte, ablage);
                    i = false;
                    rückgabe = true;
                }
                else{
                    i = false;
                    rückgabe = false;
                }
            }
            else if (spielerHand.getKarten().size() < wert) {
                view.falscheKarte();
                spieleMitSonderRegeln(spiel);
            }
            else {
                view.falscheKarte();
                rückgabe = false;
            }

        }
        return rückgabe;
    }
    }


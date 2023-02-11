package groupone.ui_management.implementation;

import groupone.kartenstapel_management.classes.AblageStapel;
import groupone.kartenstapel_management.classes.Karte;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.kartenstapel_management.implementation.KartenSpielImpl;
import groupone.kartenstapel_management.services.KartenSpielService;
import groupone.spiel_management.DAO.ISpielDAO;
import groupone.spiel_management.DAO.SpielDAO;
import groupone.spiel_management.classes.Spiel;
import groupone.spiel_management.implementation.KartenSpielerImpl;
import groupone.spiel_management.implementation.SpielImpl;
import groupone.spiel_management.services.KartenSpielerService;
import groupone.spiel_management.services.SpielService;
import groupone.spieler_management.classes.Spieler;
import groupone.spieler_management.classes.SpielerInterface;
import groupone.spieler_management.classes.VirtuellerSpieler;
import groupone.spieler_management.implementation.SpielerImpl;
import groupone.spieler_management.implementation.VirtuellerSpielerImpl;
import groupone.spieler_management.services.SpielerService;
import groupone.spieler_management.services.VirtuellerSpielerService;
import groupone.spielregeln_management.implementation.SonderregelnImpl;
import groupone.spielregeln_management.implementation.SpielregelnImpl;
import groupone.spielregeln_management.services.SpielregelnService;
import groupone.ui_management.service.ControllerService;

import java.util.ArrayList;
import java.util.List;


public class ControllerImpl implements ControllerService {

    //Controller - kennt die schnittstellen der anwendungslogik
    private Spiel spiel;
    private View view = new View();
    private List<SpielerInterface> spielerList = new ArrayList<>();
    private KartenSpielService kartenSpiel;
    private AblageStapel ablageStapel;
    private ZiehStapel ziehStapel;
    private SpielService spielService;
    private KartenSpielerService kartenSpielerService;
    private SpielregelnService spielregelnService;
    private int anzahlZiehen;
    private ISpielDAO spielDAO = new SpielDAO();
    private SpielerService spielerService = new SpielerImpl();
    private VirtuellerSpielerService virtuellerSpielerService = new VirtuellerSpielerImpl();

    public ControllerImpl() {
    }


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
            Long z = view.idFürSpiel();
            String rueckgabe = spielDAO.loescheSpiel(z);
            if(rueckgabe.equals("gelöscht")){
                view.loescheSpiel();
                hauptMenue();
            } else{
                hauptMenue();
            }
        } else if (x.equals("e")) {
            System.exit(0);
        }
    }

    /**
     * Methode läd ein existierendes Spiel aus der Datenbank oder erstellt ein neues Spiel
     */
    public void starteSpiel() {

        String x = view.startGame();

        if (x.equals("n")) {
            this.ablageStapel = new AblageStapel();
            this.ziehStapel = new ZiehStapel();
            this.kartenSpiel = new KartenSpielImpl();
            this.spielService = new SpielImpl(this.kartenSpiel);
            this.kartenSpielerService = new KartenSpielerImpl();

            // erster Spieler wird hinzugefügt
            Long id2 = view.idFürSpiel();
            String spielername = view.spielerName();
            long id = view.spielerId();
            Spieler spieler = spielerService.spielerErstellen(id, spielername);
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
                        spieler = spielerService.spielerErstellen(id, spielername);
                        spielerList.add(spieler);
                        if (spielerList.size() == 4) {
                            break;
                        }

                    } else if (frage.equals("v")) {
                        SpielerInterface virtuellerSpieler = virtuellerSpielerService.spielerErstellen();
                        spielerList.add(virtuellerSpieler);
                        if(spielerList.size() == 4) {
                            break;
                        }
                    }

            }
            spiel = spielService.erstelleSpiel(spielerList, 0, ablageStapel, ziehStapel);
            spiel.setId(id2);
             String z = frageNachSpielregeln();
             if(z.equals("einfach")){
                 spieleMitEinfachenRegeln(spiel);
             }
             else if(z.equals("sonder")){
                 spieleMitSonderRegeln(spiel);
             }
             else if(!z.equalsIgnoreCase("sonder") && !z.equalsIgnoreCase("einfach")){
                 hauptMenue();
             }
        } else if (x.equals("l")) {
            // lade aus DB und starte spiel
            ISpielDAO spielDAO = new SpielDAO();
            Long id = view.idFürSpiel();
            spiel = spielDAO.findById(id);
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
        spiel.setAktiverSpieler(spiel.getSpielerListe().get(0));
        view.zeigeAktivenSpieler(spiel.getAktiverSpieler());
        boolean boo = true;
        System.out.println("Ich bin hier angekommen!");
        while (boo) {
            anzeigeObersteKarte(spiel);
            if(spiel.getAktiverSpieler().isIstVirtuell() == true) {
                virtuellerSpielerIstDran((VirtuellerSpieler) spiel.getAktiverSpieler(), spiel);
            } else {
            zeigeKartenSpielerHand(spiel.getAktiverSpieler());
            boolean machWeiter = true;
            while (machWeiter) {
                boolean u = spielregelnEinfach.prüfeAufSiebenGelegt(spiel);
                if (u) {
                    anzahlZiehen = 2;
                    for (int i = 0; i < anzahlZiehen; i++) {
                        kartenSpielerService.zieheKarte(spiel.getAktiverSpieler().getSpielerHand(), spiel.getZiehStapel());
                    }
                }
                String antwortFrage = view.frageKarteZiehen();
                antwortFrage.toLowerCase();
                if (antwortFrage.equals("a")) {
                    boolean r = prüfeKarte(spiel, spielregelnEinfach);
                    if (r) {
                        wunschFarbe(spiel, spielregelnEinfach);
                        boolean nurFuerAss = true;
                        if (spielregelnEinfach.assGelegt(spiel.getAblageStapel())) {
                            while (nurFuerAss) {
                                String antwortFrage2 = view.frageKarteZiehen();
                                antwortFrage2.toLowerCase();
                                if (antwortFrage2.equals("a")) {
                                    boolean t = prüfeKarte(spiel, spielregelnEinfach);
                                    if (t) {
                                        wunschFarbe(spiel, spielregelnEinfach);
                                        if (spielregelnEinfach.assGelegt(spiel.getAblageStapel())) {
                                        } else {
                                            nurFuerAss = false;
                                        }
                                    } else {
                                        view.falscheKarte();
                                        int zurueck = view.zurueckGehen();
                                        if(zurueck == 1){
                                            break;
                                        } else {
                                        }
                                    }
                                } else if (antwortFrage.equals("z")) {
                                    zieheKarte(spiel.getAktiverSpieler());
                                    nurFuerAss = false;
                                } else {
                                    view.falscheAntwort();
                                }
                            }
                        }
                        machWeiter = false;
                    } else {
                        view.falscheKarte();
                    }
                } else if (antwortFrage.equals("z")) {
                    zieheKarte(spiel.getAktiverSpieler());
                    machWeiter = false;
                } else {
                    view.falscheAntwort();
                }
            }}
            if(spiel.getAktiverSpieler().isIstVirtuell() == true){
                virtuellerSpielerAntwortet((VirtuellerSpieler) spiel.getAktiverSpieler(), spiel);
                spielregelnEinfach.nächsterSpielerIstDran(spiel);
                view.zeigeAktivenSpieler(spiel.getAktiverSpieler());
                System.out.println("Virtueller Spieler verlässt einsatz");
            } else{
            prüfeMauUndMauMau(spiel);
                if(spiel.getAktiverSpieler().getSpielerHand().getAnzahlKarten() == 0){
                    spielService.gibGewinneraus(spiel);
                } else{
            spielregelnEinfach.nächsterSpielerIstDran(spiel);
            System.out.println("Als nächstes gehe ich in die view");
            view.zeigeAktivenSpieler(spiel.getAktiverSpieler());
            System.out.println("Ich war in der View");
           // spielDAO.speichereSpiel(spiel);
            System.out.println("hier hier");
        }}}
    }


    /**
     * der Spielablauf mit den Sonderregeln
     * @param spiel welches gespielt wird
     */
    private void spieleMitSonderRegeln(Spiel spiel) {
        SpielregelnService spielregelnSonder = new SonderregelnImpl();
        spiel.setAktiverSpieler(spiel.getSpielerListe().get(0));
        view.zeigeAktivenSpieler(spiel.getAktiverSpieler());
        boolean boo = true;
        boolean ersteRunde = true;
        while (boo) {
            anzeigeObersteKarte(spiel);
            if(spiel.getAktiverSpieler().isIstVirtuell() == true) {
                virtuellerSpielerIstDran((VirtuellerSpieler) spiel.getAktiverSpieler(), spiel);
            } else {
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
                                    int zurueck = view.zurueckGehen();
                                    if(zurueck == 1){
                                        break;
                                    } else {
                                    }
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
            }}
            if(spiel.getAktiverSpieler().isIstVirtuell() == true){
                virtuellerSpielerAntwortet((VirtuellerSpieler) spiel.getAktiverSpieler(), spiel);
                spielregelnSonder.nächsterSpielerIstDran(spiel);
                view.zeigeAktivenSpieler(spiel.getAktiverSpieler());
                System.out.println("Virtueller Spieler verlässt einsatz");
            } else{
            prüfeMauUndMauMau(spiel);
            if(spiel.getZiehStapel().getZiehkarten().size() == 1) {
                System.out.println("Jezt wird abgegeben");
                kartenSpiel.kartenDesAblagestapelsDemZiehstapelUebergeben(spiel.getAblageStapel(), spiel.getZiehStapel());
                kartenSpiel.mischeKarten(spiel.getZiehStapel().getZiehkarten());
            }if(spiel.getAktiverSpieler().getSpielerHand().getAnzahlKarten() == 0){
                spielService.gibGewinneraus(spiel);
                } else{
            spielregelnSonder.nächsterSpielerIstDran(spiel);
            view.zeigeAktivenSpieler(spiel.getAktiverSpieler());
        }}}
    }

    /**
     * prüft ob zum richtigen Zeitpunkt Mau oder MauMau gesagt wurde
     * @param spiel in dem es geprüft werden soll
     */
    private void prüfeMauUndMauMau(Spiel spiel){
        String aussage = view.wasSagen();

        if(aussage.equalsIgnoreCase("mau") || aussage.equalsIgnoreCase("maumau")){
            spielerHatWasGesagt(aussage, (Spieler) spiel.getAktiverSpieler());
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
     * @param spiel - Spiel das den Ablagestapel liefert
     */
    private void anzeigeObersteKarte(Spiel spiel) {

        String wert = spiel.getAblageStapel().getAblagekarten().get(spiel.getAblageStapel().getAblagekarten().size() - 1).getKartenWert();
        String farbe = spiel.getAblageStapel().getAblagekarten().get(spiel.getAblageStapel().getAblagekarten().size() - 1).getKartenFarbe();
        view.karteAnzeigenAblagestapel(wert, farbe);
    }

    /**
     * fragt nach welcher Regel gespielt werden soll
     * @return die gewählte Regel
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
    private void spielerHatWasGesagt(String aussage, Spieler spieler) {
        if(aussage.equals("mau")){
            spielerService.sageMau(spieler);
            view.spielerHatMauGesagt();
        }else if(aussage.equals("maumau")) {
            spielerService.maumau(spieler);
            view.spielerHatMauMauGesagt();
        }
    }

    /**
     * prüft was der virtuelle Spieler gesagt hat
     * @param aussage die Aussage des virtuellen Spielers
     * @param spieler der dazugehörige virtuelle Spieler
     */
    private void virtuellerSpielerHatWasGesagt(String aussage, VirtuellerSpieler spieler) {
        if(aussage.equals("mau")){
            virtuellerSpielerService.sageMau(spieler);
            view.spielerHatMauGesagt();
        }else if(aussage.equals("maumau")) {
            virtuellerSpielerService.maumau(spieler);
            view.spielerHatMauMauGesagt();
        }
    }

    /**
     * Methode die die Karten der Spielerhand des Spielers anzeigt
     * @param spieler - Spieler dessen Hand angezeigt werden soll
     */
    private void zeigeKartenSpielerHand(SpielerInterface spieler) {
        String x = view.kartenAnzeigenSpielerHand();
        x.toLowerCase(); // falls der Spieler Großbuchstaben eingibt
        if (x.equals("y")){

            for (int i = 0; i < spieler.getSpielerHand().getKarten().size(); i++) {
                String wert = spieler.getSpielerHand().getKarten().get(i).getKartenWert();
                String farbe = spieler.getSpielerHand().getKarten().get(i).getKartenFarbe();
                view.infoZurKarte(i+1,wert, farbe);
            }
        } else if (x.equals("n")){
        } else {
            view.kartenAnzeigenSpielerHand();
        }

    }

    /**
     * Methode die eine Karte zur SpielerHand inzufügt und die Karte vom ZiehStapel entfernt
     * @param spieler - Spieler der eine Karte ziehen soll  */
    private void zieheKarte(SpielerInterface spieler) {
        view.karteZiehen();
        kartenSpielerService.zieheKarte(spieler.getSpielerHand(), spiel.getZiehStapel());

    }

    /**
     * Methode die prüft eine Fehlermeldung zur falschen Karte gibt
     * @param spiel, in welchem man sich gerade befindet
     * @param spielregelnService - Spielregeln die zum Spiel gehören
     * @return true wenn abgelegt darf und false wenn nicht abgelegt werden darf
     */
    private boolean prüfeKarte(Spiel spiel, SpielregelnService spielregelnService) {
        boolean i = true;
        boolean rückgabe = false;
        while (i) {
            System.out.println("Hallo Hierher");
            int wert = view.karteAblegen();
            SpielerInterface spieler = spiel.getAktiverSpieler();
            SpielerHand spielerHand = spieler.getSpielerHand();
            if(wert > spielerHand.getAnzahlKarten()){
                rückgabe = false;
            } else {
            Karte karte = spielerHand.getKarten().get(wert - 1);
            AblageStapel ablage = spiel.getAblageStapel();

            if (spielerHand.getKarten().contains(karte)) {
                boolean x = spielregelnService.pruefeKarte(karte, ablage);

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
    return rückgabe;
    }

    /**
     * prüft die Möglichkeit der Kartenablegung und des ziehens für den virtuellen Spieler
     * @param spieler der virtuelle Spieler
     * @param spiel das Spiel welches geprüft wird
     */
    public void virtuellerSpielerIstDran(VirtuellerSpieler spieler, Spiel spiel) {
        for(int i = 0; i < spieler.getSpielerHand().getAnzahlKarten(); i++) {
            if(spieler.getSpielerHand().getKarten().get(i).getKartenWert().equals(spiel.getAblageStapel().getAblagekarten()
                    .get(spiel.getAblageStapel().getAblagekarten().size() - 1).getKartenWert()) || spieler.getSpielerHand().getKarten().get(i).getKartenFarbe().equals(spiel.getAblageStapel().getAblagekarten()
                    .get(spiel.getAblageStapel().getAblagekarten().size() - 1).getKartenFarbe())) {
                kartenSpielerService.legeKarteAb(spieler.getSpielerHand(), spieler.getSpielerHand().getKarten().get(i), spiel.getAblageStapel());
            } else{
                kartenSpielerService.zieheKarte(spieler.getSpielerHand(), spiel.getZiehStapel());
            }
            break;
        }
    }

    /**
     * gibt die ANtwort des virtuellenSpielers
     * @param spieler der virtuelle Spieler
     * @param spiel in dem Spiel in dem er sich befindet
     */
    public void virtuellerSpielerAntwortet(VirtuellerSpieler spieler, Spiel spiel) {
        if(spieler.getSpielerHand().getAnzahlKarten() == 1) {
            view.spielerHatMauGesagt();
        } else if(spieler.getSpielerHand().getAnzahlKarten() == 0) {
            view.spielerHatMauMauGesagt();
        } else{
            System.out.println("Ich sage gar nix");
        }
    }


    }


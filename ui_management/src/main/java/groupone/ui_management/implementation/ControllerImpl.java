package groupone.ui_management.implementation;

import groupone.ui_management.service.ControllerService;

public class ControllerImpl implements ControllerService {
    @Override
    public void startGame() {
        System.out.println("Möchtest du ein neues Spiel starten oder ein vorhandenes Spiel laden?");

    }

    @Override
    public void spielerhinzufügen() {
        System.out.println("Soll ein weiterer Spieler hinzugefügt werden?");

    }

    @Override
    public void spielerHatMauGesagt() {
        System.out.println("Spieler hat Mau gesagt");
    }

    @Override
    public void regelnAnzeigen() {
        System.out.println("Einfache Regeln :-----------------");
        System.out.println("1. Jeder Spieler erhält 5 Karten");
        System.out.println("2. Der Spieler muss eine Karte ablegen, die entweder die gleiche Farbe oder den gleichen Wert hat");
        System.out.println("3. Wenn der Spieler keine Karte ablegen kann, muss er eine Karte ziehen");
        System.out.println("Sonderregeln :-----------------");
        System.out.println("1. ....");
        System.out.println("2. ...");
        System.out.println("3. ...");
        System.out.println("4. ..");
    }

    @Override
    public void regelnAuswählen() {
        System.out.println("Welches Regelwerk soll gespielt werden?");
    }

    @Override
    public void kartenAnzeigenSpielerHand() {
        System.out.println("Karten des Spielers anzeigen");
    }

    @Override
    public void karteAnzeigenAblagestapel() {
        System.out.println("Karte des Ablagestapels anzeigen");
    }

    @Override
    public void karteAblegen() {
        System.out.println("Welche Karte soll abgelegt werden?");
    }

    @Override
    public void falscheKarte() {
        System.out.println("Falsche Karte abgelegt");
    }

    @Override
    public void farbeWählen() {
        System.out.println("Wähle bitte eine Farbe");
    }

    @Override
    public void karteZiehen() {
        System.out.println("Karte ziehen");
    }
}

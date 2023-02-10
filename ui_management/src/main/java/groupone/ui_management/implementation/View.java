package groupone.ui_management.implementation;



import groupone.spieler_management.classes.Spieler;

import java.util.List;
import java.util.Scanner;

public class View {

    Scanner scanner = new Scanner(System.in);

    /**
     * startet das Hauptmenü des Spiels.
     * @return die Eingabe des Spielers.
     */
    public String hauptMenue() {
        System.out.println("Was möchtest du tun? Wähle aus!");
        System.out.println("Spiel starten [S]");
        System.out.println("Spiel löschen [D]");
        System.out.println("Programm beenden [E]");
        String eingabe = scanner.next().toLowerCase();
        return eingabe;
    }

//View nur die print statements und scanner .. nur aus und eingabe

    /**
     * fragt ob ein neues Spiel gestartet werden soll oder ein vorhandenes Spiel geladen werden soll
     * @Return Die Eingabe des Spielers -> Wo soll es weitergehen
     */
    public String startGame() {
        System.out.println("Möchtest du ein neues Spiel starten oder ein vorhandenes Spiel laden?");
        System.out.println("Neues Spiel starten [N]");
        System.out.println("Vorhandenes Spiel laden [L]");
        System.out.println("Zurück ins Hauptmenü [H]");
        String x = scanner.next().toLowerCase();
        return x;
    }

    /**
     * zeigt den Namen des aktiven Spielers an
     * @param spieler der aktive Spieler
     */
    public void zeigeAktivenSpieler(Spieler spieler) {
        System.out.println("Spieler " + spieler.getName() + " ist am Zug!");
    }

    /**
     * fragt ob das Spiel gelöscht werden soll
     *
     * @return Das Spiel was gelöscht werden muss
     */
    public String loescheSpiel() {
        System.out.println("Welchen Spielstand möchtest du löschen?");
        // Spielstände aus der Datenbank auslesen
        String x = scanner.next().toLowerCase();
        return x;
    }

    /**
     * fragt nach Spielereigenschaften Name
     * @return spielername
     */
    public String spielerName() {
        System.out.println("Wie soll der Spieler heißen?");
        String name = scanner.next();
        return name;
    }

    /**
     * fragt nach Spielereigenschaften ID
     * @return spielerid
     */
    public long spielerId() {
        System.out.println("Wie ist die Spieler ID?");
        long id = scanner.nextLong();
        return id;
    }

    /**
     * Methode die die Spielernamen aus der UI entgegennimmt und in die Klasse Spieler speichert
     * @return ja wenn ein spieler hinzugefügt werden soll
     */
    public String spielerhinzufügen() {
        // System.out.println("Soll ein weiterer Spieler hinzugefügt werden? (ja/nein)");
        System.out.println("Soll ein menschlicher Spieler [m], ein virtueller Spieler [v] oder kein weiter Spieler [nein] hinzugefügt werden?");
        String x = scanner.next().toLowerCase();
        return x;
    }

    /**
     * Methode zeigt an welcher Spieler Mau gesagt hat
     */
    public void spielerHatMauGesagt() {
        System.out.println("Spieler hat Mau gesagt.");

    }

    /**
     * Methode zeigt an welcher Spieler MauMau gesagt hat. Spieler hat daraufhin gewonnen, da alle Karten abgelegt
     */
    public void spielerHatMauMauGesagt() {

        System.out.println("Spieler hat MauMau gesagt.");

    }

    /**
     * Methode die die Regeln anzeigt die im Spiel gelten können
     * @return  e für einfache Regeln ,s für Sonderregeln
     */
    public String regelnAnzeigenundAuswählen() {
        System.out.println("Einfache Regeln :-----------------");
        System.out.println("1. Jeder Spieler erhält 5 Karten");
        System.out.println("2. Der Spieler muss eine Karte ablegen, die entweder die gleiche Farbe oder den gleichen Wert hat.");
        System.out.println("3. Wenn der Spieler keine Karte ablegen kann, muss er eine Karte ziehen.");
        System.out.println("Sonderregeln :-----------------");
        System.out.println("Bei Bube, darf man sich eine Farbe wünschen\n" +
                "Aber auf einen Buben darf kein weiterer gelegt werden.\n" +
                "Legt einer eine \"7\", so muss der nächste Spieler 2 Karten ziehen.\n" +
                "Legt einer eine \"8\", so muss der nachfolgende Aussetzen.\n" +
                "Legt hingegen einer eine \"9\" so wird die Spielrichtung umgedreht.\n" +
                "Tippe e für einfache Regeln oder s für Sonderregeln");
        ;
        String x = scanner.next().toLowerCase();
        return x;
    }


    /**
     * Methode die die Karten des Spielers anzeigt
     * @return y für ja und n für nein
     */
    public String kartenAnzeigenSpielerHand() {
        System.out.println("Karten des Spielers anzeigen. y/n ?");
        String x = scanner.next().toLowerCase();
        return x;
    }

    /**
     * Methode die die Karte des Ablagestapels anzeigt
     */
    public void karteAnzeigenAblagestapel(String wert, String farbe) {

        System.out.println("Karte des Ablagestapels anzeigen.");
        System.out.println("Karte: " + wert + " " + farbe);

    }

    /**
     * wird abgefragt welche Karte auf den Ablagestapel gelegt werden soll
     * @return die Karteneigenschaften in einer Liste
     */
    public List<String> karteAblegen() {

        System.out.println("Welche Karte soll abgelegt werden?");
        System.out.println("Nummer: ");
        int nummer = scanner.nextInt();
        return nummer;
    }

    /**
     * wenn eine falsche Karte abgelegt werden möchte
     */
    public void falscheKarte() {
        System.out.println("Falsche Karte abgelegt.");
    }

    /**
     * wenn eine Karte abgelegt werden soll die nicht im Handkartenstapel ist
     */
    public void falscheAntwort() {
        System.out.println("Bitte wählen Sie nur zwischen ablegen[A] und ziehen[Z]!");
    }

    /**
     * wenn eine falsche Antwort geben wird
     */
    public void falscheAntwort2() {
        System.out.println("Bitte gebe was sinnvolles ein !");
    }

    /**
     * Fragt den Spieler, ob er eine Karte ablegen möchte oder ziehen möchte
     * @return Die Antwort auf die Frage
     */
    public String frageKarteZiehen(){
        System.out.println("Möchtest du eine Karte ablegen[A] oder ziehen[Z]?");
        String antwort = scanner.next();
        antwort = antwort.toLowerCase();
        return antwort;
    }

    /**
     * fordert dem Spieler auf, seine Wunschfarbe anzugeben
     * @return die Farbe die der Spieler gewählt hat
     */
    public String farbeWählen() {

        System.out.println("Wähle bitte eine Farbe. - KARO, PIK, HERZ, KREUZ");
        String x = scanner.next();
        x =  x.toLowerCase();
        return x;
    }

    /**
     * Methode die den Spieler auffordert eine Karte zu ziehen
     */
    public void karteZiehen() {
        System.out.println("Karte ziehen.");

    }

    /**
     * gibt den Kartenwert und die Farbe der Karte an
     */
    public void infoZurKarte(int i, String kartenwert, String farbe) {
        System.out.println(i + ": " + kartenwert + " " + farbe);
    }

    /**
     * Spieler kann, wenn er nur noch eine Karte in der Hat oder alle Karten abgelegt hat,
     * "Mau" bzw. "MauMau" sagen.
     * @param spieler Spieler, der was sagen möchte
     * @return die getroffene Aussage
     */
    public String wasSagen() {
        System.out.println("Möchtest du was sagen? [Mau] oder [MauMau] oder [Nein]");
        String x = scanner.next().toLowerCase();
        return x;
    }
}

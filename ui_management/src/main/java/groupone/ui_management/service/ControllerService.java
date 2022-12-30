package groupone.ui_management.service;

public interface ControllerService {

    /**
     * fragt ob ein neues Spiel gestartet werden soll oder ein vorhandenes Spiel geladen werden soll
     */
    public void startGame();

    /**
     * Methode die die Spielernamen aus der UI entgegennimmt und in die Klasse Spieler speichert
     */
    public void spielerhinzufügen();

    /**
     * Methode zeigt an welcher Spieler Mau gesagt hat
     */
    public void spielerHatMauGesagt();

    /**
     * Methode zeigt an welcher Spieler MauMau gesagt hat. Spieler hat daraufhin gewonnen, da alle Karten abgelegt
     */
    public void spielerHatMauMauGesagt();

    /**
     * Methode die die Regeln anzeigt die im Spiel gelten können
     */
    public void regelnAnzeigen();

    /**
     * fragt nach welchen Regelwerk gespiel werden soll
     */
    public void regelnAuswählen();

    /**
     * Methode die die Karten des Spielers anzeigt
     */
    public void kartenAnzeigenSpielerHand();

    /**
     * Methode die die Karte des Ablagestapels anzeigt
     */
    public void karteAnzeigenAblagestapel();

    /**
     * wird abgefragt welche Karte auf den Ablagestapel gelegt werden soll
     */
    public void karteAblegen();

    /**
     * wenn eine falsche Karte abgelegt werden möchte
     */
    public void falscheKarte();

    /**
     * fordert dem Spieler auf seine Wunschfarbe anzugeben
     */
    public void farbeWählen();

    /**
     * Methode die den Spieler auffordert eine Karte zu ziehen
     */
    public void karteZiehen();


}

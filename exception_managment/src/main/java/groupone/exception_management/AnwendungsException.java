package groupone.exception_management;

public class AnwendungsException extends Exception{

    /**
     * allgemeine AnwendungsException für Fehler die vom Benutzer verursacht werden
     */
    public AnwendungsException() {
        super("Hier ist ein Fehler aufgetreten");
    }

    /**
     * spefizierte AnwendungsException für Fehler die vom Benutzer verursacht werden
     *
     * @param fehlermeldung - Fehlermeldung die dem Benutzer angezeigt wird
     */
    public AnwendungsException(String fehlermeldung) {
        super("Hier ist ein Fehler aufgetreten. Fehlermeldung: " + fehlermeldung);
    }
}

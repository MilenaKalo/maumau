package groupone.exception_management;

public class TechnischeException extends RuntimeException {

    /**
     * allgemeine Technische Exception für Fehler die nicht vom Benutzer verursacht werden
     */
    public TechnischeException() {
        super("Hier ist ein technischer Fehler aufgetreten");
    }

    /**
     * spefizierte Technische Exception für Fehler die nicht vom Benutzer verursacht werden
     *
     * @param fehlermeldung - Fehlermeldung die dem Benutzer angezeigt wird
     */
    public TechnischeException(String fehlermeldung) {
        super("Hier ist ein technischer Fehler aufgetreten. Fehlermeldung: " + fehlermeldung);
    }
}

package groupone.kartenstapel_management.DAO;

import groupone.kartenstapel_management.classes.Karte;

public interface IKarteDAO {

    /**
     * Methode dient zum Aufrufen einer Karte mittels dessen ID
     * @param id der aufzurufenden Karte
     * @return die aufzurufende Karte
     */
    Karte findById(Long id);

    /**
     * Methode dient zum Speichern der Karte in der Datenbank
     * @param karte welche zu speichern ist
     * @return gespeicherte Karte
     */
    Karte speichereKarte(Karte karte);

    /**
     * Methode dient zum Löschen einer Karte
     * @param id der zu löschenden Karte
     */
    void loescheKarte(Long id);
}

package groupone.spiel_management.DAO;

import groupone.spiel_management.classes.Spiel;

public interface ISpielDAO {

    /**
     * Methode dient zum Aufrufen eines Spiels mittels dessen ID
     * @param id des aufzurufenden Spiels
     * @return das aufzurufende Spiel
     */
    Spiel findById(Long id);

    /**
     * Methode dient zum Speichern des Spielstandes eines Spiels in der Datenbank
     * @param spiel welches zu speichern ist
     */
    void speichereSpiel(Spiel spiel);

    /**
     * Methode dient zum Löschen eines Spiels
     * @param id des zu löschenden Spiels
     * @return String ob das löschen erfolgreich war oder nicht
     */
    String loescheSpiel(Long id);
}

package groupone.kartenstapel_management.DAO;

import groupone.kartenstapel_management.classes.AblageStapel;

public interface IAblageStapelDAO {

    /**
     * Methode dient zum Aufrufen eines Ablagestapels mittels dessen ID
     * @param id des aufzurufenden Ablagestapels
     * @return das aufzurufende Ablagestapel
     */
    AblageStapel findById(Long id);

    /**
     * Methode dient zum Speichern des Ablagestapels eines Spiels in der Datenbank
     * @param ablageStapel welches zu speichern ist
     * @return gespeicherter Ablagestapel
     */
    AblageStapel speichereAblagestapel(AblageStapel ablageStapel);

    /**
     * Methode dient zum Löschen eines Ablagestapels
     * @param id des zu löschenden Ablagestapels
     */
    void loescheAblagestapel(Long id);
}

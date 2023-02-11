package groupone.kartenstapel_management.DAO;

import groupone.kartenstapel_management.classes.ZiehStapel;

public interface IZiehstapelDAO {

    /**
     * Methode dient zum Aufrufen eines Ziehstapels mittels dessen ID
     * @param id des aufzurufenden Ziehstapels
     * @return das aufzurufende Spiel
     */
    ZiehStapel findById(Long id);

    /**
     * Methode dient zum Speichern des Ziehstapels in der Datenbank
     * @param ziehStapel welches zu speichern ist
     * @return gespeicherter Ziehstapel
     */
    ZiehStapel speichereZiehStapel(ZiehStapel ziehStapel);

    /**
     * Methode dient zum Löschen eines Ziehstapels
     * @param id des zu löschenden Ziehstapels
     */
    void loescheZiehStapel(Long id);
}

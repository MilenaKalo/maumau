package groupone.spieler_management.DAO;

import groupone.kartenstapel_management.classes.ZiehStapel;
import groupone.spieler_management.classes.SpielerInterface;

public interface ISpielerInterfaceDAO {

    /**
     * Methode dient zum Aufrufen eines Spielers mittels dessen ID
     * @param id des aufzurufenden Spielers
     * @return der aufzurufende Spieler
     */
    SpielerInterface findById(Long id);

    /**
     * Methode dient zum Speichern des Spielers in der Datenbank
     * @param spieler welcher zu speichern ist
     * @return gespeicherter Spieler
     */
    SpielerInterface speichereSpieler(SpielerInterface spieler);

    /**
     * Methode dient zum Löschen eines Spielers
     * @param id des zu löschenden Spielers
     */
    void loescheSpieler(Long id);
}

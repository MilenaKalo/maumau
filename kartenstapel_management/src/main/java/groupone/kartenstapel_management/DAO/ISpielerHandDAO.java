package groupone.kartenstapel_management.DAO;

import groupone.kartenstapel_management.classes.SpielerHand;

public interface ISpielerHandDAO {

    /**
     * Methode dient zum Aufrufen einer Spielerhand mittels dessen ID
     * @param id der aufzurufenden Spielerhand
     * @return die aufzurufende Spielerhand
     */
    SpielerHand findById(Long id);

    /**
     * Methode dient zum Speichern der Spielerhand in der Datenbank
     * @param spielerHand welche zu speichern ist
     * @return gespeicherte Spielerhand
     */
    SpielerHand speichereSpielerHand(SpielerHand spielerHand);

    /**
     * Methode dient zum Löschen einer Spielerhand
     * @param id der zu löschenden Spielerhand
     */
    void loescheSpielerHand(Long id);
}

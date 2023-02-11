package groupone.spieler_management.DAO;

import groupone.kartenstapel_management.DAO.ZiehstapelDAO;
import groupone.spieler_management.Exceptions.SpielerNichtGefundenException;
import groupone.spieler_management.classes.SpielerInterface;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

public class SpielerInterfaceDAO implements ISpielerInterfaceDAO {

    @PersistenceContext
    EntityManager entityManager;

    private static Logger logger = LogManager.getLogger(ZiehstapelDAO.class);

    @Override
    public SpielerInterface findById(Long id) {
        SpielerInterface spieler = entityManager.find(SpielerInterface.class, id);
        if (Objects.isNull(spieler)) {
            logger.info("Spieler wurde nicht gefunden");
            throw new SpielerNichtGefundenException("Spieler mit ID " + id + " wurde nicht gefunden.");
        } else {
            logger.info("Spieler wurde gefunden" );
            return spieler;
        }
    }

    @Override
    public SpielerInterface speichereSpieler(SpielerInterface spieler) {
        SpielerInterface gespeicherterSpieler = entityManager.merge(spieler);
        entityManager.flush();
        logger.info("Spieler wurde gespeichert.");
        return gespeicherterSpieler;
    }

    @Override
    public void loescheSpieler(Long id) {
        SpielerInterface spieler = this.entityManager.find(SpielerInterface.class, id);
        this.entityManager.remove(spieler);
        logger.info("Spieler wurde gelöscht.");
    }
}

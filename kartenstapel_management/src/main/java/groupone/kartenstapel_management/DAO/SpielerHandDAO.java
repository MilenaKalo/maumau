package groupone.kartenstapel_management.DAO;

import groupone.kartenstapel_management.Exceptions.KartenstapelNichtGefundenException;
import groupone.kartenstapel_management.classes.SpielerHand;
import groupone.kartenstapel_management.classes.ZiehStapel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

public class SpielerHandDAO implements ISpielerHandDAO {

    @PersistenceContext
    EntityManager entityManager;

    private static Logger logger = LogManager.getLogger(ZiehstapelDAO.class);

    @Override
    public SpielerHand findById(Long id) {
        SpielerHand spielerHand = entityManager.find(SpielerHand.class, id);
        if (Objects.isNull(spielerHand)) {
            logger.info("Spielerhand wurde nicht gefunden");
            throw new KartenstapelNichtGefundenException("Spielerhand mit ID " + id + " wurde nicht gefunden.");
        } else {
            logger.info("Spielerhand wurde gefunden" );
            return spielerHand;
        }
    }

    @Override
    public SpielerHand speichereSpielerHand(SpielerHand spielerHand) {
        SpielerHand gespeicherteSpielerhand = entityManager.merge(spielerHand);
        entityManager.flush();
        logger.info("Spielerhand wurde gespeichert.");
        return gespeicherteSpielerhand;
    }

    @Override
    public void loescheSpielerHand(Long id) {
        SpielerHand spielerHand = this.entityManager.find(SpielerHand.class, id);
        this.entityManager.remove(spielerHand);
        logger.info("Spielerhand wurde gelöscht.");
    }
}

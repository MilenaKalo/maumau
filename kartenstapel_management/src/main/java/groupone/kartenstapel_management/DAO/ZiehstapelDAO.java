package groupone.kartenstapel_management.DAO;

import groupone.kartenstapel_management.Exceptions.KartenstapelNichtGefundenException;
import groupone.kartenstapel_management.classes.ZiehStapel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

public class ZiehstapelDAO implements IZiehstapelDAO {

    @PersistenceContext
    EntityManager entityManager;

    private static Logger logger = LogManager.getLogger(ZiehstapelDAO.class);

    @Override
    public ZiehStapel findById(Long id) {
        ZiehStapel ziehStapel = entityManager.find(ZiehStapel.class, id);
        if (Objects.isNull(ziehStapel)) {
            logger.info("Ziehstapel wurde nicht gefunden");
            throw new KartenstapelNichtGefundenException("Ziehstapel mit ID " + id + " wurde nicht gefunden.");
        } else {
            logger.info("Ziehstapel wurde gefunden" );
            return ziehStapel;
        }
    }

    @Override
    public ZiehStapel speichereZiehStapel(ZiehStapel ziehStapel) {
        ZiehStapel gespeicherterZiehStapel = entityManager.merge(ziehStapel);
        entityManager.flush();
        logger.info("Ziehstapel wurde gespeichert.");
        return gespeicherterZiehStapel;
    }

    @Override
    public void loescheZiehStapel(Long id) {
        ZiehStapel ziehStapel = this.entityManager.find(ZiehStapel.class, id);
        this.entityManager.remove(ziehStapel);
        logger.info("Ziehstapel wurde gelöscht.");
    }
}

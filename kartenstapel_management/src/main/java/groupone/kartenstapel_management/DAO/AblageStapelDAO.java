package groupone.kartenstapel_management.DAO;

import groupone.kartenstapel_management.Exceptions.KartenstapelNichtGefundenException;
import groupone.kartenstapel_management.classes.AblageStapel;

import groupone.kartenstapel_management.classes.ZiehStapel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

public class AblageStapelDAO implements IAblageStapelDAO {

    @PersistenceContext
    EntityManager entityManager;

    private static Logger logger = LogManager.getLogger(ZiehstapelDAO.class);

    @Override
    public AblageStapel findById(Long id) {
        AblageStapel ablageStapel = entityManager.find(AblageStapel.class, id);
        if (Objects.isNull(ablageStapel)) {
            logger.info("Ablagestapel wurde nicht gefunden");
            throw new KartenstapelNichtGefundenException("Ablagestapel mit ID " + id + " wurde nicht gefunden.");
        } else {
            logger.info("Ablagestapel wurde gefunden" );
            return ablageStapel;
        }
    }

    @Override
    public AblageStapel speichereAblagestapel(AblageStapel ablageStapel) {
        AblageStapel gespeicherterAblageStapel = entityManager.merge(ablageStapel);
        entityManager.flush();
        logger.info("Ablagestapel wurde gespeichert.");
        return gespeicherterAblageStapel;
    }

    @Override
    public void loescheAblagestapel(Long id) {
        AblageStapel ablageStapel = this.entityManager.find(AblageStapel.class, id);
        this.entityManager.remove(ablageStapel);
        logger.info("Ablagestapel wurde gelöscht.");
    }
}

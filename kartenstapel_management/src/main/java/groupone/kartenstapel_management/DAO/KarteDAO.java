package groupone.kartenstapel_management.DAO;

import groupone.kartenstapel_management.Exceptions.KartenstapelNichtGefundenException;
import groupone.kartenstapel_management.classes.Karte;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

public class KarteDAO implements IKarteDAO{

    @PersistenceContext
    EntityManager entityManager;

    private static Logger logger = LogManager.getLogger(ZiehstapelDAO.class);

    @Override
    public Karte findById(Long id) {
        Karte karte = entityManager.find(Karte.class, id);
        if (Objects.isNull(karte)) {
            logger.info("Karte wurde nicht gefunden");
            throw new KartenstapelNichtGefundenException("Karte mit ID " + id + " wurde nicht gefunden.");
        } else {
            logger.info("Karte wurde gefunden" );
            return karte;
        }
    }

    @Override
    public Karte speichereKarte(Karte karte) {
        Karte gespeicherteKarte = entityManager.merge(karte);
        entityManager.flush();
        logger.info("Karte wurde gespeichert.");
        return gespeicherteKarte;
    }

    @Override
    public void loescheKarte(Long id) {
        Karte karte = this.entityManager.find(Karte.class, id);
        this.entityManager.remove(karte);
        logger.info("Karte wurde gelöscht.");
    }
}

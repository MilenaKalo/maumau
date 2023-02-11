package groupone.spiel_management.DAO;

import groupone.spiel_management.Exceptions.SpielNichtGefundenException;
import groupone.spiel_management.classes.Spiel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Objects;

public class SpielDAO implements ISpielDAO{

    @PersistenceContext
    EntityManager entityManager;


    public SpielDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public SpielDAO() {
    }
    private static Logger logger = LogManager.getLogger(SpielDAO.class);

    @Override
    public Spiel findById(Long id) {
        Spiel spiel = entityManager.find(Spiel.class, id);
        if (Objects.isNull(spiel)) {
            logger.info("Spiel wurde nicht gefunden");
                   throw new SpielNichtGefundenException("Spiel mit ID " + id + " wurde nicht gefunden." );
        } else {
            logger.info("Spiel wurde gefunden" );
            return spiel;
        }
    }

    @Override
    public void speichereSpiel(Spiel spiel) {
        Spiel gespeichertesSpiel = entityManager.merge(spiel);
        entityManager.flush();
        logger.info("Spiel wurde gespeichert.");

    }

    @Transactional(Transactional.TxType.MANDATORY)
    @Override
    public String loescheSpiel(Long id) {
        String antwort = " ";
        System.out.println("entitymanager mit this" + this.entityManager);
        System.out.println("entitymanager " + entityManager);
        System.out.println("Bis hierhin komme ich");
        if (Objects.isNull(this.entityManager.find(Spiel.class, id))) {
            System.out.println("Bis hierhin komme ich. I still live");
             antwort = "nichts gefunden";
        } else {
            Spiel spiel = this.entityManager.find(Spiel.class, id);
            this.entityManager.remove(spiel);
            logger.info("Spiel wurde gelöscht.");
             antwort = "gelöscht";
        }
        return antwort;
    }
}

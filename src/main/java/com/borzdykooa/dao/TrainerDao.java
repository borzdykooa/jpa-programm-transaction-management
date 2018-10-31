package com.borzdykooa.dao;

import com.borzdykooa.entity.Trainer;
import com.borzdykooa.util.EntityManagerFactoryProvider;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/*
Класс, содержащий метод save и find для таблицы trainer
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TrainerDao {

    private static final TrainerDao INSTANCE = new TrainerDao();
    private static final Logger log = Logger.getLogger(TrainerDao.class);
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = EntityManagerFactoryProvider.getEntityManagerFactory();

    public Trainer find(Long id) {
        log.info("Method find is called in TrainerDao");
        EntityManager entityManager = null;
        Trainer trainer = null;
        try {
            entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            trainer = entityManager.find(Trainer.class, id);
            if (trainer != null) {
                log.info("List of Trainees: " + trainer.toString());
            } else {
                log.info("Trainer doesn't exist");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return trainer;
    }

    public void save(Trainer trainer) {
        log.info("Method save is called in TrainerDao");
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(trainer);
            entityManager.flush();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public static TrainerDao getInstance() {
        return INSTANCE;
    }
}

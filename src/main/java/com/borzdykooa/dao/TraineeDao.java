package com.borzdykooa.dao;

import com.borzdykooa.entity.Trainee;
import com.borzdykooa.util.EntityManagerFactoryProvider;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

/*
Класс, содержащий методы save и findAll для таблицы trainee
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TraineeDao {

    private static final TraineeDao INSTANCE = new TraineeDao();
    private static final Logger log = Logger.getLogger(TraineeDao.class);
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = EntityManagerFactoryProvider.getEntityManagerFactory();

    public void save(Trainee trainee) {
        log.info("Method save is called in TraineeDao");
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(trainee);
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

    public List<Trainee> findAll() {
        log.info("Method findAll is called in TraineeDao");
        EntityManager entityManager = null;
        List<Trainee> list = null;
        try {
            entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            list = entityManager.createQuery("select t from Trainee t", Trainee.class).getResultList();
            if (list.size() > 0) {
                log.info("List of Trainees: " + list.toString());
            } else {
                log.info("List of Trainees is empty");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return list;
    }

    public static TraineeDao getInstance() {
        return INSTANCE;
    }
}

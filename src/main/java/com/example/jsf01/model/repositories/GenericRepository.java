package com.example.jsf01.model.repositories;

import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
///import org.apache.logging.log4j.LogManager;
///import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static jakarta.ejb.TransactionAttributeType.REQUIRED;

public abstract class GenericRepository<T> {

    ///Existed in main implementation
    ///private static final Logger logger = LogManager.getLogger(GenericRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> entityClass;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public GenericRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @TransactionAttribute(REQUIRED)
    public void create(T entity) {
        entityManager.persist(entity);
    }

    @TransactionAttribute(REQUIRED)
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @TransactionAttribute(REQUIRED)
    public void update(Iterable<T> entities) {
        entities.forEach(entityManager::merge);
    }

    @TransactionAttribute(REQUIRED)
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public Optional<T> findById(Long id) {
        try {
            return Optional.ofNullable(entityManager.find(entityClass, id));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Optional<List<T>> findAll() {
        TypedQuery<T> query = entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass);

        return executeForResultList(query);
    }

    public Optional<List<T>> findAllWithPagination(int firstResult, int maxResults) {
        TypedQuery<T> query = entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass);

        // Set the first result (starting index) and the maximum number of results to retrieve
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);

        return executeForResultList(query);
    }

    public Optional<T> executeForSingleResult(TypedQuery<T> query) {
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            ///Existed in main implementation
            ///logger.error(e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<List<T>> executeForResultList(TypedQuery<T> query) {
        try {
            return Optional.of(query.getResultList());
        } catch (NoResultException e) {
            ///Existed in main implementation
            ///logger.error(e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
}

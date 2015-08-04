package com.baselogic.tutorials.repositories.jpa;

import com.baselogic.bfl.domain.AbstractEntity;
import com.baselogic.bfl.repositories.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractJpaRepository<E extends AbstractEntity> implements Repository<E> {

    private final Class<E> entityClass;


    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractJpaRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<E> getEntityClass() {
        return entityClass;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY, readOnly = true)
    public E findById(long id) {
        return entityManager.find(getEntityClass(), id);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public E save(E entity) {
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        entityManager.flush();
        return entity;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(E entity) {
        entityManager.remove(entity);
    }
}

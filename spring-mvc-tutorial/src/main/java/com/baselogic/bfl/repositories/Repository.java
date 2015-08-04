package com.baselogic.tutorials.repositories;


import com.baselogic.bfl.domain.AbstractEntity;

public interface Repository<E extends AbstractEntity> {

    E findById(long id);

    E save(E entity);

    void delete(E entity);

}

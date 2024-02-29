package br.com.mybet.domain.core.repository;


import br.com.mybet.domain.core.entity.IAggregateRoot;

import java.util.List;
import java.util.UUID;

public interface IGenericRepository<T extends IAggregateRoot> {

    void create(final T entity);

    void update(final T entity);

    T findById(final UUID id);

    List<T> findAll();

}

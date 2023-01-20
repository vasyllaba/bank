package com.solvd.bank.dao;

import java.util.Optional;

public interface IBaseDAO<T> {

    Optional<T> getById(long id);

    boolean update(T entity);

    Optional<T> create(T entity);

    boolean remove(long id);
}

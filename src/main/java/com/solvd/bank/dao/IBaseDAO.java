package com.solvd.bank.dao;

public interface IBaseDAO<T> {

    T getById(long id);

    boolean update(T entity);

    T create(T entity);

    boolean remove(long id);
}

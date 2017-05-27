package ua.nure.fomenko.analytics.dao;

import ua.nure.fomenko.analytics.db.entity.Entity;

/**
 * Created by fomenko on 10.03.2017.
 */
public interface GenericDao<T extends Entity> {

    public T get(int id);

    int create(T entity);

    boolean update(T entity);

    boolean delete(T entity);
}

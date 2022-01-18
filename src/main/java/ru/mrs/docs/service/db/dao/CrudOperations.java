package ru.mrs.docs.service.db.dao;

import java.util.List;

public interface CrudOperations<T, ID> {

    List<T> findAll();

    T findById(ID id);

    T update(T entity);

    T delete(ID id);
}

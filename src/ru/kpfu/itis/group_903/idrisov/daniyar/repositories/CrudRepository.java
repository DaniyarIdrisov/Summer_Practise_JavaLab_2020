package ru.kpfu.itis.group_903.idrisov.daniyar.repositories;

import java.util.List;

public interface CrudRepository<T> {

    List<T> findAll();
    T findById(Long id);
    void save(T entity);
    void update(T entity);

}

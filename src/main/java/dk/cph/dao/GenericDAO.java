package dk.cph.dao;

import java.util.List;
import java.util.Set;

public interface GenericDAO<T, D> {
    Set<T> findAll(int amount);
    void persistEntity(T entity);
    void removeEntity(D id);
    T findEntity(D id);
    T updateEntity(T entity, D id);
}
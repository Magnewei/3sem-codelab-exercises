package dk.cph.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface GenericDAO<T, D> {
    Collection<T> findAll();
    void persistEntity(T entity);
    void removeEntity(D id);
    T findEntity(D id);
    void updateEntity(T entity, D id);
}
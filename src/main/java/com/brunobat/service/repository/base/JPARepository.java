package com.brunobat.service.repository.base;

import com.brunobat.model.base.Identifiable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * Created by Bruno Baptista on 09/04/15.
 */
public abstract class JPARepository <T extends Identifiable> implements Repository<T>, Serializable {

    @PersistenceContext
    private EntityManager manager;

    private Class<T> type;

    public JPARepository(Class<T> type) {
        this.type = type;
    }

    @Override
    public Class<T> getType() {
        return type;
    }

    @Override
    public T store(T entity) {
        T merged = merge(entity);
        manager.persist(merged);
        return merged;
    }

    @Override
    public T get(String id) {
        return manager.find(type, id);
    }

    @Override
    public void remove(T entity) {
        manager.remove(merge(entity));
    }

    private T merge(T entity) {
        return manager.merge(entity);
    }

    protected EntityManager getManager() {
        return manager;
    }
}

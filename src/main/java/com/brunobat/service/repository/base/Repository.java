package com.brunobat.service.repository.base;

import com.brunobat.model.base.Identifiable;

/**
 * Created by Bruno Baptista on 09/04/15.
 */
public interface Repository<T extends Identifiable> {
    Class<T> getType();
    T store(T entity);
    T get(String id);
    void remove(T entity);
}
package org.sales_management.interfaces;

import java.util.Collection;

public  interface CrudInterface<T> {
    T create(T obj);
    T getById(Long id);
    T deleteById(Long id);
    T update(T obj);
    Collection<T> getAll();
}

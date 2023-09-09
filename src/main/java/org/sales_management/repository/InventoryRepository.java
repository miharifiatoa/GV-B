package org.sales_management.repository;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.InventoryEntity;
import org.sales_management.entity.ProductEntity;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;

public class InventoryRepository implements CrudInterface<InventoryEntity> {
    @Override
    public InventoryEntity create(InventoryEntity inventory) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(inventory);
        return inventory;
    }

    @Override
    public InventoryEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(InventoryEntity.class,id);
    }

    @Override
    public InventoryEntity deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        InventoryEntity inventory = getById(id);
        if (inventory!=null){
            session.remove(inventory);
        }
        return inventory;
    }

    @Override
    public InventoryEntity update(InventoryEntity obj) {
        return null;
    }

    @Override
    public Collection<InventoryEntity> getAll() {
        return null;
    }
}

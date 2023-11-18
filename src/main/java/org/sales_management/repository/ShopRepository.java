package org.sales_management.repository;

import org.hibernate.Session;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.ShopEntity;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;

public class ShopRepository implements CrudInterface<ShopEntity> {
    @Override
    public ShopEntity create(ShopEntity shop) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(shop);
        return shop;
    }

    @Override
    public ShopEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(ShopEntity.class,id);
    }

    @Override
    public ShopEntity deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ShopEntity shop = getById(id);
        if (shop!=null){
            session.remove(shop);
        }
        return shop;
    }

    @Override
    public ShopEntity update(ShopEntity new_shop) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        if (new_shop!=null){
            session.merge(new_shop);
        }
        return new_shop;
    }
    @Override
    public Collection<ShopEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from ShopEntity", ShopEntity.class).getResultList();
    }
}

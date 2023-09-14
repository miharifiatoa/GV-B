package org.sales_management.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.ProductEntity;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;

public class ProductRepository implements CrudInterface<ProductEntity> {
    @Override
    public ProductEntity create(ProductEntity product) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(product);
        return product;
    }

    @Override
    public ProductEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(ProductEntity.class,id);
    }

    @Override
    public ProductEntity deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ProductEntity product = getById(id);
        if (product!=null){
            session.remove(product);
        }
        return product;
    }

    @Override
    public ProductEntity update(ProductEntity product) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        if (product!=null){
            session.merge(product);
        }
        return product;
    }

    @Override
    public Collection<ProductEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from ProductEntity",ProductEntity.class).getResultList();
    }
}

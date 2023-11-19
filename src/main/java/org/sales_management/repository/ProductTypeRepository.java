package org.sales_management.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.ProductTypeEntity;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;
import java.util.List;

public class ProductTypeRepository implements CrudInterface<ProductTypeEntity> {
    @Override
    public ProductTypeEntity create(ProductTypeEntity product) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(product);
        return product;
    }

    @Override
    public ProductTypeEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(ProductTypeEntity.class,id);
    }

    @Override
    public ProductTypeEntity deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ProductTypeEntity product = getById(id);
        if (product!=null){
            session.remove(product);
        }
        return product;
    }

    @Override
    public ProductTypeEntity update(ProductTypeEntity product) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        if (product!=null){
            session.merge(product);
        }
        return product;
    }

    @Override
    public Collection<ProductTypeEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from ProductTypeEntity", ProductTypeEntity.class).getResultList();
    }
    public ProductTypeEntity getProductByName(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String hql = "SELECT e FROM ProductTypeEntity e WHERE LOWER(e.name) = :name";
        Query<ProductTypeEntity> query = session.createQuery(hql, ProductTypeEntity.class);
        query.setParameter("name", name);
        List<ProductTypeEntity> results = query.list();
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }
}

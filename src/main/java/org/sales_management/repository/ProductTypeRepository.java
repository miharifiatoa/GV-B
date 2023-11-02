package org.sales_management.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.ProductEntity;
import org.sales_management.entity.ProductTypeEntity;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;
import java.util.List;

public class ProductTypeRepository implements CrudInterface<ProductTypeEntity> {
    @Override
    public ProductTypeEntity create(ProductTypeEntity productType) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(productType);
        return productType;
    }

    @Override
    public ProductTypeEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(ProductTypeEntity.class,id);
    }

    @Override
    public ProductTypeEntity deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ProductTypeEntity productType = getById(id);
        if (productType != null){
            session.remove(productType);
        }
        return productType;
    }

    @Override
    public ProductTypeEntity update(ProductTypeEntity productType) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        if (productType != null){
            session.merge(productType);
        }
        return productType;
    }

    @Override
    public Collection<ProductTypeEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from ProductEntity",ProductTypeEntity.class).getResultList();
    }
    public ProductTypeEntity isUniqueValue(String productTypeName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String hql = "SELECT e FROM ProductTypeEntity e WHERE LOWER(e.name) = :name";
        Query<ProductTypeEntity> query = session.createQuery(hql, ProductTypeEntity.class);
        query.setParameter("name", productTypeName);
        List<ProductTypeEntity> results = query.list();
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        }

        return null;
    }

}

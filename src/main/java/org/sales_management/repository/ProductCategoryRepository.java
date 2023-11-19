package org.sales_management.repository;

import org.hibernate.Session;
import org.sales_management.entity.ProductCategoryEntity;
import org.sales_management.entity.ProductEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.session.HibernateUtil;

import java.util.Collection;

public class ProductCategoryRepository implements CrudInterface<ProductCategoryEntity> {
    @Override
    public ProductCategoryEntity create(ProductCategoryEntity obj) {
        return null;
    }

    @Override
    public ProductCategoryEntity getById(Long id) {
        return null;
    }

    @Override
    public ProductCategoryEntity deleteById(Long id) {
        return null;
    }

    @Override
    public ProductCategoryEntity update(ProductCategoryEntity obj) {
        return null;
    }

    @Override
    public Collection<ProductCategoryEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from ProductCategoryEntity", ProductCategoryEntity.class).getResultList();
    }
}

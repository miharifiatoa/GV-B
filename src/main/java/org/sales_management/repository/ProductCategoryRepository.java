package org.sales_management.repository;

import org.hibernate.Session;
import org.sales_management.entity.ProductCategoryEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.session.HibernateUtil;

import java.util.Collection;
import java.util.List;
import org.hibernate.query.Query;

public class ProductCategoryRepository implements CrudInterface<ProductCategoryEntity> {
    @Override
    public ProductCategoryEntity create(ProductCategoryEntity productCategory) {
         Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(productCategory);
        return productCategory;
    }

    @Override
    public ProductCategoryEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(ProductCategoryEntity.class,id);
    }

    @Override
    public ProductCategoryEntity deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ProductCategoryEntity category = getById(id);
        if (category!=null){
            session.remove(category);
        }
        return category;
    }

    @Override
    public ProductCategoryEntity update(ProductCategoryEntity productCategory) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        if (productCategory!=null){
            session.merge(productCategory);
        }
        return productCategory;
    }

    @Override
    public Collection<ProductCategoryEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from ProductCategoryEntity", ProductCategoryEntity.class).getResultList();
    }
    
    public ProductCategoryEntity isUniqueValue(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String hql = "SELECT e FROM ProductCategoryEntity e WHERE LOWER(e.name) = :name";
        Query<ProductCategoryEntity> query = session.createQuery(hql, ProductCategoryEntity.class);
        query.setParameter("name", name);
        List<ProductCategoryEntity> results = query.list();
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }
}

package org.sales_management.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.ProductCategoryEntity;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;
import java.util.List;

public class ProductCategoryRepository implements CrudInterface<ProductCategoryEntity> {
    @Override
    public ProductCategoryEntity create(ProductCategoryEntity article) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(article);
        return article;
    }

    @Override
    public ProductCategoryEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(ProductCategoryEntity.class,id);
    }

    @Override
    public ProductCategoryEntity deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ProductCategoryEntity article = getById(id);
        if (article!=null){
            session.remove(article);
        }
        return article;
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
    public ProductCategoryEntity isUniqueValue(String productCategoryName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String hql = "SELECT e FROM ProductCategoryEntity e WHERE LOWER(e.name) = :name";
        Query<ProductCategoryEntity> query = session.createQuery(hql, ProductCategoryEntity.class);
        query.setParameter("name", productCategoryName);
        List<ProductCategoryEntity> results = query.list();
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }
}

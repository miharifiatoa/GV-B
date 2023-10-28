package org.sales_management.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.ArticleEntity;
import org.sales_management.entity.ProductEntity;
import org.sales_management.entity.StockHistoryEntity;
import org.sales_management.interfaces.CrudInterface;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

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
    public ProductEntity isUniqueValue(String productName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String hql = "SELECT e FROM ProductEntity e WHERE e.name = :name";
        Query<ProductEntity> query = session.createQuery(hql, ProductEntity.class);
        query.setParameter("name", productName);
        List<ProductEntity> results = query.list();
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }
}

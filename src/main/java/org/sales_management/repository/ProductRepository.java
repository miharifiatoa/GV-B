package org.sales_management.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sales_management.entity.ProductEntity;
import org.sales_management.session.HibernateUtil;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;
import java.util.List;

public class ProductRepository implements CrudInterface<ProductEntity> {
    @Override
    public ProductEntity create(ProductEntity article) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(article);
        return article;
    }

    @Override
    public ProductEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(ProductEntity.class,id);
    }

    @Override
    public ProductEntity deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ProductEntity article = getById(id);
        if (article!=null){
            session.remove(article);
        }
        return article;
    }

    @Override
    public ProductEntity update(ProductEntity obj) {
        return null;
    }

    @Override
    public Collection<ProductEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from ProductEntity", ProductEntity.class).getResultList();
    }
    public ProductEntity isUniqueValue(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String hql = "SELECT e FROM ProductEntity e WHERE LOWER(e.name) = :name";
        Query<ProductEntity> query = session.createQuery(hql, ProductEntity.class);
        query.setParameter("name", name);
        List<ProductEntity> results = query.list();
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }
}

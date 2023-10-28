package org.sales_management.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.ArticleEntity;
import org.sales_management.entity.ProductTypeEntity;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;
import java.util.List;

public class ArticleRepository implements CrudInterface<ArticleEntity> {
    @Override
    public ArticleEntity create(ArticleEntity priceVariation) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(priceVariation);
        return priceVariation;
    }

    @Override
    public ArticleEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(ArticleEntity.class,id);
    }

    @Override
    public ArticleEntity deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArticleEntity priceVariation = session.load(ArticleEntity.class,id);
        session.delete(priceVariation);
        return priceVariation;
    }

    @Override
    public ArticleEntity update(ArticleEntity priceVariation) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        if (priceVariation != null){
            session.update(priceVariation);
        }
        return priceVariation;
    }

    @Override
    public Collection<ArticleEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from ArticleEntity", ArticleEntity.class).getResultList();
    }
    public ArticleEntity isUniqueValue(String code) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String hql = "SELECT e FROM ArticleEntity e WHERE e.code = :code";
        Query<ArticleEntity> query = session.createQuery(hql, ArticleEntity.class);
        query.setParameter("code", code);
        List<ArticleEntity> results = query.list();
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }
}

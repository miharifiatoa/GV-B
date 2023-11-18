package org.sales_management.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.ArticleTypeEntity;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;
import java.util.List;

public class ArticleTypeRepository implements CrudInterface<ArticleTypeEntity> {
    @Override
    public ArticleTypeEntity create(ArticleTypeEntity article) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(article);
        return article;
    }

    @Override
    public ArticleTypeEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(ArticleTypeEntity.class,id);
    }

    @Override
    public ArticleTypeEntity deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArticleTypeEntity priceVariation = session.load(ArticleTypeEntity.class,id);
        session.delete(priceVariation);
        return priceVariation;
    }

    @Override
    public ArticleTypeEntity update(ArticleTypeEntity article) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        if (article != null){
            session.merge(article);
        }
        return article;
    }

    @Override
    public Collection<ArticleTypeEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from ArticleTypeEntity", ArticleTypeEntity.class).getResultList();
    }
//    public ArticleTypeEntity isUniqueValue(String code) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        String hql = "SELECT e FROM ArticleTypeEntity e WHERE LOWER(e.code) = :code";
//        Query<ArticleTypeEntity> query = session.createQuery(hql, ArticleTypeEntity.class);
//        query.setParameter("code", code);
//        List<ArticleTypeEntity> results = query.list();
//        if (results != null && !results.isEmpty()) {
//            return results.get(0);
//        }
//        return null;
//    }
}

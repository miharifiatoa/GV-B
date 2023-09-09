package org.sales_management.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.ArticleEntity;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;

public class ArticleRepository implements CrudInterface<ArticleEntity> {
    @Override
    public ArticleEntity create(ArticleEntity article) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(article);
        return article;
    }

    @Override
    public ArticleEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(ArticleEntity.class,id);
    }

    @Override
    public ArticleEntity deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArticleEntity article = getById(id);
        if (article!=null){
            session.remove(article);
        }
        return article;
    }

    @Override
    public ArticleEntity update(ArticleEntity obj) {
        return null;
    }

    @Override
    public Collection<ArticleEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from ArticleEntity", ArticleEntity.class).getResultList();
    }
    public boolean isUniqueValue(String code){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String hql = "SELECT COUNT(e) FROM ArticleEntity e WHERE e.code = :code";
        Query<Long> query = session.createQuery(hql,Long.class);
        query.setParameter("code",code);
        Long count = query.uniqueResult();
        return count == 0;
    }
}

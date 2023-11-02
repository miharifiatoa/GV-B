package org.sales_management.repository;

import org.hibernate.Session;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.ArrivalArticleEntity;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;

public class ArrivalArticleRepository implements CrudInterface<ArrivalArticleEntity> {
    @Override
    public ArrivalArticleEntity create(ArrivalArticleEntity arrivalArticle) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(arrivalArticle);
        return arrivalArticle;
    }

    @Override
    public ArrivalArticleEntity getById(Long id) {
        return null;
    }

    @Override
    public ArrivalArticleEntity deleteById(Long id) {
        return null;
    }

    @Override
    public ArrivalArticleEntity update(ArrivalArticleEntity obj) {
        return null;
    }

    @Override
    public Collection<ArrivalArticleEntity> getAll() {
        return null;
    }
}

package org.sales_management.repository;

import org.hibernate.Session;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.SaleArticleEntity;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;

public class SaleArticleRepository implements CrudInterface<SaleArticleEntity> {
    @Override
    public SaleArticleEntity create(SaleArticleEntity saleArticle) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(saleArticle);
        return saleArticle;
    }

    @Override
    public SaleArticleEntity getById(Long id) {
        return null;
    }

    @Override
    public SaleArticleEntity deleteById(Long id) {
        return null;
    }

    @Override
    public SaleArticleEntity update(SaleArticleEntity obj) {
        return null;
    }

    @Override
    public Collection<SaleArticleEntity> getAll() {
        return null;
    }
}

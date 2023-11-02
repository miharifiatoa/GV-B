package org.sales_management.repository;

import org.hibernate.Session;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.ShareArticleEntity;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;

public class ShareArticleRepository implements CrudInterface<ShareArticleEntity> {
    @Override
    public ShareArticleEntity create(ShareArticleEntity shopArticle) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(shopArticle);
        return shopArticle;
    }

    @Override
    public ShareArticleEntity getById(Long id) {
        return null;
    }

    @Override
    public ShareArticleEntity deleteById(Long id) {
        return null;
    }

    @Override
    public ShareArticleEntity update(ShareArticleEntity obj) {
        return null;
    }

    @Override
    public Collection<ShareArticleEntity> getAll() {
        return null;
    }
}

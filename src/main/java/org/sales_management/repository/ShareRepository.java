package org.sales_management.repository;

import org.hibernate.Session;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.ShareEntity;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;

public class ShareRepository implements CrudInterface<ShareEntity> {
    @Override
    public ShareEntity create(ShareEntity share) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(share);
        return share;
    }

    @Override
    public ShareEntity getById(Long id) {
        return null;
    }

    @Override
    public ShareEntity deleteById(Long id) {
        return null;
    }

    @Override
    public ShareEntity update(ShareEntity obj) {
        return null;
    }

    @Override
    public Collection<ShareEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from ShareEntity" , ShareEntity.class).getResultList();
    }
}

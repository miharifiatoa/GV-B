package org.sales_management.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sales_management.entity.ArrivalEntity;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.ShareEntity;
import org.sales_management.interfaces.CrudInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public Collection<ShareEntity> getSharesByDate(LocalDate date) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        Query<ShareEntity> query = session.createQuery("from ShareEntity where shareDate >= :startOfDay and shareDate <= :endOfDay and isCanceled = false", ShareEntity.class);
        query.setParameter("startOfDay", startOfDay);
        query.setParameter("endOfDay", endOfDay);
        return query.getResultList();
    }
    public Collection<ShareEntity> getAllSharesByDate(LocalDate date) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        Query<ShareEntity> query = session.createQuery("from ShareEntity where shareDate >= :startOfDay and shareDate <= :endOfDay", ShareEntity.class);
        query.setParameter("startOfDay", startOfDay);
        query.setParameter("endOfDay", endOfDay);
        return query.getResultList();
    }

}

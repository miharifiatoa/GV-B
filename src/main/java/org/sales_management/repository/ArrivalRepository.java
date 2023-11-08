package org.sales_management.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sales_management.entity.SaleEntity;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.ArrivalEntity;
import org.sales_management.interfaces.CrudInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public class ArrivalRepository implements CrudInterface<ArrivalEntity> {
    @Override
    public ArrivalEntity create(ArrivalEntity arrival) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(arrival);
        return arrival;
    }

    @Override
    public ArrivalEntity getById(Long id) {
        return null;
    }

    @Override
    public ArrivalEntity deleteById(Long id) {
        return null;
    }

    @Override
    public ArrivalEntity update(ArrivalEntity arrival) {
        return null;
    }

    @Override
    public Collection<ArrivalEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from ArrivalEntity", ArrivalEntity.class).getResultList();
    }
    public Collection<ArrivalEntity> getArrivalsByDate(LocalDate date) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        Query<ArrivalEntity> query = session.createQuery("from ArrivalEntity where arrivalDate >= :startOfDay and arrivalDate <= :endOfDay and isCanceled = false", ArrivalEntity.class);
        query.setParameter("startOfDay", startOfDay);
        query.setParameter("endOfDay", endOfDay);
        return query.getResultList();
    }
    public Collection<ArrivalEntity> getAllArrivalsByDate(LocalDate date) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        Query<ArrivalEntity> query = session.createQuery("from ArrivalEntity where arrivalDate >= :startOfDay and arrivalDate <= :endOfDay", ArrivalEntity.class);
        query.setParameter("startOfDay", startOfDay);
        query.setParameter("endOfDay", endOfDay);
        return query.getResultList();
    }
}

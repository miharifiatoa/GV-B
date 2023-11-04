package org.sales_management.repository;

import org.hibernate.Session;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.ArrivalEntity;
import org.sales_management.interfaces.CrudInterface;

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
}

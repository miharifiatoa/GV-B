package org.sales_management.repository;

import org.hibernate.Session;
import org.sales_management.entity.PaymentModeEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.session.HibernateUtil;

import java.util.Collection;

public class PaymentModeRepository implements CrudInterface<PaymentModeEntity> {
    @Override
    public PaymentModeEntity create(PaymentModeEntity obj) {
        return null;
    }

    @Override
    public PaymentModeEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(PaymentModeEntity.class , id);
    }

    @Override
    public PaymentModeEntity deleteById(Long id) {
        return null;
    }

    @Override
    public PaymentModeEntity update(PaymentModeEntity obj) {
        return null;
    }

    @Override
    public Collection<PaymentModeEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from PaymentModeEntity order by id" , PaymentModeEntity.class).getResultList();
    }
}

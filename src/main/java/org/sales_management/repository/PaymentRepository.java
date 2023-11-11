package org.sales_management.repository;

import org.hibernate.Session;
import org.sales_management.entity.PaymentEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.session.HibernateUtil;

import java.util.Collection;

public class PaymentRepository implements CrudInterface<PaymentEntity> {
    @Override
    public PaymentEntity create(PaymentEntity payment) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(payment);
        return payment;
    }

    @Override
    public PaymentEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(PaymentEntity.class,id);
    }

    @Override
    public PaymentEntity deleteById(Long id) {
        return null;
    }

    @Override
    public PaymentEntity update(PaymentEntity obj) {
        return null;
    }

    @Override
    public Collection<PaymentEntity> getAll() {
        return null;
    }
}

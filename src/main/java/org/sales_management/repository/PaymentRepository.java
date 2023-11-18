package org.sales_management.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sales_management.entity.PaymentEntity;
import org.sales_management.entity.PaymentModeEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.session.HibernateUtil;

import java.time.LocalDate;
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
    public Collection<Object[]> getPaymentsByModeAndDate(String mode , LocalDate localDate){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String hql = "SELECT p.paymentMode.description, SUM(p.pay) " +
                "FROM PaymentEntity p " +
                "WHERE p.paymentMode.description = :mode " +
                "AND p.paymentDate >= :startDate " +
                "AND p.paymentDate < :endDate " +
                "GROUP BY p.paymentMode.description";

        Query<Object[]> query = session.createQuery(hql, Object[].class);
        query.setParameter("mode", mode);
        query.setParameter("startDate", localDate.atStartOfDay());
        query.setParameter("endDate", localDate.plusDays(1).atStartOfDay());
        return query.getResultList();
    }
}

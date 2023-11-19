package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.entity.PaymentEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.PaymentRepository;
import org.sales_management.session.HibernateUtil;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

public class PaymentService implements CrudInterface<PaymentEntity> {
    private final PaymentRepository paymentRepository;

    public PaymentService() {
        this.paymentRepository = new PaymentRepository();
    }

    @Override
    public PaymentEntity create(PaymentEntity payment) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.paymentRepository.create(payment);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return payment;
    }

    @Override
    public PaymentEntity getById(Long id) {
        Transaction transaction = null;
        PaymentEntity payment = new PaymentEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.paymentRepository.getById(id);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return payment;
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
    public Collection<Object[]> getPaymentsByModeAndDate(String mode , LocalDate localDate) {
        Transaction transaction = null;
        Collection<Object[]> objects = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            objects = this.paymentRepository.getPaymentsByModeAndDate(mode,localDate);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return objects;
    }
}

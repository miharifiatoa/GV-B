package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.entity.PaymentModeEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.PaymentModeRepository;
import org.sales_management.session.HibernateUtil;

import java.util.Collection;
import java.util.HashSet;

public class PaymentModeService implements CrudInterface<PaymentModeEntity> {
    private final PaymentModeRepository paymentModeRepository;

    public PaymentModeService() {
        this.paymentModeRepository = new PaymentModeRepository();
    }

    @Override
    public PaymentModeEntity create(PaymentModeEntity obj) {
        return null;
    }

    @Override
    public PaymentModeEntity getById(Long id) {
        Transaction transaction = null;
        PaymentModeEntity paymentMode = new PaymentModeEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            paymentMode = this.paymentModeRepository.getById(id);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return paymentMode;
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
        Transaction transaction = null;
        Collection<PaymentModeEntity> paymentModes = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            paymentModes = this.paymentModeRepository.getAll();
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return paymentModes;
    }
}

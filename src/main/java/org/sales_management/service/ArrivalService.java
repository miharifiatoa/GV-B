package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.ArrivalEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ArrivalRepository;

import java.util.Collection;

public class ArrivalService implements CrudInterface<ArrivalEntity> {
    private final ArrivalRepository arrivalRepository;

    public ArrivalService() {
        this.arrivalRepository = new ArrivalRepository();
    }

    @Override
    public ArrivalEntity create(ArrivalEntity arrival) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.arrivalRepository.create(arrival);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
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
    public ArrivalEntity update(ArrivalEntity obj) {
        return null;
    }

    @Override
    public Collection<ArrivalEntity> getAll() {
        return null;
    }
}

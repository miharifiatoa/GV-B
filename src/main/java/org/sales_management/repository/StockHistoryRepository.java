package org.sales_management.repository;

import org.hibernate.Session;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.StockHistoryEntity;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;

public class StockHistoryRepository implements CrudInterface<StockHistoryEntity> {
    @Override
    public StockHistoryEntity create(StockHistoryEntity stockHistory) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(stockHistory);
        return stockHistory;
    }

    @Override
    public StockHistoryEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return  session.get(StockHistoryEntity.class,id);
    }

    @Override
    public StockHistoryEntity deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        StockHistoryEntity history = getById(id);
        if (history!=null){
            session.remove(history);
        }
        return history;
    }

    @Override
    public StockHistoryEntity update(StockHistoryEntity obj) {
         Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        if (obj!=null){
            session.merge(obj);
        }
        return obj;
    }

    @Override
    public Collection<StockHistoryEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from StockHistoryEntity",StockHistoryEntity.class).getResultList();    
    }
}

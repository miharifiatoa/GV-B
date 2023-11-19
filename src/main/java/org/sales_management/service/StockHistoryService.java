package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.StockHistoryEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.StockHistoryRepository;

import java.util.Collection;
import java.util.HashSet;

public class StockHistoryService implements CrudInterface<StockHistoryEntity> {
    private final StockHistoryRepository stockHistoryRepository;

    public StockHistoryService() {
        this.stockHistoryRepository = new StockHistoryRepository();
    }

    @Override
    public StockHistoryEntity create(StockHistoryEntity stockHistory) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.stockHistoryRepository.create(stockHistory);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return stockHistory;
    }

    @Override
    public StockHistoryEntity getById(Long id) {
        StockHistoryEntity history = new StockHistoryEntity();
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            history = stockHistoryRepository.getById(id);
            transaction.commit();
        }
        catch(Throwable t){
            if(transaction!=null){
                transaction.rollback();
            }
            t.printStackTrace();
        }
        finally {
            if(session!=null){
                session.close();
            }
        }
        return history;
    }

    @Override
    public StockHistoryEntity deleteById(Long id) {
        StockHistoryEntity history = new StockHistoryEntity();
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            history = this.stockHistoryRepository.deleteById(id);
            transaction.commit();
        }
        catch(Throwable t){
            if(transaction!=null){
                transaction.rollback();
            }
            t.printStackTrace();
        }
        finally {
            if(session!=null){
                session.close();
            }
        }
        return history;
    }

    @Override
    public StockHistoryEntity update(StockHistoryEntity obj) {
        Session session = null;
        Transaction transaction = null;
        StockHistoryEntity history = new StockHistoryEntity();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            history = this.stockHistoryRepository.update(obj);
            transaction.commit();
        }
        catch(Throwable t){
            if(transaction!=null){
                transaction.rollback();
            }
            t.printStackTrace();
        }
        finally {
            if(session!=null){
                session.close();
            }
        }
        return history;
    }

    @Override
    public Collection<StockHistoryEntity> getAll() {
        Collection<StockHistoryEntity> collection = new HashSet<>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            collection = this.stockHistoryRepository.getAll();
            transaction.commit();
        } 
        catch(Throwable t){
            if(transaction!=null){
                transaction.rollback();
            }
            t.printStackTrace();
        }
        finally {
            if(session!=null){
                session.close();
            }
        }
        return collection;
    }
}

package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.StockHistoryEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.StockHistoryRepository;

import java.util.Collection;

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
        return null;
    }

    @Override
    public StockHistoryEntity deleteById(Long id) {
        return null;
    }

    @Override
    public StockHistoryEntity update(StockHistoryEntity obj) {
        return null;
    }

    @Override
    public Collection<StockHistoryEntity> getAll() {
        return null;
    }
}

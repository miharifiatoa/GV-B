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

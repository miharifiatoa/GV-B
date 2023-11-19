package org.sales_management.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sales_management.entity.SaleEntity;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.SaleArticleEntity;
import org.sales_management.interfaces.CrudInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public class SaleArticleRepository implements CrudInterface<SaleArticleEntity> {
    @Override
    public SaleArticleEntity create(SaleArticleEntity saleArticle) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(saleArticle);
        return saleArticle;
    }

    @Override
    public SaleArticleEntity getById(Long id) {
        return null;
    }

    @Override
    public SaleArticleEntity deleteById(Long id) {
        return null;
    }

    @Override
    public SaleArticleEntity update(SaleArticleEntity obj) {
        return null;
    }

    @Override
    public Collection<SaleArticleEntity> getAll() {
        return null;
    }
    public Collection<SaleArticleEntity> getSaleArticlesByDate(LocalDate date) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        Query<SaleArticleEntity> query = session.createQuery("from SaleArticleEntity where saleDate >= :startOfDay and saleDate <= :endOfDay", SaleArticleEntity.class);
        query.setParameter("startOfDay", startOfDay);
        query.setParameter("endOfDay", endOfDay);
        return query.getResultList();
    }
}

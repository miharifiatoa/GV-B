package org.sales_management.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.SaleEntity;
import org.sales_management.interfaces.CrudInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public class SaleRepository implements CrudInterface<SaleEntity> {
    @Override
    public SaleEntity create(SaleEntity sale) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(sale);
        return sale;
    }

    @Override
    public SaleEntity getById(Long id) {
        return null;
    }

    @Override
    public SaleEntity deleteById(Long id) {
        return null;
    }

    @Override
    public SaleEntity update(SaleEntity sale) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        if (sale != null){
            session.merge(sale);
        }
        return sale;
    }

    @Override
    public Collection<SaleEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from SaleEntity",SaleEntity.class).getResultList();
    }
    public Collection<SaleEntity> getAcceptedAndPayedSalesByDate(LocalDate date) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        Query<SaleEntity> query = session.createQuery("from SaleEntity where saleDate >= :startOfDay and saleDate <= :endOfDay and isCanceled = false and isPayed = true", SaleEntity.class);
        query.setParameter("startOfDay", startOfDay);
        query.setParameter("endOfDay", endOfDay);
        return query.getResultList();
    }
    public Collection<SaleEntity> getAcceptedUnPayedSales() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<SaleEntity> query = session.createQuery("from SaleEntity where isCanceled = false and isPayed = false", SaleEntity.class);
        return query.getResultList();
    }
    public Collection<SaleEntity> getAcceptedSalesByDate(LocalDate date) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        Query<SaleEntity> query = session.createQuery("from SaleEntity where saleDate >= :startOfDay and saleDate <= :endOfDay and isCanceled = false", SaleEntity.class);
        query.setParameter("startOfDay", startOfDay);
        query.setParameter("endOfDay", endOfDay);
        return query.getResultList();
    }
    public Collection<SaleEntity> getAllSalesByDate(LocalDate date) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        Query<SaleEntity> query = session.createQuery("from SaleEntity where saleDate >= :startOfDay and saleDate <= :endOfDay", SaleEntity.class);
        query.setParameter("startOfDay", startOfDay);
        query.setParameter("endOfDay", endOfDay);
        return query.getResultList();
    }
}

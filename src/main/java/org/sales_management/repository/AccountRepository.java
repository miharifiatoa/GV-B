package org.sales_management.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.AccountEntity;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;

public class AccountRepository implements CrudInterface<AccountEntity> {
    @Override
    public AccountEntity create(AccountEntity account) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(account);
        return account;
    }

    @Override
    public AccountEntity getById(Long id) {
        new AccountEntity();
        AccountEntity account;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        account = session.get(AccountEntity.class,id);
        return account;
    }

    @Override
    public AccountEntity deleteById(Long id) {
        return null;
    }

    @Override
    public AccountEntity update(AccountEntity obj) {
        return null;
    }

    @Override
    public Collection<AccountEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<AccountEntity> query = session.createQuery("from AccountEntity",AccountEntity.class);
        return query.getResultList();
    }
    public boolean isUniqueValue(String value){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String hql = "SELECT COUNT(e) FROM AccountEntity e WHERE e.username = :value";
        Query<Long> query = session.createQuery(hql,Long.class);
        query.setParameter("value",value);
        Long count = query.uniqueResult();
        return count == 0;
    }
}

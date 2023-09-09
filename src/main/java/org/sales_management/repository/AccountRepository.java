package org.sales_management.repository;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return  session.get(AccountEntity.class,id);
    }

    @Override
    public AccountEntity deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        AccountEntity account = getById(id);
        if (account!=null){
            session.remove(account);
        }
        return account;
    }

    @Override
    public AccountEntity update(AccountEntity obj) {
        return null;
    }

    @Override
    public Collection<AccountEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from AccountEntity",AccountEntity.class).getResultList();
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

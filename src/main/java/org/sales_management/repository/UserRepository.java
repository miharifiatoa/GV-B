package org.sales_management.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.AccountEntity;
import org.sales_management.entity.UserEntity;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;

public class UserRepository implements CrudInterface<UserEntity> {
    @Override
    public UserEntity create(UserEntity user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(user);
        return user;
    }

    @Override
    public UserEntity getById(Long id) {
        return null;
    }

    @Override
    public UserEntity deleteById(Long id) {
        return null;
    }

    @Override
    public UserEntity update(UserEntity obj) {
        return null;
    }

    @Override
    public Collection<UserEntity> getAll() {
        return null;
    }
    public AccountEntity getAccountByUserName(String username){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<AccountEntity> query = session.createQuery("SELECT a FROM AccountEntity a WHERE a.username = :username", AccountEntity.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}

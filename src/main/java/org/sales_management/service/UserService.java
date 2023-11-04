package org.sales_management.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.AccountEntity;
import org.sales_management.entity.UserEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.UserRepository;

import java.util.Collection;

public class UserService implements CrudInterface<UserEntity> {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    @Override
    public UserEntity create(UserEntity user) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            this.userRepository.create(user);
            transaction.commit();
        }catch (HibernateException e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
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

    public AccountEntity getAccountByUsername(String username){
        Transaction transaction = null;
        AccountEntity account = new AccountEntity();
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            account = userRepository.getAccountByUserName(username);
            transaction.commit();
        }
        catch (HibernateException e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return account;
    }
}

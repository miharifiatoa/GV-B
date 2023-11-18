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
        UserEntity user = new UserEntity();
        Session session = null;
        Transaction transaction = null;
                try{
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                transaction = session.beginTransaction();
                user = this.userRepository.deleteById(id);
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
        return user;
    }

    @Override
    public UserEntity update(UserEntity new_user) {
        Transaction transaction = null;
        UserEntity user = new UserEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            user = this.userRepository.update(new_user);
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Collection<UserEntity> getAll() {
        return null;
    }

    public AccountEntity getAccountByUsername(String username){
        Session session = null;
        Transaction transaction = null;
        AccountEntity account = new AccountEntity();
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            if(userRepository.getAccountByUserName(username)!=null)
            { account = userRepository.getAccountByUserName(username); }
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
        return account;
    }
}
        
         
         
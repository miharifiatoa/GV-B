package org.sales_management.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.AccountEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.AccountRepository;

import java.util.Collection;
import java.util.HashSet;

public class AccountService implements CrudInterface<AccountEntity> {
    private final AccountRepository accountRepository;

    public AccountService() {
        this.accountRepository = new AccountRepository();
    }

    @Override
    public AccountEntity create(AccountEntity account) {
        Transaction transaction = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            this.accountRepository.create(account);
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

    @Override
    public AccountEntity getById(Long id) {
        AccountEntity account = new AccountEntity();
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            account = accountRepository.getById(id);
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

    @Override
    public AccountEntity deleteById(Long id) {
        AccountEntity account = new AccountEntity();
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            account = this.accountRepository.deleteById(id);
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

    @Override
    public AccountEntity update(AccountEntity new_account) {
        Session session = null;
        Transaction transaction = null;
        AccountEntity account = new AccountEntity();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            account = this.accountRepository.update(new_account);
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

    @Override
    public Collection<AccountEntity> getAll() {
        Collection<AccountEntity> collection = new HashSet<>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            collection = this.accountRepository.getAll();
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
    public boolean isUniqueValue(String value){
        boolean isUnique = false;
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            isUnique = this.accountRepository.isUniqueValue(value);
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
        return isUnique;
    }
}

package org.sales_management.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.HibernateUtil;
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
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            this.accountRepository.create(account);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return account;
    }

    @Override
    public AccountEntity getById(Long id) {
        AccountEntity account = new AccountEntity();
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            account = accountRepository.getById(id);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
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
        Collection<AccountEntity> collection = new HashSet<>();
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            collection = this.accountRepository.getAll();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return collection;
    }
    public boolean isUniqueValue(String value){
        boolean isUnique = false;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            isUnique = this.accountRepository.isUniqueValue(value);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return isUnique;
    }
}

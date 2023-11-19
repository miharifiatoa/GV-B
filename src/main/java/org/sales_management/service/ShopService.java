package org.sales_management.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.ShopEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ShopRepository;

import java.util.Collection;
import java.util.HashSet;

public class ShopService implements CrudInterface<ShopEntity> {
    private final ShopRepository shopRepository;
    public ShopService() {
        this.shopRepository = new ShopRepository();
    }

    @Override
    public ShopEntity create(ShopEntity shop) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            this.shopRepository.create(shop);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return shop;
    }

    @Override
    public ShopEntity getById(Long id) {
        ShopEntity shop = new ShopEntity();
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            shop = shopRepository.getById(id);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return shop;
    }

    @Override
    public ShopEntity deleteById(Long id) {

        ShopEntity shop = new ShopEntity();
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            shop = this.shopRepository.deleteById(id);
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
        return shop;
    }

    @Override
    public ShopEntity update(ShopEntity new_shop) {
        Session session = null;
        Transaction transaction = null;
        ShopEntity shop = new ShopEntity();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            shop = this.shopRepository.update(new_shop);
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
        return new_shop;
    }

    @Override
    public Collection<ShopEntity> getAll() {
        Collection<ShopEntity> collection = new HashSet<>();
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            collection = this.shopRepository.getAll();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return collection;
    }
}

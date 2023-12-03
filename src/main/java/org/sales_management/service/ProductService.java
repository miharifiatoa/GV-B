package org.sales_management.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.entity.ProductEntity;
import org.sales_management.session.HibernateUtil;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ProductRepository;

import java.util.Collection;
import java.util.HashSet;
import org.hibernate.query.Query;

public class ProductService implements CrudInterface<ProductEntity> {
    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    @Override
    public ProductEntity create(ProductEntity product) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.productRepository.create(product);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return product;
    }

    @Override
    public ProductEntity getById(Long id) {
        Transaction transaction = null;
        ProductEntity product = new ProductEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            product = this.productRepository.getById(id);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return product;
    }

    @Override
    public ProductEntity deleteById(Long id) {
        Transaction transaction = null;
        ProductEntity product = new ProductEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            product = this.productRepository.deleteById(id);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return product;
    }

    @Override
    public ProductEntity update(ProductEntity product) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            product = this.productRepository.update(product);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return product;
    }

    @Override
    public Collection<ProductEntity> getAll() {
        Transaction transaction = null;
        Collection<ProductEntity> products = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            products = this.productRepository.getAll();
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return products;
    }
    public ProductEntity isUniqueValue(String code){
        Transaction transaction = null;
        ProductEntity product = new ProductEntity();
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            product = this.productRepository.isUniqueValue(code);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return product;
    }
    
    public Collection<ProductEntity> searchProductsByName(String name){
        Transaction transaction = null;
        Collection<ProductEntity> foundProducts = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query<ProductEntity> productEntityQuery = session.createQuery("from ProductEntity where name like :name", ProductEntity.class);
            productEntityQuery.setParameter("name","%"+name+"%");
            foundProducts = productEntityQuery.getResultList();
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return foundProducts;
    }
}

package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.ProductEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ProductRepository;

import java.util.Collection;
import java.util.HashSet;

public class ProductService implements CrudInterface<ProductEntity> {
    private ProductRepository productRepository;

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
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
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
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
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
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public ProductEntity update(ProductEntity obj) {
        return null;
    }

    @Override
    public Collection<ProductEntity> getAll() {
        Transaction transaction = null;
        Collection<ProductEntity> products = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            products = this.productRepository.getAll();
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return products;
    }
}

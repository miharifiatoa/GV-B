package org.sales_management.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.ProductCategoryEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ProductCategoryRepository;

import java.util.Collection;
import java.util.HashSet;

public class ProductCategoryService implements CrudInterface<ProductCategoryEntity> {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService() {
        this.productCategoryRepository = new ProductCategoryRepository();
    }

    @Override
    public ProductCategoryEntity create(ProductCategoryEntity article) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.productCategoryRepository.create(article);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return article;
    }

    @Override
    public ProductCategoryEntity getById(Long id) {
        Transaction transaction = null;
        ProductCategoryEntity article = new ProductCategoryEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            article = this.productCategoryRepository.getById(id);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return article;
    }

    @Override
    public ProductCategoryEntity deleteById(Long id) {
        Transaction transaction = null;
        ProductCategoryEntity article = new ProductCategoryEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            article = this.productCategoryRepository.deleteById(id);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return article;
    }

    @Override
    public ProductCategoryEntity update(ProductCategoryEntity obj) {
        return null;
    }

    @Override
    public Collection<ProductCategoryEntity> getAll() {
        Transaction transaction = null;
        Collection<ProductCategoryEntity> articles = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            articles = this.productCategoryRepository.getAll();
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return articles;
    }
    public ProductCategoryEntity isUniqueValue(String code){
        Transaction transaction = null;
        ProductCategoryEntity productCategory = new ProductCategoryEntity();
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            productCategory = this.productCategoryRepository.isUniqueValue(code);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return productCategory;
    }
}

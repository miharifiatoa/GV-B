package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.entity.ProductCategoryEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ProductCategoryRepository;
import org.sales_management.session.HibernateUtil;

import java.util.Collection;
import java.util.HashSet;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

public class ProductCategoryService implements CrudInterface<ProductCategoryEntity> {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService() {
        this.productCategoryRepository = new ProductCategoryRepository();
    }

    @Override
    public ProductCategoryEntity create(ProductCategoryEntity obj) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.productCategoryRepository.create(obj);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public ProductCategoryEntity getById(Long id) {
        Transaction transaction = null;
        ProductCategoryEntity category = new ProductCategoryEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            category = this.productCategoryRepository.getById(id);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public ProductCategoryEntity deleteById(Long id) {
        Transaction transaction = null;
        ProductCategoryEntity category = new ProductCategoryEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            category = this.productCategoryRepository.deleteById(id);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public ProductCategoryEntity update(ProductCategoryEntity obj) {
        Transaction transaction = null;
        ProductCategoryEntity category = new ProductCategoryEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            category = this.productCategoryRepository.update(obj);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return category;
    }

    @Override
    public Collection<ProductCategoryEntity> getAll() {
        Transaction transaction = null;
        Collection<ProductCategoryEntity> productCategories = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            productCategories = this.productCategoryRepository.getAll();
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return productCategories;
    }
    
    public Collection<ProductCategoryEntity> searchCategoryByName(String name){
        Collection<ProductCategoryEntity> foundCategories = new HashSet<>();
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query<ProductCategoryEntity> productCategoryEntityQuery = session.createQuery("from ProductCategoryEntity where name like :name", ProductCategoryEntity.class);
            productCategoryEntityQuery.setParameter("name","%"+name+"%");
            foundCategories = productCategoryEntityQuery.getResultList();
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return foundCategories;
    }
    
    public ProductCategoryEntity isUniqueValue(String name){
        Transaction transaction = null;
        ProductCategoryEntity productCategory = new ProductCategoryEntity();
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            productCategory = this.productCategoryRepository.isUniqueValue(name);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return productCategory;
    }
}

package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.entity.ProductCategoryEntity;
import org.sales_management.entity.ProductEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ProductCategoryRepository;
import org.sales_management.session.HibernateUtil;

import java.util.Collection;
import java.util.HashSet;

public class ProductCategoryService implements CrudInterface<ProductCategoryEntity> {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService() {
        this.productCategoryRepository = new ProductCategoryRepository();
    }

    @Override
    public ProductCategoryEntity create(ProductCategoryEntity obj) {
        return null;
    }

    @Override
    public ProductCategoryEntity getById(Long id) {
        return null;
    }

    @Override
    public ProductCategoryEntity deleteById(Long id) {
        return null;
    }

    @Override
    public ProductCategoryEntity update(ProductCategoryEntity obj) {
        return null;
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
}

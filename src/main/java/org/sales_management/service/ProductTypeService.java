package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.ProductTypeEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ProductTypeRepository;

import java.util.Collection;
import java.util.HashSet;

public class ProductTypeService implements CrudInterface<ProductTypeEntity> {
    private final ProductTypeRepository productTypeRepository;

    public ProductTypeService() {
        this.productTypeRepository = new ProductTypeRepository();
    }

    @Override
    public ProductTypeEntity create(ProductTypeEntity productType) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.productTypeRepository.create(productType);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return productType;
    }

    @Override
    public ProductTypeEntity getById(Long id) {
        Transaction transaction = null;
        ProductTypeEntity productType = new ProductTypeEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            productType = this.productTypeRepository.getById(id);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return productType;
    }

    @Override
    public ProductTypeEntity deleteById(Long id) {
        Transaction transaction = null;
        ProductTypeEntity productType = new ProductTypeEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            productType = this.productTypeRepository.deleteById(id);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return productType;
    }

    @Override
    public ProductTypeEntity update(ProductTypeEntity productType) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.productTypeRepository.update(productType);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return productType;
    }

    @Override
    public Collection<ProductTypeEntity> getAll() {
        Transaction transaction = null;
        Collection<ProductTypeEntity> productTypes = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            productTypes = this.productTypeRepository.getAll();
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return productTypes;
    }
    public ProductTypeEntity isUniqueValue(String productTypeName){
        Transaction transaction = null;
        ProductTypeEntity productType = new ProductTypeEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            productType = productTypeRepository.isUniqueValue(productTypeName);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return productType;
    }


}

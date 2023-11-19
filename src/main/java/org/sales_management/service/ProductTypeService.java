package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.ProductTypeEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ProductTypeRepository;
import org.sales_management.repository.StockHistoryRepository;

import java.util.Collection;
import java.util.HashSet;

public class ProductTypeService implements CrudInterface<ProductTypeEntity> {
    private final ProductTypeRepository productTypeRepository;
    private final StockHistoryRepository stockHistoryRepository;

    public ProductTypeService() {
        this.productTypeRepository = new ProductTypeRepository();
        this.stockHistoryRepository = new StockHistoryRepository();
    }

    @Override
    public ProductTypeEntity create(ProductTypeEntity product) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.productTypeRepository.create(product);
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
    public ProductTypeEntity getById(Long id) {
        Transaction transaction = null;
        ProductTypeEntity product = new ProductTypeEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            product = this.productTypeRepository.getById(id);
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
    public ProductTypeEntity deleteById(Long id) {
        Transaction transaction = null;
        ProductTypeEntity product = new ProductTypeEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            product = this.productTypeRepository.deleteById(id);
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
    public ProductTypeEntity update(ProductTypeEntity new_product) {
        Transaction transaction = null;
        ProductTypeEntity product = new ProductTypeEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            product = this.productTypeRepository.update(new_product);
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
    public Collection<ProductTypeEntity> getAll() {
        Transaction transaction = null;
        Collection<ProductTypeEntity> products = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            products = this.productTypeRepository.getAll();
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return products;
    }
    public Collection<ProductTypeEntity> searchProductsByName(String name){
        Session session = null;
        Transaction transaction = null;
        Collection<ProductTypeEntity> foundProducts = new HashSet<>();
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query<ProductTypeEntity> productTypeEntityQuery = session.createQuery("from ProductTypeEntity where name like :name", ProductTypeEntity.class);
            productTypeEntityQuery.setParameter("name","%"+name+"%");
            foundProducts = productTypeEntityQuery.getResultList();
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
        return foundProducts;
    }

    public ProductTypeEntity isProductNameExists(String productName) {
        Session session = null;
        Transaction transaction = null;
        ProductTypeEntity product = new ProductTypeEntity();
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            product = this.productTypeRepository.getProductByName(productName);
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
        return product;
    }
}

package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.ProductEntity;
import org.sales_management.entity.StockHistoryEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ProductRepository;
import org.sales_management.repository.StockHistoryRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

public class ProductService implements CrudInterface<ProductEntity> {
    private final ProductRepository productRepository;
    private final StockHistoryRepository stockHistoryRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
        this.stockHistoryRepository = new StockHistoryRepository();
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
    public ProductEntity update(ProductEntity new_product) {
        Transaction transaction = null;
        ProductEntity product = new ProductEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            product = this.productRepository.update(new_product);
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
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return products;
    }
    public ProductEntity addProduct(int quantity_added,ProductEntity product) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            if (product!=null){
                StockHistoryEntity stockHistory = new StockHistoryEntity();
                stockHistory.setArrivalDate(LocalDateTime.now());
                stockHistory.setQuantity(quantity_added);
                stockHistory.setAction("+");
                stockHistory.setProduct(product);
                product.setQuantity(product.getQuantity()+quantity_added);
                this.stockHistoryRepository.create(stockHistory);
                this.productRepository.update(product);
                transaction.commit();
            }
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return product;
    }
    public ProductEntity retireProduct(int quantity_retired, ProductEntity product){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            if (product!=null){
                if(product.getQuantity()>=quantity_retired){
                    StockHistoryEntity stockHistory = new StockHistoryEntity();
                    stockHistory.setQuantity(quantity_retired);
                    stockHistory.setArrivalDate(LocalDateTime.now());
                    stockHistory.setAction("-");
                    stockHistory.setProduct(product);
                    product.setQuantity(product.getQuantity()-quantity_retired);
                    session.persist(stockHistory);
                    session.merge(product);
                    transaction.commit();
                }
            }
        }
        catch (Exception e){
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
    public ProductEntity shareProduct(int quantity_shared, ProductEntity product){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            if (this.productRepository.shareProduct(product, quantity_shared)!=null){
                transaction.commit();
            }
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return product;
    }
}

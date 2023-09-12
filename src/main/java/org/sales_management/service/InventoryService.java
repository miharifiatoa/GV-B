package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.InventoryEntity;
import org.sales_management.entity.ProductEntity;
import org.sales_management.entity.ShopEntity;
import org.sales_management.repository.InventoryRepository;
import org.sales_management.repository.ProductRepository;

import java.util.Collection;

public class InventoryService {
    private ProductRepository productRepository;
    private ShopService shopService;
    private InventoryRepository inventoryRepository;

    public InventoryService() {
        this.productRepository = new ProductRepository();
        this.shopService = new ShopService();
        this.inventoryRepository = new InventoryRepository();
    }
    public InventoryEntity getById(Long inventory_id){
        Transaction transaction = null;
        InventoryEntity inventory = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            inventory = this.inventoryRepository.getById(inventory_id);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return inventory;
    }
    private Collection<ProductEntity> shareProducts(Collection<ProductEntity> products, ShopEntity shop){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.inventoryRepository.shareProducts(products,shop);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return products;
    }
}

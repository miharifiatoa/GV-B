package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.InventoryEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.InventoryRepository;
import org.sales_management.repository.ProductRepository;

import java.util.Collection;
import java.util.HashSet;

public class InventoryService implements CrudInterface<InventoryEntity> {
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public InventoryService() {
        this.productRepository = new ProductRepository();
        this.inventoryRepository = new InventoryRepository();
    }

    @Override
    public InventoryEntity create(InventoryEntity inventory) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.inventoryRepository.create(inventory);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return inventory;
    }
    @Override
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

    @Override
    public InventoryEntity deleteById(Long id) {
        return null;
    }

    @Override
    public InventoryEntity update(InventoryEntity obj) {
        return null;
    }

    @Override
    public Collection<InventoryEntity> getAll() {
        Transaction transaction = null;
        Collection<InventoryEntity> inventories = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            inventories = this.inventoryRepository.getAll();
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return inventories;
    }

//    public ProductEntity shareProducts(ProductEntity product, ShopEntity shop,int quantity_shared){
//        Transaction transaction = null;
//        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
//            transaction = session.beginTransaction();
//            if (product.getQuantity()>=quantity_shared){
//                product.setQuantity(quantity_shared);
//                ShopProductEntity shopProduct = new ShopProductEntity();
//                shopProduct.setShop(shop);
//                shopProduct.setProduct(product);
//                this.productRepository.shareProduct(product, quantity_shared);
//                this.shopProductRepository.create(shopProduct);
//                transaction.commit();
//            }
//            else System.out.println("Produits insuffisant");
//        }
//        catch (Exception e){
//            if (transaction!=null){
//                transaction.rollback();
//            }
//        }
//        return product;
//    }
}

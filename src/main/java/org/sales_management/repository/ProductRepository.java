package org.sales_management.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.ProductEntity;
import org.sales_management.entity.StockHistoryEntity;
import org.sales_management.interfaces.CrudInterface;

import java.time.LocalDateTime;
import java.util.Collection;

public class ProductRepository implements CrudInterface<ProductEntity> {
    @Override
    public ProductEntity create(ProductEntity product) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(product);
        return product;
    }

    @Override
    public ProductEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(ProductEntity.class,id);
    }

    @Override
    public ProductEntity deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ProductEntity product = getById(id);
        if (product!=null){
            session.remove(product);
        }
        return product;
    }

    @Override
    public ProductEntity update(ProductEntity product) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        if (product!=null){
            session.merge(product);
        }
        return product;
    }

    @Override
    public Collection<ProductEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from ProductEntity",ProductEntity.class).getResultList();
    }
//    public ProductEntity shareProduct(ProductEntity product, int quantity_shared){
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        if (product!=null){
//            if(product.getQuantity()>=quantity_shared){
//                StockHistoryEntity stockHistory = new StockHistoryEntity();
//                stockHistory.setQuantity(quantity_shared);
//                stockHistory.setArrivalDate(LocalDateTime.now());
//                stockHistory.setAction("/");
//                stockHistory.setProduct(product);
//                product.setQuantity(product.getQuantity()-quantity_shared);
//                session.persist(stockHistory);
//                session.merge(product);
//            }
//            else System.out.println("Produit insuffisant");
//        }
//        return product;
//    }
}

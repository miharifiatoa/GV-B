package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.*;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.*;

import java.util.Collection;
import java.util.HashSet;

public class ArticleTypeService implements CrudInterface<ArticleTypeEntity> {
    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ArticleTypeRepository articleTypeRepository;

    public ArticleTypeService() {
        this.articleTypeRepository = new ArticleTypeRepository();
        this.productTypeRepository = new ProductTypeRepository();
        this.productRepository = new ProductRepository();
    }

    @Override
    public ArticleTypeEntity create(ArticleTypeEntity priceVariation) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.articleTypeRepository.create(priceVariation);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return priceVariation;
    }

    @Override
    public ArticleTypeEntity getById(Long id) {
        Transaction transaction = null;
        ArticleTypeEntity priceVariation = new ArticleTypeEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            priceVariation = this.articleTypeRepository.getById(id);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return priceVariation;
    }

    @Override
    public ArticleTypeEntity deleteById(Long id) {
        Transaction transaction = null;
        ArticleTypeEntity priceVariation = new ArticleTypeEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            priceVariation = this.articleTypeRepository.deleteById(id);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return priceVariation;
    }

    @Override
    public ArticleTypeEntity update(ArticleTypeEntity new_priceVariation) {
        Transaction transaction = null;
        ArticleTypeEntity priceVariation = new ArticleTypeEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            priceVariation = this.articleTypeRepository.update(new_priceVariation);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return priceVariation;
    }

    @Override
    public Collection<ArticleTypeEntity> getAll() {
        Transaction transaction = null;
        Collection<ArticleTypeEntity> priceVariations = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            priceVariations = this.articleTypeRepository.getAll();
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return priceVariations;
    }
//    public ArticleTypeEntity isUniqueValue(String articleName){
//        Transaction transaction = null;
//        ArticleTypeEntity article = new ArticleTypeEntity();
//        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
//            transaction = session.beginTransaction();
//            article = articleTypeRepository.isUniqueValue(articleName);
//            transaction.commit();
//        }
//        catch (Exception e){
//            if (transaction!=null){
//                transaction.rollback();
//            }
//        }
//        return article;
//    }

}

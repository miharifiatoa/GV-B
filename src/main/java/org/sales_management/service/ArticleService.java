package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.*;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.*;

import java.util.Collection;
import java.util.HashSet;

public class ArticleService implements CrudInterface<ArticleEntity> {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final ArticleRepository articleRepository;

    public ArticleService() {
        this.articleRepository = new ArticleRepository();
        this.productRepository = new ProductRepository();
        this.productCategoryRepository = new ProductCategoryRepository();
    }

    @Override
    public ArticleEntity create(ArticleEntity priceVariation) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.articleRepository.create(priceVariation);
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
    public ArticleEntity getById(Long id) {
        Transaction transaction = null;
        ArticleEntity priceVariation = new ArticleEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            priceVariation = this.articleRepository.getById(id);
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
    public ArticleEntity deleteById(Long id) {
        Transaction transaction = null;
        ArticleEntity priceVariation = new ArticleEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            priceVariation = this.articleRepository.deleteById(id);
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
    public ArticleEntity update(ArticleEntity new_priceVariation) {
        Transaction transaction = null;
        ArticleEntity priceVariation = new ArticleEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            priceVariation = this.articleRepository.update(new_priceVariation);
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
    public Collection<ArticleEntity> getAll() {
        Transaction transaction = null;
        Collection<ArticleEntity> priceVariations = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            priceVariations = this.articleRepository.getAll();
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return priceVariations;
    }
    public Collection<ArticleEntity> searchArticleByByCode(String code){
        Transaction transaction = null;
        Collection<ArticleEntity> foundArticles = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query<ArticleEntity> arrivalEntityQuery = session.createQuery("from ArticleEntity where code like :code", ArticleEntity.class);
            arrivalEntityQuery.setParameter("code","%"+code+"%");
            foundArticles = arrivalEntityQuery.getResultList();
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return foundArticles;
    }
    public ArticleEntity isUniqueValue(String articleName){
        Transaction transaction = null;
        ArticleEntity article = new ArticleEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            article = articleRepository.isUniqueValue(articleName);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return article;
    }

}

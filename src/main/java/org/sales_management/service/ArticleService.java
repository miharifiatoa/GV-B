package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.ArticleEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ArticleRepository;

import java.util.Collection;
import java.util.HashSet;

public class ArticleService implements CrudInterface<ArticleEntity> {
    private final ArticleRepository articleRepository;

    public ArticleService() {
        this.articleRepository = new ArticleRepository();
    }

    @Override
    public ArticleEntity create(ArticleEntity productType) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.articleRepository.create(productType);
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
    public ArticleEntity getById(Long id) {
        Transaction transaction = null;
        ArticleEntity productType = new ArticleEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            productType = this.articleRepository.getById(id);
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
    public ArticleEntity deleteById(Long id) {
        Transaction transaction = null;
        ArticleEntity productType = new ArticleEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            productType = this.articleRepository.deleteById(id);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return productType;
    }

    @Override
    public ArticleEntity update(ArticleEntity productType) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.articleRepository.update(productType);
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
    public Collection<ArticleEntity> getAll() {
        Transaction transaction = null;
        Collection<ArticleEntity> productTypes = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            productTypes = this.articleRepository.getAll();
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return productTypes;
    }
    public ArticleEntity isUniqueValue(String productTypeName){
        Transaction transaction = null;
        ArticleEntity productType = new ArticleEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            productType = articleRepository.isUniqueValue(productTypeName);
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

package org.sales_management.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.HibernateUtil;
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
    public ArticleEntity create(ArticleEntity article) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.articleRepository.create(article);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return article;
    }

    @Override
    public ArticleEntity getById(Long id) {
        Transaction transaction = null;
        ArticleEntity article = new ArticleEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            article = this.articleRepository.getById(id);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return article;
    }

    @Override
    public ArticleEntity deleteById(Long id) {
        Transaction transaction = null;
        ArticleEntity article = new ArticleEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            article = this.articleRepository.deleteById(id);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return article;
    }

    @Override
    public ArticleEntity update(ArticleEntity obj) {
        return null;
    }

    @Override
    public Collection<ArticleEntity> getAll() {
        Transaction transaction = null;
        Collection<ArticleEntity> articles = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            articles = this.articleRepository.getAll();
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return articles;
    }
    public boolean isUniqueValue(String code){
        boolean isUnique = false;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            isUnique = this.articleRepository.isUniqueValue(code);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return isUnique;
    }
}

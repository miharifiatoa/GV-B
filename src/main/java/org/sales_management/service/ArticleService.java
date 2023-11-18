package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.sales_management.entity.ArticleTypeEntity;
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
    public ArticleEntity isUniqueValue(String code){
        Transaction transaction = null;
        ArticleEntity productType = new ArticleEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            productType = articleRepository.isUniqueValue(code);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return productType;
    }
    public Collection<ArticleEntity> searchArticleByCode(String code){
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
}

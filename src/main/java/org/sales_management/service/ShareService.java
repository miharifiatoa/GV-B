package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.entity.*;
import org.sales_management.session.HibernateUtil;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ArticleTypeRepository;
import org.sales_management.repository.ShareRepository;
import org.sales_management.session.SessionManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

public class ShareService implements CrudInterface<ShareEntity> {
    private final ShareRepository shareRepository;
    private final ArticleTypeRepository articleTypeRepository;

    public ShareService() {
        this.articleTypeRepository = new ArticleTypeRepository();
        this.shareRepository = new ShareRepository();
    }

    @Override
    public ShareEntity create(ShareEntity share) {
        return null;
    }

    @Override
    public ShareEntity getById(Long id) {
        return null;
    }

    @Override
    public ShareEntity deleteById(Long id) {
        return null;
    }

    @Override
    public ShareEntity update(ShareEntity obj) {
        return null;
    }

    @Override
    public Collection<ShareEntity> getAll() {
        Transaction transaction = null;
        Collection<ShareEntity> shares = new HashSet<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            shares = shareRepository.getAll();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return shares;
    }
    public ShareEntity toShareArticles(ShareEntity share , Collection<ArticleTypeEntity> articles){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            for (ArticleTypeEntity article : articles){
                ShareArticleEntity shareArticle = new ShareArticleEntity();
                shareArticle.setArticleType(article);
                shareArticle.setShare(share);
                shareArticle.setQuantity(article.getQuantity());
                shareArticle.setShareDate(LocalDateTime.now());
                shareArticle.setCanceled(false);
                session.persist(shareArticle);
                ArticleTypeEntity articleTypeEntity = articleTypeRepository.getById(article.getId());
                articleTypeEntity.setQuantity(articleTypeEntity.getQuantity() - article.getQuantity());
                session.merge(articleTypeEntity);
            }
            session.persist(share);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return share;
    }
    public ShareEntity toCancelShare(ShareEntity share){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            share.setCanceled(true);
            share.setUser(SessionManager.getSession().getCurrentUser());
            session.merge(share);
            for (ShareArticleEntity shareArticle : share.getShareArticles()){
                shareArticle.setCanceled(true);
                ArticleTypeEntity article = shareArticle.getArticleType();
                article.setQuantity(article.getQuantity() + shareArticle.getQuantity());
                session.merge(shareArticle);
                session.merge(article);
            }
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return share;
    }
    public Collection<ShareEntity> getSharesByDate(LocalDate localDate) {
        Transaction transaction = null;
        Collection<ShareEntity> shares = new HashSet<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            shares = shareRepository.getSharesByDate(localDate);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return shares;
    }
    public Collection<ShareEntity> getAllSharesByDate(LocalDate localDate) {
        Transaction transaction = null;
        Collection<ShareEntity> shares = new HashSet<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            shares = shareRepository.getAllSharesByDate(localDate);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return shares;
    }
}

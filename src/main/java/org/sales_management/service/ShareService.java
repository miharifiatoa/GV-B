package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.entity.*;
import org.sales_management.session.HibernateUtil;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ArticleRepository;
import org.sales_management.repository.ShareRepository;

import java.util.Collection;
import java.util.HashSet;

public class ShareService implements CrudInterface<ShareEntity> {
    private final ShareRepository shareRepository;
    private final ArticleRepository articleRepository;

    public ShareService() {
        this.articleRepository = new ArticleRepository();
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
    public ShareEntity toShareArticles(ShareEntity share , Collection<ArticleEntity> articles){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            for (ArticleEntity article : articles){
                ShareArticleEntity shareArticle = new ShareArticleEntity();
                shareArticle.setArticle(article);
                shareArticle.setShare(share);
                shareArticle.setQuantity(article.getQuantity());
                session.persist(shareArticle);
                ArticleEntity articleEntity = articleRepository.getById(article.getId());
                articleEntity.setQuantity(articleEntity.getQuantity() - article.getQuantity());
                session.merge(articleEntity);
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
            session.merge(share);
            for (ShareArticleEntity shareArticle : share.getShareArticles()){
                ArticleEntity article = shareArticle.getArticle();
                article.setQuantity(article.getQuantity() + shareArticle.getQuantity());
                session.merge(shareArticle.getArticle());
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

}

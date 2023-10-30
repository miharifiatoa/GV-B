package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.ArticleEntity;
import org.sales_management.entity.SaleArticleEntity;
import org.sales_management.entity.SaleEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ArticleRepository;
import org.sales_management.repository.SaleArticleRepository;
import org.sales_management.repository.SaleRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

public class SaleService implements CrudInterface<SaleEntity> {
    private final SaleRepository saleRepository;
    private final SaleArticleRepository saleArticleRepository;
    private final ArticleRepository articleRepository;

    public SaleService() {
        this.saleArticleRepository = new SaleArticleRepository();
        this.saleRepository = new SaleRepository();
        this.articleRepository = new ArticleRepository();
    }

    @Override
    public SaleEntity create(SaleEntity sale) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.saleRepository.create(sale);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return sale;
    }


    @Override
    public SaleEntity getById(Long id) {
        return null;
    }

    @Override
    public SaleEntity deleteById(Long id) {
        return null;
    }

    @Override
    public SaleEntity update(SaleEntity obj) {
        return null;
    }

    @Override
    public Collection<SaleEntity> getAll() {
        Transaction transaction = null;
        Collection<SaleEntity> sales = new HashSet<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            sales = this.saleRepository.getAll();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return sales;
    }
    public Collection<SaleEntity> getSalesByDate(LocalDate date) {
        Transaction transaction = null;
        Collection<SaleEntity> sales = new HashSet<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            sales = this.saleRepository.getSalesByDate(date);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return sales;
    }
    public SaleEntity toSaleArticles(SaleEntity sale, Collection<ArticleEntity> articlesToSale) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            for (ArticleEntity article : articlesToSale) {
                SaleArticleEntity saleArticle = new SaleArticleEntity();
                saleArticle.setSale(sale);
                saleArticle.setArticle(article);
                saleArticle.setQuantity(article.getQuantity());
                session.persist(saleArticle);
                ArticleEntity articleEntity = articleRepository.getById(article.getId());
                articleEntity.setQuantity(articleEntity.getQuantity() - article.getQuantity());
                session.merge(articleEntity);
            }
            session.persist(sale);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return sale;
    }
}

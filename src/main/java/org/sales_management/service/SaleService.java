package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.entity.*;
import org.sales_management.repository.PaymentRepository;
import org.sales_management.session.HibernateUtil;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ArticleRepository;
import org.sales_management.repository.SaleArticleRepository;
import org.sales_management.repository.SaleRepository;
import org.sales_management.session.SessionManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

public class SaleService implements CrudInterface<SaleEntity> {
    private final SaleRepository saleRepository;
    private final PaymentRepository paymentRepository;
    private final ArticleRepository articleRepository;

    public SaleService() {
        this.paymentRepository = new PaymentRepository();
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
    public SaleEntity update(SaleEntity sale) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.saleRepository.update(sale);
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
    public Collection<SaleEntity> getAcceptedAndPayedOrUnPayedSalesByDate(LocalDate date, boolean isPayed) {
        Transaction transaction = null;
        Collection<SaleEntity> sales = new HashSet<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            sales = this.saleRepository.getAcceptedAndPayedOrUnPayedSalesByDate(date,isPayed);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return sales;
    }
    public Collection<SaleEntity> getAllSalesByDate(LocalDate date) {
        Transaction transaction = null;
        Collection<SaleEntity> sales = new HashSet<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            sales = this.saleRepository.getAllSalesByDate(date);
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
                saleArticle.setSaleDate(LocalDateTime.now());
                saleArticle.setCanceled(false);
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
    public SaleEntity toCancelSale(SaleEntity sale){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            sale.setCanceled(true);
            sale.setUser(SessionManager.getSession().getCurrentUser());
            session.merge(sale);
            for (SaleArticleEntity saleArticle : sale.getSaleArticles()){
                saleArticle.setCanceled(true);
                ArticleEntity article = saleArticle.getArticle();
                article.setQuantity(article.getQuantity() + saleArticle.getQuantity());
                session.merge(saleArticle);
                session.merge(saleArticle.getArticle());
            }
            for (PaymentEntity payment : sale.getPayments()){
                PaymentEntity paymentEntity = paymentRepository.getById(payment.getId());
                session.remove(paymentEntity);
            }
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return sale;
    }
}

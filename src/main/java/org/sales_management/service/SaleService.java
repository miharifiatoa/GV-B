package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.entity.*;
import org.sales_management.repository.*;
import org.sales_management.session.HibernateUtil;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.session.SessionManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

public class SaleService implements CrudInterface<SaleEntity> {
    private final SaleRepository saleRepository;
    private final PaymentRepository paymentRepository;
    private final ArticleTypeRepository articleTypeRepository;
    private final ClientRepository clientRepository;

    public SaleService() {
        this.paymentRepository = new PaymentRepository();
        this.saleRepository = new SaleRepository();
        this.articleTypeRepository = new ArticleTypeRepository();
        this.clientRepository = new ClientRepository();
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
    public Collection<SaleEntity> getAcceptedAndPayedSalesByDate(LocalDate date) {
        Transaction transaction = null;
        Collection<SaleEntity> sales = new HashSet<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            sales = this.saleRepository.getAcceptedAndPayedSalesByDate(date);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return sales;
    }
    public Collection<SaleEntity> getAcceptedAndUnPayedSales() {
        Transaction transaction = null;
        Collection<SaleEntity> sales = new HashSet<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            sales = this.saleRepository.getAcceptedUnPayedSales();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return sales;
    }
    public Collection<SaleEntity> getAcceptedSalesByDate(LocalDate date) {
        Transaction transaction = null;
        Collection<SaleEntity> sales = new HashSet<>();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            sales = this.saleRepository.getAcceptedSalesByDate(date);
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
    public SaleEntity toSaleArticles(SaleEntity sale, Collection<ArticleTypeEntity> articlesToSale) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            for (ArticleTypeEntity articleType : articlesToSale) {
                SaleArticleEntity saleArticle = new SaleArticleEntity();
                saleArticle.setSale(sale);
                saleArticle.setArticleType(articleType);
                saleArticle.setQuantity(articleType.getQuantity());
                saleArticle.setSaleDate(LocalDateTime.now());
                saleArticle.setCanceled(false);
                ArticleTypeEntity articleTypeEntity = articleTypeRepository.getById(articleType.getId());
                articleTypeEntity.setQuantity(articleTypeEntity.getQuantity() - articleType.getQuantity());
                session.persist(saleArticle);
                session.merge(articleTypeEntity);
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
                ArticleTypeEntity articleType = saleArticle.getArticleType();
                articleType.setQuantity(articleType.getQuantity() + saleArticle.getQuantity());
                session.merge(saleArticle);
                session.merge(articleType);
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

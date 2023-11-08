package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.entity.*;
import org.sales_management.session.HibernateUtil;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ArrivalRepository;
import org.sales_management.repository.ArticleRepository;
import org.sales_management.session.SessionManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

public class ArrivalService implements CrudInterface<ArrivalEntity> {
    private final ArrivalRepository arrivalRepository;
    private final ArticleRepository articleRepository;

    public ArrivalService() {
        this.arrivalRepository = new ArrivalRepository();
        this.articleRepository = new ArticleRepository();
    }

    @Override
    public ArrivalEntity create(ArrivalEntity arrival) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.arrivalRepository.create(arrival);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return arrival;
    }

    @Override
    public ArrivalEntity getById(Long id) {
        return null;
    }

    @Override
    public ArrivalEntity deleteById(Long id) {
        return null;
    }

    @Override
    public ArrivalEntity update(ArrivalEntity obj) {
        return null;
    }

    @Override
    public Collection<ArrivalEntity> getAll() {
        Transaction transaction = null;
        Collection<ArrivalEntity> arrivals = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            arrivals = arrivalRepository.getAll();
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return arrivals;
    }
    public ArrivalEntity toSaveArrival(ArrivalEntity arrival , Collection<ArticleEntity> articles){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            for (ArticleEntity article : articles){
                ArrivalArticleEntity arrivalArticle = new ArrivalArticleEntity();
                arrivalArticle.setArrivalDate(LocalDateTime.now());
                arrivalArticle.setQuantity(article.getQuantity());
                arrivalArticle.setArrival(arrival);
                arrivalArticle.setArticle(article);
                arrivalArticle.setCanceled(false);
                session.persist(arrivalArticle);
                ArticleEntity articleEntity = articleRepository.getById(article.getId());
                articleEntity.setQuantity(articleEntity.getQuantity() + article.getQuantity());
                session.merge(articleEntity);
            }
            session.persist(arrival);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return arrival;
    }
    public ArrivalEntity toCancelArrival(ArrivalEntity arrival){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            arrival.setCanceled(true);
            arrival.setUser(SessionManager.getSession().getCurrentUser());
            session.merge(arrival);
            for (ArrivalArticleEntity arrivalArticle : arrival.getArrivalArticles()){
                arrivalArticle.setCanceled(true);
                ArticleEntity article = arrivalArticle.getArticle();
                article.setQuantity(article.getQuantity() - arrivalArticle.getQuantity());
                session.merge(arrivalArticle);
                session.merge(arrivalArticle.getArticle());
            }
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return arrival;
    }
    public Collection<ArrivalEntity> getArrivalsByDate(LocalDate localDate) {
        Transaction transaction = null;
        Collection<ArrivalEntity> arrivals = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            arrivals = arrivalRepository.getArrivalsByDate(localDate);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return arrivals;
    }
    public Collection<ArrivalEntity> getAllArrivalsByDate(LocalDate localDate) {
        Transaction transaction = null;
        Collection<ArrivalEntity> arrivals = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            arrivals = arrivalRepository.getAllArrivalsByDate(localDate);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return arrivals;
    }
}

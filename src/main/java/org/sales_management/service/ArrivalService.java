package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.ArrivalArticleEntity;
import org.sales_management.entity.ArrivalEntity;
import org.sales_management.entity.ArticleEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ArrivalRepository;
import org.sales_management.repository.ArticleRepository;

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
}

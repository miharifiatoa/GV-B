package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.entity.ArticleEntity;
import org.sales_management.entity.ShareArticleEntity;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.ArrivalArticleEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ArrivalArticleRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

public class ArrivalArticleService implements CrudInterface<ArrivalArticleEntity> {
    private final ArrivalArticleRepository arrivalArticleRepository;

    public ArrivalArticleService() {
        this.arrivalArticleRepository = new ArrivalArticleRepository();
    }

    @Override
    public ArrivalArticleEntity create(ArrivalArticleEntity arrivalArticle) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.arrivalArticleRepository.create(arrivalArticle);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return arrivalArticle;
    }

    @Override
    public ArrivalArticleEntity getById(Long id) {
        return null;
    }

    @Override
    public ArrivalArticleEntity deleteById(Long id) {
        return null;
    }

    @Override
    public ArrivalArticleEntity update(ArrivalArticleEntity arrivalArticle) {
        return null;
    }

    @Override
    public Collection<ArrivalArticleEntity> getAll() {
        return null;
    }
    public Collection<ArrivalArticleEntity> getArrivalsArticleInArticleByDate(ArticleEntity article, LocalDate date) {
        Collection<ArrivalArticleEntity> matchingArrivalArticles = new HashSet<>();
        for (ArrivalArticleEntity arrivalArticle : article.getArrivalArticles()) {
            LocalDate arrivalDate = arrivalArticle.getArrivalDate().toLocalDate();
            if (arrivalDate.equals(date)) {
                matchingArrivalArticles.add(arrivalArticle);
            }
        }
        return matchingArrivalArticles;
    }
}

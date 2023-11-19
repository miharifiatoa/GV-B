package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.entity.ArticleTypeEntity;
import org.sales_management.session.HibernateUtil;
import org.sales_management.entity.SaleArticleEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.SaleArticleRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

public class SaleArticleService implements CrudInterface<SaleArticleEntity> {
    private final SaleArticleRepository saleArticleRepository;

    public SaleArticleService() {
        this.saleArticleRepository = new SaleArticleRepository();
    }

    @Override
    public SaleArticleEntity create(SaleArticleEntity saleArticle) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.saleArticleRepository.create(saleArticle);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return saleArticle;
    }

    @Override
    public SaleArticleEntity getById(Long id) {
        return null;
    }

    @Override
    public SaleArticleEntity deleteById(Long id) {
        return null;
    }

    @Override
    public SaleArticleEntity update(SaleArticleEntity obj) {
        return null;
    }

    @Override
    public Collection<SaleArticleEntity> getAll() {
        return null;
    }
    public Collection<SaleArticleEntity> getSalesArticleInArticleByDate(ArticleTypeEntity article, LocalDate date) {
        Collection<SaleArticleEntity> matchingSaleArticles = new HashSet<>();
        for (SaleArticleEntity saleArticle : article.getSaleArticles()) {
            LocalDate saleDate = saleArticle.getSaleDate().toLocalDate();
            if (saleDate.equals(date)) {
                matchingSaleArticles.add(saleArticle);
            }
        }
        return matchingSaleArticles;
    }
}

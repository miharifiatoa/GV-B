package org.sales_management.service;

import org.sales_management.entity.ArticleEntity;
import org.sales_management.entity.ArticleTypeEntity;
import org.sales_management.entity.ShareArticleEntity;
import org.sales_management.interfaces.CrudInterface;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

public class ShareArticleService implements CrudInterface<ShareArticleEntity> {
    @Override
    public ShareArticleEntity create(ShareArticleEntity obj) {
        return null;
    }

    @Override
    public ShareArticleEntity getById(Long id) {
        return null;
    }

    @Override
    public ShareArticleEntity deleteById(Long id) {
        return null;
    }

    @Override
    public ShareArticleEntity update(ShareArticleEntity obj) {
        return null;
    }

    @Override
    public Collection<ShareArticleEntity> getAll() {
        return null;
    }
    public Collection<ShareArticleEntity> getShareArticlesByDate(ArticleTypeEntity articleType, LocalDate date) {
        Collection<ShareArticleEntity> matchingShareArticles = new HashSet<>();
        for (ShareArticleEntity shareArticle : articleType.getShareArticles()) {
            LocalDate shareDate = shareArticle.getShareDate().toLocalDate();
            if (shareDate.equals(date)) {
                matchingShareArticles.add(shareArticle);
            }
        }
        return matchingShareArticles;
    }
}

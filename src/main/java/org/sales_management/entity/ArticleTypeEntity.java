package org.sales_management.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "article_types")
public class ArticleTypeEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;
    @GeneratedValue
    @Id
    private Long id;
    private String size;
    private String color;
    private int quantity;
    @Column(nullable = false)
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity article;
    @OneToMany(mappedBy = "articleType" , fetch = FetchType.EAGER)
    private Collection<SaleArticleEntity> saleArticles;
    @OneToMany(mappedBy = "articleType" , fetch = FetchType.EAGER)
    private Collection<ShareArticleEntity> shareArticles;
    @OneToMany(mappedBy = "articleType" , fetch = FetchType.EAGER)
    private Collection<ArrivalArticleEntity> arrivalArticles;
    @OneToMany(mappedBy = "articleType", cascade = CascadeType.ALL)
    private Collection<StockHistoryEntity> stockHistories;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ArticleEntity getArticle() {
        return article;
    }

    public void setArticle(ArticleEntity article) {
        this.article = article;
    }

    public Collection<ShareArticleEntity> getShareArticles() {
        return shareArticles;
    }

    public void setShareArticles(Collection<ShareArticleEntity> shareArticles) {
        this.shareArticles = shareArticles;
    }

    public Collection<SaleArticleEntity> getSaleArticles() {
        return saleArticles;
    }

    public void setSaleArticles(Collection<SaleArticleEntity> saleArticles) {
        this.saleArticles = saleArticles;
    }

    public Collection<ArrivalArticleEntity> getArrivalArticles() {
        return arrivalArticles;
    }

    public void setArrivalArticles(Collection<ArrivalArticleEntity> arrivalArticles) {
        this.arrivalArticles = arrivalArticles;
    }

    public Collection<StockHistoryEntity> getStockHistories() {
        return stockHistories;
    }

    public void setStockHistories(Collection<StockHistoryEntity> stockHistories) {
        this.stockHistories = stockHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleTypeEntity that = (ArticleTypeEntity) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

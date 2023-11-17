package org.sales_management.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "sale_articles")
public class SaleArticleEntity implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private int quantity;
    private LocalDateTime saleDate;
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private SaleEntity sale;
    @ManyToOne
    @JoinColumn(name = "article_type_id")
    private ArticleTypeEntity articleType;
    @Column(nullable = false)
    private boolean isCanceled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public SaleEntity getSale() {
        return sale;
    }

    public void setSale(SaleEntity sale) {
        this.sale = sale;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public ArticleTypeEntity getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleTypeEntity articleType) {
        this.articleType = articleType;
    }
}

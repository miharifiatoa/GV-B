package org.sales_management.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "arrival_articles")
public class ArrivalArticleEntity implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private LocalDateTime arrivalDate;
    @ManyToOne
    @JoinColumn(name = "arrival_id")
    private ArrivalEntity arrival;
    @Column(nullable = false)
    private boolean isCanceled;
    @ManyToOne
    @JoinColumn(name = "article_type_id")
    private ArticleTypeEntity articleType;

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

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public ArrivalEntity getArrival() {
        return arrival;
    }

    public void setArrival(ArrivalEntity arrival) {
        this.arrival = arrival;
    }

    public ArticleTypeEntity getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleTypeEntity articleType) {
        this.articleType = articleType;
    }
}

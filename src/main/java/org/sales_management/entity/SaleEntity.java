package org.sales_management.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "sales")
public class SaleEntity{
    @Id
    @GeneratedValue
    private Long id;
    private String clientName;
    private String description;
    private LocalDateTime saleDate;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sale_articles",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id"))
    private Collection<ArticleEntity> articles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public Collection<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(Collection<ArticleEntity> articles) {
        this.articles = articles;
    }
}

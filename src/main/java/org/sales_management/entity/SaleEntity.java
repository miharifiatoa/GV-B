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
    @Column(nullable = false)
    private Boolean isCanceled;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @OneToMany(fetch = FetchType.EAGER , mappedBy = "sale" , cascade = CascadeType.ALL)
    private Collection<SaleArticleEntity> saleArticles;

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

    public Boolean getCanceled() {
        return isCanceled;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Collection<SaleArticleEntity> getSaleArticles() {
        return saleArticles;
    }

    public void setSaleArticles(Collection<SaleArticleEntity> saleArticles) {
        this.saleArticles = saleArticles;
    }
}

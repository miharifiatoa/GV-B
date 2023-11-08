package org.sales_management.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "sales")
public class SaleEntity implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private LocalDateTime saleDate;
    @Column(nullable = false)
    private Boolean isCanceled;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @OneToMany(fetch = FetchType.EAGER , mappedBy = "sale" , cascade = CascadeType.ALL)
    private Collection<SaleArticleEntity> saleArticles;
    @OneToMany(mappedBy = "sale")
    private Collection<PaymentEntity> payments;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Collection<PaymentEntity> getPayments() {
        return payments;
    }

    public void setPayments(Collection<PaymentEntity> payments) {
        this.payments = payments;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }
}

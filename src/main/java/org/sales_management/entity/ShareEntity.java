package org.sales_management.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "shares")
public class ShareEntity implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime shareDate;
    private boolean isCanceled;
    @OneToMany(mappedBy = "share" , fetch = FetchType.EAGER)
    private Collection<ShareArticleEntity> shareArticles;
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getShareDate() {
        return shareDate;
    }

    public void setShareDate(LocalDateTime shareDate) {
        this.shareDate = shareDate;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public Collection<ShareArticleEntity> getShareArticles() {
        return shareArticles;
    }

    public void setShareArticles(Collection<ShareArticleEntity> shareArticles) {
        this.shareArticles = shareArticles;
    }

    public ShopEntity getShop() {
        return shop;
    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

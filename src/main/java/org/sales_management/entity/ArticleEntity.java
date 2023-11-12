package org.sales_management.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "articles")
public class ArticleEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;
    @GeneratedValue
    @Id
    private Long id;
    @Column(nullable = false , unique = true)
    private String code;
    private String size;
    private String color;
    private int quantity;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductTypeEntity productType;
    @OneToMany(mappedBy = "article" , fetch = FetchType.EAGER)
    private Collection<SaleArticleEntity> saleArticles;
    @OneToMany(mappedBy = "article" , fetch = FetchType.EAGER)
    private Collection<ShareArticleEntity> shareArticles;
    @OneToMany(mappedBy = "article" , fetch = FetchType.EAGER)
    private Collection<ArrivalArticleEntity> arrivalArticles;
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private Collection<StockHistoryEntity> stockHistories;
    public Long getId() {
        return id;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ProductTypeEntity getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeEntity productType) {
        this.productType = productType;
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
        ArticleEntity that = (ArticleEntity) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

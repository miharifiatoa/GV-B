package org.sales_management.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
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
    private Double price;
    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductTypeEntity productType;
    @OneToMany(mappedBy = "article")
    private Collection<SaleArticleEntity> saleArticles;
    @OneToMany(mappedBy = "article")
    private Collection<ShareArticleEntity> shopArticles;

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

    public ProductTypeEntity getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeEntity productType) {
        this.productType = productType;
    }

    public Collection<ShareArticleEntity> getShopArticles() {
        return shopArticles;
    }

    public void setShopArticles(Collection<ShareArticleEntity> shopArticles) {
        this.shopArticles = shopArticles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleEntity that = (ArticleEntity) o;
        return Objects.equals(id, that.id);
    }

    public Collection<SaleArticleEntity> getSaleArticles() {
        return saleArticles;
    }

    public void setSaleArticles(Collection<SaleArticleEntity> saleArticles) {
        this.saleArticles = saleArticles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

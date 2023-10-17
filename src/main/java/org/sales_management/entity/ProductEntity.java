package org.sales_management.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "products")
public class ProductEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Collection<PriceVariationEntity> priceVariations;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity article;
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventory;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Collection<StockHistoryEntity> stockHistories;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<PriceVariationEntity> getPriceVariations() {
        return priceVariations;
    }

    public void setPriceVariations(Collection<PriceVariationEntity> priceVariations) {
        this.priceVariations = priceVariations;
    }

    public InventoryEntity getInventory() {
        return inventory;
    }

    public void setInventory(InventoryEntity inventory) {
        this.inventory = inventory;
    }

    public ArticleEntity getArticle() {
        return article;
    }

    public void setArticle(ArticleEntity article) {
        this.article = article;
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
        ProductEntity product = (ProductEntity) o;
        return Objects.equals(id, product.id);
    }
}

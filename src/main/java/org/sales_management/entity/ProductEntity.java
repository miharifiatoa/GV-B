package org.sales_management.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
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
    @Column(nullable = false , unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategoryEntity productCategory;
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventory;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Collection<StockHistoryEntity> stockHistories;
    @OneToMany(mappedBy = "product" , fetch = FetchType.EAGER)
    private Collection<ProductTypeEntity> productTypes;
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

    public InventoryEntity getInventory() {
        return inventory;
    }

    public void setInventory(InventoryEntity inventory) {
        this.inventory = inventory;
    }

    public ProductCategoryEntity getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategoryEntity productCategory) {
        this.productCategory = productCategory;
    }

    public Collection<StockHistoryEntity> getStockHistories() {
        return stockHistories;
    }

    public void setStockHistories(Collection<StockHistoryEntity> stockHistories) {
        this.stockHistories = stockHistories;
    }

    public Collection<ProductTypeEntity> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(Collection<ProductTypeEntity> productTypes) {
        this.productTypes = productTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity product = (ProductEntity) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

package org.sales_management.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "categories")
public class ProductCategoryEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "productCategory")
    private Collection<ProductEntity> products;

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

    public Collection<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Collection<ProductEntity> products) {
        this.products = products;
    }
}

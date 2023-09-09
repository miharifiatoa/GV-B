package org.sales_management.entity;

import jakarta.persistence.*;

import java.util.Collections;

@Entity
@Table(name = "inventories")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Integer code;
    @OneToMany(mappedBy = "inventory")
    private Collections products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Collections getProducts() {
        return products;
    }

    public void setProducts(Collections products) {
        this.products = products;
    }
}

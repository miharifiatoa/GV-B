package org.sales_management.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "inventories")
public class InventoryEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Integer code;

    @OneToMany(mappedBy = "inventory")
    private Collection<ProductTypeEntity> products;

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

    public Collection<ProductTypeEntity> getProducts() {
        return products;
    }

    public void setProducts(Collection<ProductTypeEntity> products) {
        this.products = products;
    }
}

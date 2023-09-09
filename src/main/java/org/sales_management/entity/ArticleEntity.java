package org.sales_management.entity;

import jakarta.persistence.*;

import java.util.Collections;

@Entity
@Table(name = "articles")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String code;
    private String label;
    private String family;
    @Transient
    @OneToMany(mappedBy = "article")
    private Collections products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Collections getProducts() {
        return products;
    }

    public void setProducts(Collections products) {
        this.products = products;
    }
}

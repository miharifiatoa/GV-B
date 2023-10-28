package org.sales_management.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "shops")
public class ShopEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String Address;
    private Long contact;
    private String email;
    @OneToMany(mappedBy = "shop", fetch = FetchType.EAGER)
    private Collection<ShopProductEntity> shop_products;

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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<ShopProductEntity> getShop_products() {
        return shop_products;
    }

    public void setShop_products(Collection<ShopProductEntity> shop_products) {
        this.shop_products = shop_products;
    }
}

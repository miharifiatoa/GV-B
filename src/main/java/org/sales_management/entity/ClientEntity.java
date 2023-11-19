package org.sales_management.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "clients")
public class ClientEntity implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telephone;
    private String name;
    @OneToMany(mappedBy = "client")
    private Collection<SaleEntity> sales;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<SaleEntity> getSales() {
        return sales;
    }

    public void setSales(Collection<SaleEntity> sales) {
        this.sales = sales;
    }
}

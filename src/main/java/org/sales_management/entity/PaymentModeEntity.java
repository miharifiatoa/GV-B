package org.sales_management.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "payment_modes")
public class PaymentModeEntity implements Serializable {
    @Serial
    private static  final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

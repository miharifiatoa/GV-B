package org.sales_management.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "payments")
public class PaymentEntity implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double pay;
    private LocalDateTime paymentDate;
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private SaleEntity sale;
    @ManyToOne
    @JoinColumn(name = "payment_mode_id")
    private PaymentModeEntity paymentMode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public SaleEntity getSale() {
        return sale;
    }

    public void setSale(SaleEntity sale) {
        this.sale = sale;
    }

    public PaymentModeEntity getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentModeEntity paymentMode) {
        this.paymentMode = paymentMode;
    }
}

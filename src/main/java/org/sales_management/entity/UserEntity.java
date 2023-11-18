package org.sales_management.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;
    private Long cin;
    private Long number;
    private String email;
    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;
    @OneToOne(mappedBy = "user")
    private AccountEntity account;
    @OneToMany(mappedBy = "user" , fetch = FetchType.EAGER)
    private Collection<ArrivalEntity> arrivals;
    @OneToMany(mappedBy = "user" , fetch = FetchType.EAGER)
    private Collection<SaleEntity> sales;
    @OneToMany(mappedBy = "user" , fetch = FetchType.EAGER)
    private Collection<ShareEntity> shares;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public Long getCin() {
        return cin;
    }

    public void setCin(Long cin) {
        this.cin = cin;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public Collection<ArrivalEntity> getArrivals() {
        return arrivals;
    }

    public void setArrivals(Collection<ArrivalEntity> arrivals) {
        this.arrivals = arrivals;
    }

    public Collection<SaleEntity> getSales() {
        return sales;
    }

    public void setSales(Collection<SaleEntity> sales) {
        this.sales = sales;
    }

    public Collection<ShareEntity> getShares() {
        return shares;
    }

    public void setShares(Collection<ShareEntity> shares) {
        this.shares = shares;
    }
}

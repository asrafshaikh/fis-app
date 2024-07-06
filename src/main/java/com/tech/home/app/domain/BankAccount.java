package com.tech.home.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tech.home.app.domain.enumeration.AccType;
import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A BankAccount.
 */
@Entity
@Table(name = "bank_account")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "account_number")
    private Long accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccType accountType;

    @Column(name = "balance")
    private Long balance;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bankAccount")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "transactionDetails", "bankAccount" }, allowSetters = true)
    private Set<Transaction> transactions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "bankAccounts" }, allowSetters = true)
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BankAccount id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountNumber() {
        return this.accountNumber;
    }

    public BankAccount accountNumber(Long accountNumber) {
        this.setAccountNumber(accountNumber);
        return this;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccType getAccountType() {
        return this.accountType;
    }

    public BankAccount accountType(AccType accountType) {
        this.setAccountType(accountType);
        return this;
    }

    public void setAccountType(AccType accountType) {
        this.accountType = accountType;
    }

    public Long getBalance() {
        return this.balance;
    }

    public BankAccount balance(Long balance) {
        this.setBalance(balance);
        return this;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public BankAccount createdAt(Instant createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public BankAccount updatedAt(Instant updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        if (this.transactions != null) {
            this.transactions.forEach(i -> i.setBankAccount(null));
        }
        if (transactions != null) {
            transactions.forEach(i -> i.setBankAccount(this));
        }
        this.transactions = transactions;
    }

    public BankAccount transactions(Set<Transaction> transactions) {
        this.setTransactions(transactions);
        return this;
    }

    public BankAccount addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setBankAccount(this);
        return this;
    }

    public BankAccount removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setBankAccount(null);
        return this;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BankAccount customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankAccount)) {
            return false;
        }
        return getId() != null && getId().equals(((BankAccount) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankAccount{" +
            "id=" + getId() +
            ", accountNumber=" + getAccountNumber() +
            ", accountType='" + getAccountType() + "'" +
            ", balance=" + getBalance() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}

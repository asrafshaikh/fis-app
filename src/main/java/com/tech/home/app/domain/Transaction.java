package com.tech.home.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tech.home.app.domain.enumeration.TransactionType;
import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "transaction_date")
    private Instant transactionDate;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transaction")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "merchant", "transaction" }, allowSetters = true)
    private Set<TransactionDetail> transactionDetails = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "transactions", "customer" }, allowSetters = true)
    private BankAccount bankAccount;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Transaction id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return this.amount;
    }

    public Transaction amount(Long amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public Transaction transactionType(TransactionType transactionType) {
        this.setTransactionType(transactionType);
        return this;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Instant getTransactionDate() {
        return this.transactionDate;
    }

    public Transaction transactionDate(Instant transactionDate) {
        this.setTransactionDate(transactionDate);
        return this;
    }

    public void setTransactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return this.description;
    }

    public Transaction description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TransactionDetail> getTransactionDetails() {
        return this.transactionDetails;
    }

    public void setTransactionDetails(Set<TransactionDetail> transactionDetails) {
        if (this.transactionDetails != null) {
            this.transactionDetails.forEach(i -> i.setTransaction(null));
        }
        if (transactionDetails != null) {
            transactionDetails.forEach(i -> i.setTransaction(this));
        }
        this.transactionDetails = transactionDetails;
    }

    public Transaction transactionDetails(Set<TransactionDetail> transactionDetails) {
        this.setTransactionDetails(transactionDetails);
        return this;
    }

    public Transaction addTransactionDetail(TransactionDetail transactionDetail) {
        this.transactionDetails.add(transactionDetail);
        transactionDetail.setTransaction(this);
        return this;
    }

    public Transaction removeTransactionDetail(TransactionDetail transactionDetail) {
        this.transactionDetails.remove(transactionDetail);
        transactionDetail.setTransaction(null);
        return this;
    }

    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Transaction bankAccount(BankAccount bankAccount) {
        this.setBankAccount(bankAccount);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        return getId() != null && getId().equals(((Transaction) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transaction{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", transactionType='" + getTransactionType() + "'" +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}

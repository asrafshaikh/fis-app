package com.tech.home.app.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.tech.home.app.domain.TransactionDetail} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TransactionDetailDTO implements Serializable {

    private Long id;

    private Long amount;

    private Instant transactionDate;

    private String description;

    private MerchantDTO merchant;

    private TransactionDTO transaction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Instant getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MerchantDTO getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantDTO merchant) {
        this.merchant = merchant;
    }

    public TransactionDTO getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionDTO transaction) {
        this.transaction = transaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransactionDetailDTO)) {
            return false;
        }

        TransactionDetailDTO transactionDetailDTO = (TransactionDetailDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, transactionDetailDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransactionDetailDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", merchant=" + getMerchant() +
            ", transaction=" + getTransaction() +
            "}";
    }
}

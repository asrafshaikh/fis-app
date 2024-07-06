package com.tech.home.app.service.dto;

import com.tech.home.app.domain.enumeration.AccType;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.tech.home.app.domain.BankAccount} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BankAccountDTO implements Serializable {

    private Long id;

    private Long accountNumber;

    private AccType accountType;

    private Long balance;

    private Instant createdAt;

    private Instant updatedAt;

    private CustomerDTO customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccType accountType) {
        this.accountType = accountType;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankAccountDTO)) {
            return false;
        }

        BankAccountDTO bankAccountDTO = (BankAccountDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, bankAccountDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankAccountDTO{" +
            "id=" + getId() +
            ", accountNumber=" + getAccountNumber() +
            ", accountType='" + getAccountType() + "'" +
            ", balance=" + getBalance() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", customer=" + getCustomer() +
            "}";
    }
}

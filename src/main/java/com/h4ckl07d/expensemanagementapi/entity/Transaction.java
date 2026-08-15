package com.h4ckl07d.expensemanagementapi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(
        name = "transactions"
)
public class Transaction {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "amount",
            nullable = false
    )
    private long amount;

    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(
            name = "userId",
            updatable = false
    )
    private User user;
    private LocalDate createdAt;

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public long getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Transaction(long amount, User user, LocalDate createdAt, String description, TransactionType transactionType) {
        this.amount = amount;
        this.user = user;
        this.createdAt = createdAt;
        this.description = description;
        this.transactionType = transactionType;

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && amount == that.amount && Objects.equals(description, that.description) && Objects.equals(user, that.user) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, description, user, createdAt);
    }
}

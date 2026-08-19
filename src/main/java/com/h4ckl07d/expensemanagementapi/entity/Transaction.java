package com.h4ckl07d.expensemanagementapi.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
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
    private BigDecimal amount;

    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDate transactionDate;

    @PrePersist
    protected void onCreate() {
        this.transactionDate = LocalDate.now();
    }

    public Transaction() {
    }


    public Transaction(BigDecimal amount,String description, TransactionType transactionType) {
        this.amount = amount;
        this.description = description;
        this.transactionType = transactionType;

    }


    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
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

    public LocalDate getTransactionDate() {
        return transactionDate;
    }


    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setUser(User user) {
        this.user = user;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode(); // constant, safe even before id is assigned
    }
}

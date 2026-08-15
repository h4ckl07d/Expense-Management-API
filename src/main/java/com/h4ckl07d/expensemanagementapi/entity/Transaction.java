package com.h4ckl07d.expensemanagementapi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    private long amount;
    private String description;

    @ManyToOne
    private User userid;
    private LocalDate createdAt;

    public Transaction() {
    }

    public Transaction(long amount, User userid, LocalDate createdAt, String description) {
        this.amount = amount;
        this.userid = userid;
        this.createdAt = createdAt;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && amount == that.amount && Objects.equals(description, that.description) && Objects.equals(userid, that.userid) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, description, userid, createdAt);
    }
}

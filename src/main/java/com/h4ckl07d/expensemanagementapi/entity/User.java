package com.h4ckl07d.expensemanagementapi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(
        name = "User",
        uniqueConstraints={
                @UniqueConstraint(name = "user_email_unique", columnNames = "email")
        }
)
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "name",
            nullable = false

    )
    private String email;
    private LocalDate createdAt;

    public User() {
    }

    public User(String name, String email, LocalDate createdAt ) {
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(createdAt, user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, createdAt);
    }
}

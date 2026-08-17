package com.h4ckl07d.expensemanagementapi.repository;

import com.h4ckl07d.expensemanagementapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}

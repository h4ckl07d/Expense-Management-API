package com.h4ckl07d.expensemanagementapi.repository;

import com.h4ckl07d.expensemanagementapi.entity.Transaction;
import com.h4ckl07d.expensemanagementapi.entity.TransactionType;
import com.h4ckl07d.expensemanagementapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserAndTransactionType(User user, TransactionType transactionType);
}

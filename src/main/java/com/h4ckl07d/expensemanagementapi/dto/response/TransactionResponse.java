package com.h4ckl07d.expensemanagementapi.dto.response;

import com.h4ckl07d.expensemanagementapi.entity.Transaction;
import com.h4ckl07d.expensemanagementapi.entity.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponse(
        Long id,
        BigDecimal amount,
        TransactionType transactionType,
        String description,
        LocalDate transactionDate,
        Long userId) {
    public static TransactionResponse from(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getDescription(),
                transaction.getTransactionDate(),
                transaction.getUser().getId()
        );
    }
}

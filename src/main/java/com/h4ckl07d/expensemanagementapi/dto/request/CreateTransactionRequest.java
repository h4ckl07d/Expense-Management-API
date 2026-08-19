package com.h4ckl07d.expensemanagementapi.dto.request;

import com.h4ckl07d.expensemanagementapi.entity.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateTransactionRequest (
        @NotNull(message = "Enter Valid amount")
        @Positive
         BigDecimal amount,

        @NotBlank
        @Size(max =100, message = "Enter 100 character")
        String description,

        @NotNull
        TransactionType transactionType
){}

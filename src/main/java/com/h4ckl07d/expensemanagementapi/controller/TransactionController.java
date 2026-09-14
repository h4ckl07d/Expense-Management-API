package com.h4ckl07d.expensemanagementapi.controller;

import com.h4ckl07d.expensemanagementapi.dto.request.CreateTransactionRequest;
import com.h4ckl07d.expensemanagementapi.dto.response.TransactionResponse;
import com.h4ckl07d.expensemanagementapi.entity.Transaction;
import com.h4ckl07d.expensemanagementapi.entity.TransactionType;
import com.h4ckl07d.expensemanagementapi.entity.User;
import com.h4ckl07d.expensemanagementapi.repository.TransactionRepository;
import com.h4ckl07d.expensemanagementapi.repository.UserRepository;
import com.h4ckl07d.expensemanagementapi.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;


    public TransactionController(TransactionService transactionService, UserRepository userRepository, TransactionRepository transactionRepository) {
        this.transactionService = transactionService;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(
            @Valid @RequestBody CreateTransactionRequest request ){

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User currentUser = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        TransactionResponse transaction = transactionService.createTransaction(request, currentUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransactionById(
            @PathVariable Long id
    ){
        TransactionResponse transactionResponse = transactionService.getTransactionById(id);
        return ResponseEntity
                .ok(transactionResponse);
    }
    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTransactions(
            @RequestParam(required = false)TransactionType type
            ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        List<TransactionResponse> transaction = (type != null)
                ? transactionService.getTransactionByType(currentUser, type)
                : transactionService.getAllTransactions(currentUser);

        return ResponseEntity.ok(transaction);

    }
}

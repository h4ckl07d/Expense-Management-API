package com.h4ckl07d.expensemanagementapi.controller;

import com.h4ckl07d.expensemanagementapi.dto.request.CreateTransactionRequest;
import com.h4ckl07d.expensemanagementapi.dto.response.TransactionResponse;
import com.h4ckl07d.expensemanagementapi.entity.Transaction;
import com.h4ckl07d.expensemanagementapi.entity.User;
import com.h4ckl07d.expensemanagementapi.repository.TransactionRepository;
import com.h4ckl07d.expensemanagementapi.repository.UserRepository;
import com.h4ckl07d.expensemanagementapi.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        User currentUser = userRepository.findById(1L)
                .orElseThrow(() -> new IllegalStateException("Temp user not found — seed a user with id=1"));
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
    public ResponseEntity<List<TransactionResponse>> getAllTransactions(){
        List<TransactionResponse> transaction = transactionService.getAllTransactions();
        return ResponseEntity.ok(transaction);
    }
}

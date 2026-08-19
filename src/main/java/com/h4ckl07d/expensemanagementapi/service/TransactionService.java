package com.h4ckl07d.expensemanagementapi.service;

import com.h4ckl07d.expensemanagementapi.dto.request.CreateTransactionRequest;
import com.h4ckl07d.expensemanagementapi.dto.response.TransactionResponse;
import com.h4ckl07d.expensemanagementapi.entity.Transaction;
import com.h4ckl07d.expensemanagementapi.entity.User;
import com.h4ckl07d.expensemanagementapi.repository.TransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalApplicationListenerAdapter;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionResponse createTransaction(CreateTransactionRequest request, User currentUser){

        Transaction transaction = new Transaction();

        transaction.setAmount(request.amount());
        transaction.setTransactionType(request.transactionType());
        transaction.setDescription(request.description());

        transaction.setUser(currentUser);

        Transaction savedResponse = transactionRepository.save(transaction);
        return TransactionResponse.from(savedResponse);
    }

    public TransactionResponse getTransactionById(Long id){
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + "Not Found" ) );
                return TransactionResponse.from(transaction);
    }
    public List<TransactionResponse> getAllTransactions(){
        List<Transaction> response = transactionRepository.findAll();
        return response.stream()
                .map(TransactionResponse::from)
                .toList();
    }

}

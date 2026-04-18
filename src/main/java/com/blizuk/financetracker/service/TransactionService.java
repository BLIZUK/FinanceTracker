package com.blizuk.financetracker.service;

import com.blizuk.financetracker.model.Transaction;
import com.blizuk.financetracker.model.TransactionType;
import com.blizuk.financetracker.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionService {
    private final TransactionRepository repository = new TransactionRepository();


    public void addTransaction(double amount, TransactionType type, Long categoryId, String desc)
    {
        Transaction tx = new Transaction();
        tx.setAmount(amount);
        tx.setType(type);
        tx.setCategoryId(categoryId);
        tx.setDescription(desc);
        tx.setCreatedAt(LocalDateTime.now());

        repository.save(tx);
    }


    public List<Transaction> getAllTransaction()
    {
        return repository.findAll();
    }
}

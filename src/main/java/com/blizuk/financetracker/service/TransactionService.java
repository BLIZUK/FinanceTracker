package com.blizuk.financetracker.service;

import com.blizuk.financetracker.model.Transaction;
import com.blizuk.financetracker.model.TransactionType;
import com.blizuk.financetracker.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TransactionService {
    private final TransactionRepository repository = new TransactionRepository();


    public void addTransaction(double amount, TransactionType type, Long categoryId, String desc)
    {
        Transaction tx = new Transaction();
        LocalDateTime now = LocalDateTime.now();
        tx.setAmount(amount);
        tx.setType(type);
        tx.setCategoryId(categoryId);
        tx.setDescription(desc);
        tx.setCreatedAt(now.truncatedTo(ChronoUnit.MINUTES));

        repository.save(tx);
    }


    public List<Transaction> getAllTransaction()
    {
        return repository.findAll();
    }


    public void deleteTransaction(int id){repository.del(id);}
}

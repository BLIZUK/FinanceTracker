package com.blizuk.financetracker.service;

import com.blizuk.financetracker.model.Transaction;
import com.blizuk.financetracker.model.TransactionType;
import com.blizuk.financetracker.repository.TransactionRepository;
import com.blizuk.financetracker.view.dto.TransactionInputData;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TransactionService {
    private static final TransactionRepository repository = new TransactionRepository();


    public static void addTransaction(Long userid, double amount, TransactionType type, Long categoryId, String desc)
    {
        LocalDateTime now = LocalDateTime.now();
        Transaction tx = new Transaction(userid, amount, type, categoryId, desc, now.truncatedTo(ChronoUnit.MINUTES));
        repository.save(tx);
    }

    public static void addTransaction(TransactionInputData data, Long currentUserId) {
        // Здесь может быть валидация: if (data.amount() <= 0) throw ...

        LocalDateTime now = LocalDateTime.now();
        Transaction tx = new Transaction(currentUserId, data.amount(), data.type(), data.categoryId(), data.description(), now.truncatedTo(ChronoUnit.MINUTES) );
         repository.save(tx);
    }


    public List<Transaction> getAllTransaction()
    {
        return repository.findAll();
    }


    public void deleteTransaction(int id){repository.del(id);}
}

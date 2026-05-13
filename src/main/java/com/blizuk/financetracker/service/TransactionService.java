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


    public static void addTransaction(double amount, TransactionType type, Long categoryId, String desc)
    {
        // УБРАТЬ СЕТТЕРЫ И ОСТАВИТЬ ТОЛЬКО КОНСТРУКТОР
        Transaction tx = new Transaction();
        LocalDateTime now = LocalDateTime.now();
        tx.setAmount(amount);
        tx.setType(type);
        tx.setCategoryId(categoryId);
        tx.setDescription(desc);
        tx.setCreatedAt(now.truncatedTo(ChronoUnit.MINUTES));

        repository.save(tx);
    }

    public static void addTransaction(TransactionInputData data, Long currentUserId) {
        // Здесь может быть валидация: if (data.amount() <= 0) throw ...

        Transaction tx = new Transaction();
        tx.setAmount(data.amount());
        tx.setType(data.type());
        tx.setDescription(data.description());
        tx.setUserId(currentUserId); // ID берем из контекста текущего пользователя

         repository.save(tx);
    }


    public List<Transaction> getAllTransaction()
    {
        return repository.findAll();
    }


    public void deleteTransaction(int id){repository.del(id);}
}

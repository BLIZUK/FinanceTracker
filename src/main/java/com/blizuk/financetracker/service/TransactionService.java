package com.blizuk.financetracker.service;

import com.blizuk.financetracker.model.Transaction;
import com.blizuk.financetracker.repository.TransactionRepository;
import com.blizuk.financetracker.view.dto.TransactionInputData;
import java.util.List;

public class TransactionService {
    private static final TransactionRepository repository = new TransactionRepository();


    public static void addTransaction(TransactionInputData data) {
        // Здесь может быть валидация: if (data.amount() <= 0) throw ...
        Transaction tx = new Transaction(data.userId(), data.amount(), data.type(), data.categoryId(), data.description(), data.time() );
         repository.save(tx);
    }


    public List<Transaction> getAllTransaction()
    {
        return repository.findAll();
    }


    public void deleteTransaction(int id){repository.del(id);}
}

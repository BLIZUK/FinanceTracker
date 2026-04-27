package com.blizuk.financetracker.controller;

import com.blizuk.financetracker.model.Transaction;
import com.blizuk.financetracker.model.TransactionType;
import com.blizuk.financetracker.service.TransactionService;

import java.util.List;
import java.util.Scanner;

public class ConsoleController {
    private final Scanner scanner = new Scanner(System.in);
    private final TransactionService service = new TransactionService();

    public void start()
    {
        while (true)
        {
            System.out.println("1. Добавление транзакции");
            System.out.println("2. Удаление транзакции");
            System.out.println("3. Просмотр всех транзакций");
            System.out.println("4. Выход");

            int choice = scanner.nextInt();

            switch (choice)
            {
                case 1  -> addTransaction();
                case 2  -> deleteTransaction();
                case 3  -> showAllTransactions();
                case 4  -> System.exit(0);
            }
        }
    }

    private  void addTransaction()
    {
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();

        System.out.print("Type (1-INCOME, 2-EXPENSE): ");
        int typeInput = scanner.nextInt();

        TransactionType type = (typeInput == 1)
                ? TransactionType.INCOME
                : TransactionType.EXPENSE;

        service.addTransaction(amount, type, 1L, "test");
    }


    private void showAllTransactions()
    {
        List<Transaction> listTransactions = service.getAllTransaction();

        for (Transaction tx : listTransactions)
        {
            System.out.println(tx);
        }
    }


    private void deleteTransaction()
    {
        showAllTransactions();
        System.out.println("Выберите операцию: ");
        int choice = scanner.nextInt();
        service.deleteTransaction(choice);
    }
}

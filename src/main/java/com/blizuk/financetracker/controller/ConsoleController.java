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
            System.out.println("2. Удаление транзакции"); //Удалить
            System.out.println("3. Просмотр всех транзакций");//Собрать меню
            System.out.println("4. Выход");

            int choice = scanner.nextInt();

            switch (choice)
            {
                case 1  -> addTransaction();
                case 2  -> deleteTransaction(); // Удалить
                case 3  -> showAllTransactions(); // Собрать меню
                case 4  -> System.exit(0);
            }
        }
    }


    private  void addTransaction()
    {
        System.out.print("Сумма: ");
        double amount = scanner.nextDouble();

        System.out.print("Type (1-INCOME, 2-EXPENSE): ");
        int typeInput = scanner.nextInt();

        TransactionType type = (typeInput == 1)
                ? TransactionType.INCOME
                : TransactionType.EXPENSE;

        // обработать полный ввод
        service.addTransaction(amount, type, 1L, "test");
    }


    private void showAllTransactions()
    {
/* 
Расширить метод: добавить целое меню для адекватного просмотра
операций, а также возможность длч удаленич транзакций засунуть сюда. 
*/
        List<Transaction> listTransactions = service.getAllTransaction();

        for (Transaction tx : listTransactions)
        {
            System.out.println(tx);
        }
    }

// Удалить/перенести в просмотр всех
    private void deleteTransaction()
    {
        showAllTransactions();
        System.out.println("Выберите операцию: ");
        int choice = scanner.nextInt();
        service.deleteTransaction(choice);
    }
}

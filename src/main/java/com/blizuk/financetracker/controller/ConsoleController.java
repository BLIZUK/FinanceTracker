package com.blizuk.financetracker.controller;


import com.blizuk.financetracker.model.Transaction;
import com.blizuk.financetracker.model.TransactionType;
import com.blizuk.financetracker.service.LogService;
import com.blizuk.financetracker.service.TransactionService;


import java.util.List;
import java.util.Scanner;


public class ConsoleController {
    private final Scanner scanner = new Scanner(System.in);
    private final TransactionService service = new TransactionService();
    private final LogService log;


    public ConsoleController (LogService log)
    {
        this.log = log;
    }


    public void start()
    {
        main_menu();
    }


    private void main_menu()
    {
        String name = "main_menu";
        while (true)
        {
            log.showRun(name);
            System.out.println("1. Добавление транзакции");
            System.out.println("2. Просмотр всех транзакций"); //Собрать меню
            System.out.println("3. Выход");

            int choice = scanner.nextInt();

            switch (choice)
            {
                case 1 -> addTransaction();
                case 2 -> editTransactions_menu();
                case 3 -> { System.out.println("Выход..."); return; }
            }
        }
    }


    private void editTransactions_menu()
    {
        boolean flag = true;
        String name = "editTransactions_menu";
        while (flag)
        {
            log.showRun(name);
            System.out.println("1. Просмотр всех транзакций");
            System.out.println("2. Удаление транзакции");
            System.out.println("3. Выход");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> showAllTransactions();
                case 2 -> deleteTransaction();
                default -> { return; }
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

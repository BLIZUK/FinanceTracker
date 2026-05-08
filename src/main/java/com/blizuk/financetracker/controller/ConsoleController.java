package com.blizuk.financetracker.controller;


import com.blizuk.financetracker.model.Transaction;
import com.blizuk.financetracker.model.TransactionType;
import com.blizuk.financetracker.service.AuthenticationService;
import com.blizuk.financetracker.service.LogService;
import com.blizuk.financetracker.service.TransactionService;


import java.util.List;
import java.util.Scanner;


public class ConsoleController {
    private final Scanner scanner = new Scanner(System.in);
    private final TransactionService ts = new TransactionService();
    private final AuthenticationService as = new AuthenticationService();
    private final LogService log;


    public ConsoleController(LogService log) {
        this.log = log;
    }

    public void start() {
        login();
    }

    private void login()
    {
        System.out.println("Введите ваш Login: ");
        String login = scanner.nextLine();
        if (!as.authenticateUser(login)) System.out.println("Не существует такого пользователя: " + login);
        else{
            System.out.println("Добро пожаловать, " + login);
        }
    }

    private void main_menu() {
        while (true) {
            log.showRun("main_menu");
            System.out.println("1. Добавление транзакции");
            System.out.println("2. Просмотр всех транзакций"); //Собрать меню
            System.out.println("3. Выход");

            int choice = readInt();

            switch (choice) {
                case 1 -> addTransaction();
                case 2 -> editTransactions_menu();
                case 3 -> {
                    System.out.println("Выход...");
                    return;
                }
            }
        }
    }

    private void editTransactions_menu() {
        while (true) {
            log.showRun("editTransactions_menu");
            System.out.println("1. Просмотр всех транзакций");
            System.out.println("2. Удаление транзакции");
            System.out.println("3. Выход");

            int choice = readInt();

            switch (choice) {
                case 1 -> showAllTransactions();
                case 2 -> deleteTransaction();
                default -> {
                    return;
                }
            }
        }
    }

    private void addTransaction() {
        System.out.print("Сумма: ");
        double amount = scanner.nextDouble();

        System.out.print("Type (1-INCOME, 2-EXPENSE): ");
        int typeInput = scanner.nextInt();

        TransactionType type = (typeInput == 1)
                ? TransactionType.INCOME
                : TransactionType.EXPENSE;

        // обработать полный ввод
        ts.addTransaction(amount, type, 1L, "test");
    }

    // Нужно сделать таблицу выводом
    private void showAllTransactions() {
        List<Transaction> listTransactions = ts.getAllTransaction();

        for (Transaction tx : listTransactions) {
            System.out.println(tx);
        }
    }

    // Удалить/перенести в просмотр всех
    private void deleteTransaction() {
        showAllTransactions();
        System.out.println("Выберите операцию: ");
        int choice = scanner.nextInt();
        ts.deleteTransaction(choice);
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Введите число!");
            }
        }
    }
}
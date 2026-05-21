package com.blizuk.financetracker.view;


import com.blizuk.financetracker.model.Transaction;
import com.blizuk.financetracker.model.TransactionType;
import com.blizuk.financetracker.util.ColorUtil;
import com.blizuk.financetracker.view.dto.AuthInputData;
import com.blizuk.financetracker.view.dto.TransactionInputData;

import java.time.LocalDateTime;
import java.util.List;


public class ConsoleView {
    ColorUtil color;
    ConsoleInput input;

    public ConsoleView(ConsoleInput ci, ColorUtil cu) {
        this.input = ci;
        this.color = cu;
    }

    // Методы для аутентификации ------>
    public void showAuthMenu() {
        clearConsole();
        System.out.println("\n=== МЕНЮ АУТЕНТИФИКАЦИИ ===");
        System.out.println("1. Вход");
        System.out.println("2. Регистрация");
        System.out.println("3. Выход");
        System.out.print("Выберите действие: ");
    }

    // Форма ввода данных (только чтение)
    public AuthInputData showAuthForm(String title) {
        clearConsole();
        System.out.println("\n--- " + title + " ---");
        System.out.print("Введите логин: ");
        String login = input.String();
        System.out.print("Введите пароль: ");
        String password = input.String();
        return new AuthInputData(login, password); // dto
    }
    // <------ Методы для аутентификации


    /*
    protected void showTitle (String title)
        System.out.println("\n====  Fintrack   ====\n");
        System.out.println("\n~~~ " + title + " ~~~\n\n");

        System.out.println("1. Общий Баланс: " + balance);
        System.out.println("2. Вы потратили в  " + month + ": " + money + "Р");
        System.out.println("3. Недавние траты:");
         */


    // Методы для главного меню ------>
    public void showMainMenu() {
        clearConsole();
        System.out.println("\n====  ГЛАВНОЕ МЕНЮ  ====");
        System.out.println("1. Добавление транзакции");
        System.out.println("2. Просмотр всех транзакций");
        System.out.println("3. Выход из аккаунта");
        System.out.println("4. Выход из программы");
        System.out.print("Выберите действие: ");
    }

    public TransactionInputData showTransactionForm(Long uId, LocalDateTime time) {
        System.out.println("\n--- Добавление новой транзакции ---");
        System.out.print("Введите сумму: ");
        double amount = input.Double();
        System.out.println("1. Доход");
        System.out.println("2. Расход");
        System.out.print("Выберите тип: ");
        int typeChoice = input.Int();
        TransactionType type = (typeChoice == 1) ? TransactionType.INCOME : TransactionType.EXPENSE;
        System.out.print("Введите номер категории: ");
        Long categoryId = input.Long();
        System.out.print("Введите описание: ");
        String description = input.String();
        return new TransactionInputData(uId, amount, type, categoryId, description, time); //dto
    }


    public void showTransactionsTable(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("Список транзакций пуст.");
            return;
        }
        System.out.println("\n----------------------------------------------------------");
        System.out.printf("%-5s | %-10s | %-10s | %-10s | %-15s%n", "ID", "UserId", "Сумма", "Тип", "Описание");
        System.out.println("----------------------------------------------------------");
        for (Transaction tx : transactions) {
            // Предполагается наличие геттеров в модели Transaction
            System.out.printf("%-5d | %-10d | %-10.2f | %-10s | %-15s%n",
                    tx.getId(), tx.getUserId(), tx.getAmount(), tx.getType(), tx.getDescription());
        }
        System.out.println("----------------------------------------------------------");

    }
    // <------ Методы для главного меню


    // Прикладные методы
    public void showMessage(String s) {
        System.out.println(s);
    }

    public void clearConsole() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                // Конструкция для очистки консоли Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Конструкция для Linux и macOS
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // Если системная очистка не сработала, используем ANSI
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    public void waitForEnter() {
        System.out.println("\nНажмите Enter, чтобы продолжить...");
        // Используем готовый Scanner из вашего проекта
        try {
            // Очистка буфера, если там что-то осталось, и ожидание ввода
            new java.util.Scanner(System.in).nextLine();
        } catch (Exception e) {
            // Игнорируем ошибки ввода
        }
    }
}

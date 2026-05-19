package com.blizuk.financetracker.view;

import com.blizuk.financetracker.model.Transaction;
import com.blizuk.financetracker.model.TransactionType;
import com.blizuk.financetracker.model.User;
import com.blizuk.financetracker.util.ColorUtil;
import com.blizuk.financetracker.view.dto.AuthInputData;
import com.blizuk.financetracker.view.dto.TransactionInputData;

import java.util.List;

public class ConsoleView {
    ColorUtil color;
    ConsoleInput input;

    public ConsoleView(ConsoleInput ci, ColorUtil cu) {
        this.input = ci;
        this.color = cu;
    }

    public void showAuthMenu() {

        System.out.println("\n=== МЕНЮ РЕГИСТРАЦИИ ===");
        System.out.println("1. Вход");
        System.out.println("2. Регистрация");
        System.out.println("3. Выход");
        System.out.print("Выберите действие: ");
    }

    public void showUserNotFound(String login) {
        System.out.println("Не существует такого пользователя: " + login);
        System.out.println("Перейти к регистрации?:\n1. Да\n2. Нет");
    }

    public void showMainMenu() {
        System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
        System.out.println("1. Добавление транзакции");
        System.out.println("2. Просмотр всех транзакций");
        System.out.println("3. Выход");
        System.out.print("Выберите действие: ");
    }

    public void showTransactionsTable(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("Список транзакций пуст.");
            return;
        }
        System.out.println("\n------------------------------------------------");
        System.out.printf("%-5s | %-10s | %-10s | %-15s%n", "ID", "Сумма", "Тип", "Описание");
        System.out.println("------------------------------------------------");
        for (Transaction tx : transactions) {
            // Предполагается наличие геттеров в модели Transaction
            System.out.printf("%-5d | %-10.2f | %-10s | %-15s%n",
                    tx.getId(), tx.getAmount(), tx.getType(), tx.getDescription());
        }
        System.out.println("------------------------------------------------");
    }

    public TransactionInputData showTransactionForm() {
        System.out.println("\n--- Добавление новой транзакции ---");

        System.out.print("Введите сумму: ");
        double amount = input.Double();

        System.out.print("Выберите тип (1 - ДОХОД, 2 - РАСХОД): ");
        int typeChoice = input.Int();
        TransactionType type = (typeChoice == 1) ? TransactionType.INCOME : TransactionType.EXPENSE;

        System.out.print("Введите описание: ");
        String description = input.String();

        return new TransactionInputData(amount, type, description);
    }

    // Форма ввода данных (только чтение)
    public AuthInputData showAuthForm(String title) {
        System.out.println("\n--- " + title + " ---");
        System.out.print("Введите логин: ");
        String login = input.String();
        System.out.print("Введите пароль: ");
        String password = input.String();
        return new AuthInputData(login, password);
    }

    public void showMessage(String s) {
        System.out.println(s);
    }
}

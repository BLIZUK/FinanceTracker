package com.blizuk.financetracker.controller;


import com.blizuk.financetracker.model.User;
import com.blizuk.financetracker.model.UserRole;
import com.blizuk.financetracker.service.AuthenticationService;
import com.blizuk.financetracker.service.TransactionService;
import com.blizuk.financetracker.view.ConsoleInput;
import com.blizuk.financetracker.view.ConsoleView;
import com.blizuk.financetracker.view.dto.AuthInputData;
import com.blizuk.financetracker.view.dto.TransactionInputData;


public class ConsoleController {
    private final ConsoleInput input;
    private final ConsoleView view;
    private final TransactionService ts;
    private final AuthenticationService as;
    private User currentUser = null;

    // Конструктор
    public ConsoleController(ConsoleInput ci, ConsoleView cv, TransactionService ts, AuthenticationService as) {
        this.input = ci;
        this.view = cv;
        this.ts = ts;
        this.as = as;
    }

    // Инициализация
    public void run() {
        while (true)
        {
            // Запуск главного меню
            if (authMenuLoop())
            {
                mainMenuLoop();
            }
            // Выход из программы
            else
            {
                view.clearConsole();
                view.showMessage("Выход из программы.");
                view.waitForEnter();
                break;
            }
        }
    }


    // Логика для меню ------------>
    private boolean authMenuLoop() {
        while (currentUser == null)
        {
            view.showAuthMenu();
            int choice = input.Int();
            switch (choice)
            {
                case 1 -> handleLogin();
                case 2 -> handleRegistration();
                case 3 -> { return false; }
            }
        }
        return true;
    }

    private void mainMenuLoop() {
        while (currentUser != null)
        {
            view.showMainMenu();
            int choice = input.Int();

            switch (choice) {
                case 1 -> addTransaction();
                case 2 -> view.showTransactionsTable(ts.getAllTransaction());
                // Выход из учетки
                case 3 -> {
                    // Смена пользователя: сбрасываем данные и выходим из этого меню
                    this.currentUser = null;
                    view.clearConsole();
                    view.showMessage("Вы успешно вышли из учетной записи.");
                    view.waitForEnter();
                    return;
                }
                //Выход из программы
                case 4 -> {
                    view.clearConsole();
                    // Полный выход: завершаем работу всего приложения
                    view.showMessage("Выход из программы.");
                    System.exit(0);
                }
                // Ошибочный ввод
                default -> {
                    view.clearConsole();
                    view.showMessage("Неверный пункт меню.");
                    view.waitForEnter();
                }
            }
        }
    }
// <------------ Логика для меню


    // ==== Прикладные методы ====


    // Методы для аутентификации ------>
    private void handleLogin() {
        AuthInputData credentials = view.showAuthForm("Авторизация");

        User user = as.authenticateUser(credentials.login(), credentials.password());

        view.clearConsole();
        // Успешная авторизация
        if (user != null)
        {
            this.currentUser = user;

            view.showMessage("Добро пожаловать, " + user.getUserName() + "!");
        }
        // Ошибочный ввод
        else
        {
            view.showMessage("Ошибка! Неверный логин или пароль.");
        }
        view.waitForEnter();
    }

    private void handleRegistration() {
        AuthInputData credentials = view.showAuthForm("Регистрация нового пользователя");

        boolean success = as.addUser(credentials.login(), credentials.password(), UserRole.USER);

        view.clearConsole();
        if (success) {
            view.showMessage("Успешная регистрация! Теперь вы можете войти.");
        } else {
            view.showMessage("Ошибка! Такой логин уже существует.");
        }
        view.waitForEnter();
    }
    // <------ Методы для аутентификации

    // Методы для главного меню ------>
    private void addTransaction() {
        Long currentUserId = 1L;
        // 1. Запрашиваем данные у пользователя через View
        TransactionInputData inputData = view.showTransactionForm();

        // 2. Передаем данные в сервис (передаем id текущего авторизованного юзера)
        TransactionService.addTransaction(inputData, currentUserId);

        // 3. Сообщаем об успехе
        // view.showSuccessMessage("Транзакция успешно добавлена!" );
    }
    // <------ Методы для главного меню
}
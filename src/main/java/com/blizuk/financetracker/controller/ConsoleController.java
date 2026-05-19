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

    public ConsoleController(ConsoleInput ci, ConsoleView cv, TransactionService ts, AuthenticationService as) {
        this.input = ci;
        this.view = cv;
        this.ts = ts;
        this.as = as;
    }

    public void start()
    {
        if (authMenuLoop())
        {
            return;
        } else {
            mainMenuLoop();
        }
    }

    private boolean authMenuLoop()
    {
        while (currentUser == null) {
            view.showAuthMenu(); // 1. Войти, 2. Зарегистрироваться, 3. Выход
            int choice = input.Int();
            switch (choice) {
                case 1 -> handleLogin();
                case 2 -> handleRegistration();
                case 3 -> { return true; }
            }
        }
        return false;
    }

    private void mainMenuLoop() {
        while (true) {
            view.showMainMenu();
            int choice = input.Int();
            switch (choice)
            {
                case 1 -> addTransaction();
                case 2 -> view.showTransactionsTable(ts.getAllTransaction());
                case 3 -> {
                    System.out.println("Выход из программы.");
                    return;
                }
                default -> System.out.println("Неверный пункт меню.");
            }

        }
    }

    private void addTransaction() {
        Long currentUserId = 1L;
        // 1. Запрашиваем данные у пользователя через View
        TransactionInputData inputData = view.showTransactionForm();

        // 2. Передаем данные в сервис (передаем id текущего авторизованного юзера)
        TransactionService.addTransaction(inputData, currentUserId);

        // 3. Сообщаем об успехе
       // view.showSuccessMessage("Транзакция успешно добавлена!" );
    }

    private void handleLogin() {
        // 1. View собирает данные с консоли
        AuthInputData credentials = view.showAuthForm("Авторизация");

        // 2. Service проверяет логику
        User user = AuthenticationService.login(credentials.login(), credentials.password());

        // 3. Controller принимает решение на основе ответа сервиса
        if (user != null) {
            this.currentUser = user;
            view.showMessage("Добро пожаловать, " + user.getUserName() + "!");
        } else {
            view.showMessage("Ошибка! Неверный логин или пароль.");
        }
    }

    private void handleRegistration() {
        // 1. View собирает данные с консоли
        AuthInputData credentials = view.showAuthForm("Регистрация нового пользователя");

        // 2. Service проверяет логику
        boolean success = as.addUser(credentials.login(), credentials.password(), UserRole.USER);

        // 3. View показывает результат
        if (success) {
            view.showMessage("Регистрация успешна! Теперь вы можете войти.");
        } else {
            view.showMessage("Ошибка! Такой логин уже существует.");
        }
    }
}
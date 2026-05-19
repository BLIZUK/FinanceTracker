package com.blizuk.financetracker;


import com.blizuk.financetracker.controller.ConsoleController;
import com.blizuk.financetracker.service.AuthenticationService;
import com.blizuk.financetracker.service.TransactionService;
import com.blizuk.financetracker.util.ColorUtil;
import com.blizuk.financetracker.util.InputUtil;
import com.blizuk.financetracker.util.LoggerUtil;
import com.blizuk.financetracker.view.ConsoleInput;
import com.blizuk.financetracker.view.ConsoleView;
import java.io.IOException;


public class Main {
    static void main(String[] args) throws IOException {
        try {
            LoggerUtil logger_util = new LoggerUtil();
            InputUtil input_util = new InputUtil();
            ColorUtil color_util = new ColorUtil();

            TransactionService transaction_service = new TransactionService();
            AuthenticationService authentication_service = new AuthenticationService();
            ConsoleInput console_Input = new ConsoleInput(input_util);
            ConsoleView console_view = new ConsoleView(console_Input, color_util);

            ConsoleController app = new ConsoleController(console_Input, console_view, transaction_service, authentication_service);

            logger_util.showRun("app");
            app.run();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Press Enter to exit...");
            new java.util.Scanner(System.in).nextLine();
        }
    }
}

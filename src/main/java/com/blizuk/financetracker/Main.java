package com.blizuk.financetracker;


import com.blizuk.financetracker.controller.ConsoleController;
import com.blizuk.financetracker.service.*;
import com.blizuk.financetracker.util.*;
import com.blizuk.financetracker.view.*;
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

package com.blizuk.financetracker;

import com.blizuk.financetracker.controller.ConsoleController;
import com.blizuk.financetracker.service.AuthenticationService;
import com.blizuk.financetracker.util.InputUtil;
import com.blizuk.financetracker.util.LoggerUtil;
import com.blizuk.financetracker.service.TransactionService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException
    {
        try {
            LoggerUtil logger_util = new LoggerUtil();
            TransactionService transaction_service = new TransactionService();
            AuthenticationService authentication_service = new AuthenticationService();
            InputUtil input_util = new InputUtil();
            logger_util.showRun("app");

            ConsoleController app = new ConsoleController(logger_util, input_util, transaction_service, authentication_service);
            app.start();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Press Enter to exit...");
            new java.util.Scanner(System.in).nextLine();
        }
    }
}
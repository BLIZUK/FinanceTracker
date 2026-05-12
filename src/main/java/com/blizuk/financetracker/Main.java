package com.blizuk.financetracker;

import com.blizuk.financetracker.controller.ConsoleController;
import com.blizuk.financetracker.service.AuthenticationService;
import com.blizuk.financetracker.util.LoggerUtil;
import com.blizuk.financetracker.service.TransactionService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException
    {
        try {
            LoggerUtil log = new LoggerUtil();
            TransactionService ts = new TransactionService();
            AuthenticationService as = new AuthenticationService();
            log.showRun("app");

            ConsoleController app = new ConsoleController(log, ts, as);
            app.start();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Press Enter to exit...");
            new java.util.Scanner(System.in).nextLine();
        }
    }
}
package com.blizuk.financetracker;

import com.blizuk.financetracker.controller.ConsoleController;
import com.blizuk.financetracker.service.LogService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException
    {
        try {
            LogService log = new LogService();
            log.showRun("app");

            ConsoleController app = new ConsoleController(log);
            app.start();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Press Enter to exit...");
            new java.util.Scanner(System.in).nextLine();
        }
    }
}
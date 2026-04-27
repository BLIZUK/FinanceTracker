package com.blizuk.financetracker;

import com.blizuk.financetracker.controller.ConsoleController;
import java.io.IOException;

public class Main {
    static void main(String[] args) throws IOException
    {
        System.out.println("#----------------------> App start!");
        ConsoleController App = new ConsoleController();
        App.start();
    }
}

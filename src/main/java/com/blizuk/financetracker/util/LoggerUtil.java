package com.blizuk.financetracker.util;

public class LoggerUtil
{
    public void showRun(String name)
    {
        System.out.print("\033[H\033[J");
        System.out.println("#----------------------> " + name + " start!\n");
    }

}

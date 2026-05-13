package com.blizuk.financetracker.util;

public class InputUtil {
    private static final String RESET = "\u001B[0m";
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";


    public String reset(){
        return RESET;
    }

    public String black(){
        return BLACK;
    }

    public String green(){
        return GREEN;
    }

    public String cyan(){
        return CYAN;
    }
}

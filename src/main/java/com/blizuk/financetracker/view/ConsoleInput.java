package com.blizuk.financetracker.view;


import com.blizuk.financetracker.util.InputUtil;


public class ConsoleInput {
    private final InputUtil iu;

    public ConsoleInput(InputUtil iu) {
        this.iu = iu;
    }

    public int Int() {
        return iu.readInt();
    }

    public double Double() {
        return iu.readDouble();
    }

    public String String()
    {
        return iu.readString();
    }
}

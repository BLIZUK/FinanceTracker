package com.blizuk.financetracker.util;


import java.util.Scanner;


public class InputUtil
{
    private final Scanner scanner = new Scanner(System.in);

    public String readString()
    {
        return scanner.nextLine().trim();
    }

    public int readInt() {
        while (true) {
            try {
                return Integer.parseInt(readString());
            } catch (NumberFormatException e) {
                System.out.println("\nОшибка! ");
            }
        }
    }

    public double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(readString());
            } catch (NumberFormatException e) {
                System.out.println("\nОшибка! ");
            }
        }
    }

    public Long  readLong() {
        while (true) {
            try {
                return Long.parseLong(readString());
            } catch (NumberFormatException e) {
                System.out.println("\nОшибка! ");
            }
        }
    }
}

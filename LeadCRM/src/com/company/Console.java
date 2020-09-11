package com.company;

import java.util.Scanner;

public class Console {
    private static Console instance = null;
    private Scanner scanner = new Scanner(System.in);
    private Console() {}

    public static Console getInstance() {
        if (instance == null) {
            instance = new Console();
        }
        return instance;
    }

    public String stringIN(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int intIn(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }



}

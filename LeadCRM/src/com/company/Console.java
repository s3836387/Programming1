package com.company;

import com.company.myPackage.Lead;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Console {

    private static Scanner scanner = new Scanner(System.in);
    private Console() {}


    public static String charIn(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

    public static int intIn(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    //check if the input is integer
    public static int validateInt(String prompt) {
        System.out.print(prompt);
        int input;
        while (!scanner.hasNextInt()) {
            System.out.print(prompt);
            scanner.next();
        }
        input = scanner.nextInt();
        return input;
    }

    //check if the input is integer and is in range
    public static int validateInt(String prompt, int min, int max) {
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print(prompt);
                scanner.next();
            }
            input = scanner.nextInt();
        } while (!((input >= min) && (input <= max)));
        return input;
    }

    //check if the id is existed
    public static int validateID(List<Lead> leadList) {
        int id = validateInt("Type in the ID (number only): ");
        while (true) {
            for (Lead lead : leadList) {
                if (lead.getId() == id) {
                    return id;
                }
            }
            id = validateInt("The id is not existed, type another: ");
        }
    }


    //validate name
    public static String validateName(String prompt) {
        String result = scanner.nextLine();
        while (!result.matches("^[a-zA-Z\\s]+")) {
            System.out.print(prompt);
            result = scanner.nextLine();
        }
        return result;
    }
    //validate input date
    public static String validateDate(String prompt) {
        String result = null;
        Boolean isValid = false;
        while (isValid == false) {
            try {
                System.out.print(prompt);
                result = scanner.next();
                LocalDate.parse(result);
                isValid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Wrong date format!");
                isValid = false;
            }
        }

        return result;

    }

    //check if input match true or false
    public static String validateGender(String prompt) {
        System.out.print(prompt);
        String result = scanner.next();
        while (!(result.equals("true") || result.equals("false"))) {
            System.out.print(prompt);
            result = scanner.next();
        }
        return result;
    }

    public static String validateEmail(String prompt) {
        System.out.print(prompt);
        String result = scanner.next();
        while (!result.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            System.out.print("Invalid format, type again: ");
            result = scanner.next();
        }
        return result;
    }

    public static String validatePhone(String prompt) {
        System.out.print(prompt);
        String result = scanner.next();
        while (!result.matches("^\\d{10}$")) {
            System.out.print("Invalid format, try again: ");
            result = scanner.next();
        }
        return result;
    }



}

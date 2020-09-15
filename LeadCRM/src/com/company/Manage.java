package com.company;

import com.company.myPackage.I_FileProcessor;
import com.company.myPackage.Lead;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manage {

    private static String filePath;

    private static I_FileProcessor fileProcessor;


    public static I_FileProcessor file() {
        return fileProcessor;
    }

    private Manage() {
    }

    //Setters
    public static void setFilePath(String path) {
        fileProcessor.setFilePath(path);
    }
    public static void setFP(FileProcessor fp) {
        fileProcessor = fp;
    }


    //ask user to type detail of new lead
    public static Lead addNewLead() {
        String stringBDate;
        Lead lead = new Lead();
        lead.setName(Console.validateName("Name: "));
        stringBDate = Console.validateDate("Birthday (YYYY-MM-DD): ");
        lead.setStringBDate(stringBDate);
        lead.setBirthDate(lead.stringToDate(stringBDate));
        lead.setGender(Boolean.parseBoolean(Console.charIn("Gender (true/false): ")));
        lead.setPhoneNumber(Console.charIn("Phone number: "));
        lead.setEmail(Console.charIn("Email: "));
        lead.setAddress(Console.charIn("Address: "));
        return lead;
    }

    //return index of a lead in lead array list
    public static int chooseLeadByID(List<Lead> arrayOfLead, int id) {
        int index = 0;
        for (Lead single : arrayOfLead) {
            if (single.getId() == id) {
                System.out.println("You have chosen: " + formatLeadID(single.getId()));
                index = arrayOfLead.indexOf(single);
            }
        }
        return index;

    }

    //update a lead in Lead array-list
    public static void updateLead(List<Lead> leadList, int index) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------");
        System.out.println("1.Name");
        System.out.println("2.Birthday");
        System.out.println("3.Gender");
        System.out.println("4.Phone");
        System.out.println("5.Email");
        System.out.println("6.Address");
        System.out.println("----------------");
        int field = Console.validateInt("Type in one of the number represent the field you want to modify: ",1,6);
        String temp;
        switch (field) {
            case 1 -> {
                String name = Console.validateName("Update name: ");
                leadList.get(index).setName(name);
            }
            case 2 -> {
                temp = Console.validateDate("Update birthday: ");
                leadList.get(index).setStringBDate(temp);
                leadList.get(index).setBirthDate(LocalDate.parse(temp));
            }
            case 3 -> {
                temp = Console.validateGender("Update gender (true/false): ");
                leadList.get(index).setGender(Boolean.parseBoolean(temp));
            }
            case 4 -> {

                temp = Console.validatePhone("Update phone: ");
                leadList.get(index).setPhoneNumber(temp);
            }
            case 5 -> {
                temp = Console.validateEmail("Update email: ");
                leadList.get(index).setEmail(temp);
            }
            case 6 -> {
                System.out.print("Update address: ");
                 temp = scanner.nextLine();
                leadList.get(index).setAddress(temp);
            }

        }

    }




    //Convert 2-dimensional String array-list into Lead array-list
    public static List<Lead> dataToLeads(List<List<String>> data) {
        List<Lead> tempArr = new ArrayList<>();
        int counter = 0;
        for (List<String> row : data) {
            tempArr.add(new Lead());
            tempArr.get(counter).setId(extractID(row.get(0)));
            tempArr.get(counter).setName(row.get(1));
            tempArr.get(counter).setStringBDate(row.get(2));
            tempArr.get(counter).setBirthDate(tempArr.get(counter).stringToDate(row.get(2)));
            tempArr.get(counter).setGender(Boolean.parseBoolean(row.get(3)));
            tempArr.get(counter).setPhoneNumber(row.get(4));
            tempArr.get(counter).setEmail(row.get(5));
            tempArr.get(counter).setAddress(row.get(6));

            counter++;
        }
        return tempArr;
    }


    //Convert Lead array-list into 2-dimensional string array-list
    public static List<List<String>> leadsListToString(List<Lead> leads) {
        List<List<String>> tempArr = new ArrayList<>();
        for (Lead single : leads) {
            List<String> temp = new ArrayList<>();
            temp.add(formatLeadID(single.getId()));
            temp.add(single.getName());
            temp.add(single.getStringBDate());
            temp.add(String.valueOf(single.isGender()));
            temp.add(single.getPhoneNumber());
            temp.add(single.getEmail());
            temp.add(single.getAddress());
            tempArr.add(temp);
        }

        return tempArr;
    }

    public static int extractID(String id) {
        int tempID;
        id = id.substring(id.lastIndexOf("_") + 1);
        tempID = Integer.parseInt(id);
        return tempID;
    }

    public static String formatLeadID(int id) {
        return "lead_" + String.format("%03d", id);
    }








}

package com.company;

import com.company.myPackage.Lead;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manage {

    private static String filePath;

    private static I_FileProcessor fileProcessor;

    private static List<Lead> leads;


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

    //Getters
    public static List<Lead> getLeads() {
        return leads;
    }

    public static void readLeadFile() throws IOException {
        List<List<String>> stringData = fileProcessor.readFile();
        leads = dataToLeads(stringData);
    }


    //ask user to type detail of new lead and write to the file
    public static void addNewLead() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String stringBDate;
        Lead lead = new Lead();
        lead.setName(Console.validateName("Name: "));
        stringBDate = Console.validateDate("Birthday (YYYY-MM-DD): ");
        lead.setStringBDate(stringBDate);
        lead.setBirthDate(lead.stringToDate(stringBDate));
        lead.setGender(Boolean.parseBoolean(Console.charIn("Gender (true/false): ")));
        lead.setPhoneNumber(Console.charIn("Phone number: "));
        lead.setEmail(Console.charIn("Email: "));
        System.out.print("Address: ");
        lead.setAddress(scanner.nextLine());
        fileProcessor.writeNewLead(lead.leadToString());

    }
    //return index of a lead in lead array list
    public static int chooseLeadByID() {
        int index = validateID();
        for (Lead single : leads) {
            if (single.getId() == index) {
                System.out.println("You have chosen: " + formatLeadID(single.getId()));
                index = leads.indexOf(single);
            }
        }
        return index;

    }

    //update a lead in Lead array-list and update the file
    public static void updateLead( int index) throws IOException {
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
                leads.get(index).setName(name);
            }
            case 2 -> {
                temp = Console.validateDate("Update birthday: ");
                leads.get(index).setStringBDate(temp);
                leads.get(index).setBirthDate(LocalDate.parse(temp));
            }
            case 3 -> {
                temp = Console.validateGender("Update gender (true/false): ");
                leads.get(index).setGender(Boolean.parseBoolean(temp));
            }
            case 4 -> {

                temp = Console.validatePhone("Update phone: ");
                leads.get(index).setPhoneNumber(temp);
            }
            case 5 -> {
                temp = Console.validateEmail("Update email: ");
                leads.get(index).setEmail(temp);
            }
            case 6 -> {
                System.out.print("Update address: ");
                 temp = scanner.nextLine();
                leads.get(index).setAddress(temp);
            }

        }
        fileProcessor.updateFile(leadsListToString(leads));

    }

    //delete lead and write to file
    public static void removeLead(int index) throws IOException {
        leads.remove(index);
        fileProcessor.updateFile(leadsListToString(leads));
    }

    public static void showLeadRecords() throws IOException {
        fileProcessor.showRecords();
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

    public static int validateID() {
        int result = Console.validateID(leads);
        return result;
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

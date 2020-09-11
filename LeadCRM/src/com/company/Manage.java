package com.company;

import com.company.myPackage.Lead;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manage {

    private static String filePath;

    private static I_FileProcessor fileProcessor;

    public static void setFilePath(String path) {
       fileProcessor.setFilePath(path);
    }

    public static I_FileProcessor file() {
        return fileProcessor;
    }

    private Manage() {
    }

    //ask user to type detail of new lead
    public static Lead addNewLead() {
        Lead lead = new Lead();
        lead.setName(Console.getInstance().stringIN("Name: "));
        lead.setEmail(Console.getInstance().stringIN("Email: "));
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
        System.out.println("1.Name 2.Email");
        int field = Console.getInstance().intIn("choose field to update: ");
        String temp;
        switch (field) {
            case 1 -> {
                temp = scanner.nextLine();
                leadList.get(index).setName(temp);
            }
            case 2 -> {
                temp = scanner.nextLine();
                leadList.get(index).setEmail(temp);
            }
            default -> System.out.println("Invalid field");
        }

    }


    public static void setFP(FileProcessor fp) {
        fileProcessor = fp;
    }


    //Convert 2-dimensional String array-list into Lead array-list
    public static List<Lead> dataToLeads(List<List<String>> data) {
        List<Lead> tempArr = new ArrayList<>();
        int counter = 0;
        for (List<String> row : data) {
            tempArr.add(new Lead());
            tempArr.get(counter).setId(extractID(row.get(0)));
            tempArr.get(counter).setName(row.get(1));
            tempArr.get(counter).setEmail(row.get(2));
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
            temp.add(single.getEmail());
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

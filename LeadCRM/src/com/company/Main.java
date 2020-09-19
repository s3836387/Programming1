package com.company;

import com.company.myPackage.*;


import java.time.LocalDate;
import java.io.IOException;
import java.util.List;


public class Main {
    public static void leadManagement() throws IOException {
        //Validate csv file
        boolean cont = true;
        String leadFile = "src/com/company/leads.csv";
        if (Manage.initFile()) {
            System.out.println("No lead.csv file found, new file created!");
        }

        //Start the menu
        while (cont) {


            //Initializing
            FileProcessor fp = new FileProcessor();
            Manage.setFP(fp);
            Manage.setFilePath(leadFile);

            //Show all the lead records
            Manage.showLeadRecords();

            //Start lead management
            System.out.println("----------------Lead Main Menu----------------");
            System.out.println("-----------------");
            System.out.println("1.Create new lead");
            System.out.println("2.Modify an existing lead");
            System.out.println("3.Show report based on age");
            System.out.println("4.Back to main menu");
            System.out.println("----------------------------");


            int choice2 = Console.validateInt("Selection (number): ", 1, 4);
            switch (choice2) {
                case 1 -> {
                    Manage.addNewLead();
                    System.out.println("Added new lead!");
                }
                case 2 -> {
                    //read lead file and store data, check if the id user enter is valid.
                    Manage.readLeadFile();
                    int indexOfLead = Manage.chooseLeadByID();
                    int choice = Console.validateInt("1.UPDATE 2.DELETE 3.CANCEL: ", 1, 3);
                    if (choice == 1) {
                        boolean loop = true;
                        while (loop) {
                            loop = Manage.updateLead(indexOfLead);
                        }
                    } else if (choice == 2) {
                        //access interaction file to check if the is any related inters
                        InteractionManagement manageInters = new InteractionManagement();
                        InteractionManagement.initFile();

                        System.out.println("Interaction related to this lead:");
                        List<Integer> relateIntersID = Manage.showRelatedInteractionID(manageInters.getAll(), Manage.getLeads().get(indexOfLead).getId());
                        for (int id : relateIntersID) {
                            System.out.println("inter_" + String.format("%03d", id));
                        }
                        System.out.println("Process to delete chosen lead will also delete any related interactions, continue?");
                        System.out.println("------------------");
                        choice = Console.validateInt("1.Yes   2.Cancel: ", 1, 2);
                        if (choice == 1) {
                            Manage.removeLead(indexOfLead);
                            for (int interID : relateIntersID) {
                                System.out.println("Deleted inter_" + String.format("%03d", interID));
                                Interaction temp = (Interaction) manageInters.getObject(interID);
                                manageInters.deleteObject(temp);
                            }
                        } else if (choice == 2) {
                            System.out.println("-------------------");
                            System.out.println("Cancelled deletion");
                            System.out.println("--------------------");

                        }
                    }

                }

                //Generate report
                case 3 -> {
                    System.out.println("-------REPORT--------");
                    Manage.readLeadFile();
                    Report report = new Report(Manage.getLeads());
                    report.showRecordAge();
                    cont = false;
                }
                //End loop and go back to the program main menu
                case 4 -> cont = false;
            }
        }

    }

    public static void interManagement() throws IOException {
        String leadFile = "src/com/company/leads.csv";
        boolean isRun = true;
        //Initializing
        // ----- Lead management class -----
        FileProcessor fp = new FileProcessor();
        Manage.initFile();
        Manage.setFP(fp);
        Manage.setFilePath(leadFile);
        // ----- Interaction management class -----
        InteractionManagement manage = new InteractionManagement();
        if(InteractionManagement.initFile()){
            System.out.println("New file created!");
        }
        while (isRun) {
            System.out.println("----------------Interaction Main Menu----------------");
            System.out.println("----------------");
            System.out.println("1.Show all records");
            System.out.println("2.Add new interaction");
            System.out.println("3.Select interaction");
            System.out.println("4.Report");
            System.out.println("5.Back to main menu");
            System.out.println("----------------");

            int interMenu = Console.validateInt("Type in number of your choice: ", 1, 5);
            switch (interMenu) {
                case 1 -> {
                    System.out.format("%10s%20s%10s%17s%15s\n", "Interaction ID", "Interaction Date","Lead ID" ,"By which mean","Potential");
                    manage.readAll();
                }
                case 2 -> {
                    // Add interaction
                    Manage.readLeadFile();
                    Lead newLead = Manage.getLead(Manage.chooseLeadByID());
                    Interaction newInter = new Interaction();
                    //-------- Get interaction info --------
                    newInter.setInterDate(LocalDate.parse(Console.validateDateWithLimit("Interaction date (YYYY-MM-DD): ")));
                    newInter.setLead(newLead);
                    // Interaction medium choice
                    System.out.println("1.Email");
                    System.out.println("2.Phone");
                    System.out.println("3.Face-to-face");
                    System.out.println("4.Social media");
                    int choice = Console.validateInt("Choice: ", 1, 4);
                    switch (choice){
                        case 1 -> newInter.setInterMedium("email");
                        case 2 -> newInter.setInterMedium("phone");
                        case 3 -> newInter.setInterMedium("face-to-face");
                        case 4 -> newInter.setInterMedium("Social_media");
                    }
                    // Potential choice
                    System.out.println("1.Negative");
                    System.out.println("2.Neutral");
                    System.out.println("3.Positive");
                    choice = Console.validateInt("Choice: ", 1, 3);
                    newInter.setPotential(choice);
                    //-------- End --------
                    // -------- Write into file --------
                    InteractionManagement.write(newInter, true, false);
                }
                case 3 -> {
                    int id = Console.validateInt("Type in the ID (number only): ");
                    int index, field;
                    do {
                        index = manage.binarySearchObject(id);
                        if (index == -1)
                            id = Console.validateInt("The id is not existed, type another: ");
                    } while (index == -1);

                    Interaction inter1 = (Interaction) manage.getObjectbyIndex(index);

                    int selectAction = Console.validateInt("1.UPDATE 2.DELETE 3.CANCEL: ", 1, 3);
                    switch (selectAction) {
                        case 1 -> {
                            do {
                                System.out.println("----------------");
                                System.out.println("1.Date");
                                System.out.println("2.Lead");
                                System.out.println("3.Contact medium");
                                System.out.println("4.Potential");
                                System.out.println("5.Save & Exit");
                                System.out.println("----------------");
                                System.out.println("NOTE: Remember to \"Save & Exit\" to update your file.");

                                field = Console.validateInt("Type in one of the number represent the field you want to modify: ", 1, 5);
                                String temp;
                                switch (field) {
                                    case 1 -> {
                                        temp = Console.validateDateWithLimit("Update interaction date to (YYYY-MM-DD): ");
                                        inter1.setInterDate(LocalDate.parse(temp));
                                    }
                                    case 2 -> {
                                        Manage.readLeadFile();
                                        Lead newLead = Manage.getLead(Manage.chooseLeadByID());
                                        inter1.getLead().setId(newLead.getId());
                                    }
                                    case 3 -> {
                                        // Interaction medium choice
                                        System.out.println("1.Email");
                                        System.out.println("2.Phone");
                                        System.out.println("3.Face-to-face");
                                        System.out.println("4.Social media");
                                        int choice = Console.validateInt("Update contact medium: ", 1, 4);
                                        switch (choice){
                                            case 1 -> inter1.setInterMedium("email");
                                            case 2 -> inter1.setInterMedium("phone");
                                            case 3 -> inter1.setInterMedium("face-to-face");
                                            case 4 -> inter1.setInterMedium("Social_media");
                                        }
                                    }
                                    case 4 -> {
                                        System.out.println("1.Negative");
                                        System.out.println("2.Neutral");
                                        System.out.println("3.Positive");
                                        int choice = Console.validateInt("Choice: ", 1, 3);
                                        inter1.setPotential(choice);
                                    }
                                    default -> {
                                    }
                                }
                            } while (field != 5);
                            manage.updateObject(inter1);
                        }
                        case 2 -> manage.deleteObject(inter1);
                        default -> {
                        }
                    }
                }
                case 4 -> {
                    Report report = new Report();
                    int reportMenu;

                    do {
                        System.out.println("----------------");
                        System.out.println("1.Show all number of interactions by potential");
                        System.out.println("2.Show all number of interactions by month");
                        System.out.println("3.Back to interactions menu");
                        System.out.println("----------------");

                        reportMenu = Console.validateInt("Type in number of your choice: ", 1, 3);
                        if (reportMenu ==3)break;
                        LocalDate startDate= LocalDate.parse(Console.validateDateWithLimit("From date (YYYY-MM-dd number only): "));
                        LocalDate endDate =LocalDate.parse(Console.validateDateWithLimit("To date (YYYY-MM-dd number only): "));
                        boolean isValid = false;
                        while (!isValid) {
                            if(startDate.compareTo(endDate)>0){
                                System.out.println("Start date cannot be after end date!");
                                startDate= LocalDate.parse(Console.validateDateWithLimit("From date (YYYY-MM-dd number only): "));
                            }
                            if(endDate.compareTo(startDate)<0){
                                System.out.println("End date cannot be before start date!");
                                endDate= LocalDate.parse(Console.validateDateWithLimit("To date (YYYY-MM-dd number only): "));
                                isValid =false;
                            }else{
                                isValid =true;
                            }

                        }
                        switch (reportMenu){
                            case 1 -> report.showPotentialReport(manage.getAll(),startDate,endDate);
                            case 2 -> report.showInterMonth(manage.getAll(),startDate,endDate);
                        }
                    }while (true);
                }
                case 5 -> isRun = false;
            }
        }


    }

    public static void main(String[] args) throws IOException {
        while (true) {
            //Program main menu
            System.out.println();
            System.out.println("---MAIN MENU----");
            System.out.println("1.Manage leads");
            System.out.println("2.Manage interactions");
            System.out.println("3.Exit");
            System.out.println("------------------------");
            int choice1 = Console.validateInt("Selection (number): ", 1, 3);
            switch (choice1) {
                case 1 -> leadManagement();
                case 2 -> interManagement();
                case 3 -> System.exit(0);
            }
        }


    }
}


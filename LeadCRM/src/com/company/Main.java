package com.company;

import com.company.myPackage.*;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.IOException;
import java.util.List;


public class Main {
    public static void leadManagement() throws IOException {
        String leadFile = "src/com/company/leads.csv";

        //Initializing
        FileProcessor fp = new FileProcessor();
        Manage.setFP(fp);
        Manage.setFilePath(leadFile);

        InteractionManagement manageInters = new InteractionManagement();
        manageInters.initFile();

        //Add new lead
        int yn = Console.validateInt("Create new lead? (1.Yes  2.No): ", 1, 2);
        if (yn == 1) {
            Manage.addNewLead();
        }


        Manage.showLeadRecords();


        //Delete & Update chosen lead
        Manage.readLeadFile();
        int indexOfLead = Manage.chooseLeadByID();
        int choice = Console.validateInt("1.UPDATE 2.DELETE 3.CANCEL: ", 1, 3);
        if (choice == 1) {
            Manage.updateLead(indexOfLead);
        } else if (choice == 2) {
            System.out.println("Interaction related to this lead:");
            List<Integer> relateIntersID = Manage.showRelatedInteractionID(manageInters.getAll(), Manage.getLeads().get(indexOfLead).getId());
            for (int id : relateIntersID) {
                System.out.println("inter_" + String.format("%03d", id));
            }
            System.out.println("Process to delete chosen lead will also delete any related interactions, continue?");
            choice = Console.validateInt("1.Yes 2.No: ", 1, 2);
            if (choice == 1) {
                Manage.removeLead(indexOfLead);
                for (int interID : relateIntersID) {
                    System.out.println("Deleted inter_" + String.format("%03d", interID));
                    Interaction temp = (Interaction) manageInters.getObject(interID);
                    manageInters.deleteObject(temp);
                }
            } else if (choice == 2) {
                System.out.println("Cancelled deletion");
            }
        }

        Manage.showLeadRecords();
        Report report = new Report(Manage.getLeads());
        report.showRecordAge();

    }

    public static void interManagement() throws IOException {
        String leadFile = "src/com/company/leads.csv";
        boolean isrun = true;
        //Initializing
        // ----- Lead management class -----
        FileProcessor fp = new FileProcessor();
        Manage.setFP(fp);
        Manage.setFilePath(leadFile);
        // ----- Interaction management class -----
        InteractionManagement manage = new InteractionManagement();
        while (isrun) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("----------------");
            System.out.println("1.Show all records");
            System.out.println("2.Add new interaction");
            System.out.println("3.Select interaction");
            System.out.println("4.Exit");
            System.out.println("----------------");

            int interMenu = Console.validateInt("Type in number of your choice: ", 1, 4);
            switch (interMenu) {
                case 1 -> {
                    manage.readAll();
                }
                case 2 -> {
                    // Add interaction
                    Manage.readLeadFile();
                    Lead newlead = Manage.getLead(Manage.chooseLeadByID());
                    Potential newpotential;
                    Interaction newInter = new Interaction();
                    //-------- Get interaction info --------
                    newInter.setInterDate(LocalDate.parse(Console.validateDate("Interaction date (YYYY-MM-DD): ")));
                    newInter.setLead(newlead);
                    System.out.println("1.Email");
                    System.out.println("2.Phone");
                    System.out.println("3.Face-to-face");
                    System.out.println("4.Social media");
                    newInter.setInterMedium(Console.charIn("Email: "));
                    System.out.println("1.Negative");
                    System.out.println("2.Neutral");
                    System.out.println("3.Positive");
                    int choice = Console.validateInt("Choice: ", 1, 3);
                    newInter.setPotential(choice);
                    //-------- End --------
                    // -------- Write into file --------
                    manage.write(newInter, true, false);
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
                                System.out.println("3.Email");
                                System.out.println("4.Potential");
                                System.out.println("5.Save & Exit");
                                System.out.println("----------------");
                                System.out.println("NOTE: Remember to \"Save & Exit\" to update your file.");

                                field = Console.validateInt("Type in one of the number represent the field you want to modify: ", 1, 5);
                                String temp;
                                switch (field) {
                                    case 1 -> {
                                        temp = Console.validateDate("Update interaction date to (YYYY-MM-DD): ");
                                        inter1.setInterDate(LocalDate.parse(temp));
                                    }
                                    case 2 -> {
                                        Manage.readLeadFile();
                                        Lead newlead = Manage.getLead(Manage.chooseLeadByID());
                                        inter1.getLead().setId(newlead.getId());
                                    }
                                    case 3 -> {
                                        temp = Console.validateEmail("Update email: ");
                                        inter1.setInterMedium(temp);
                                    }
                                    case 4 -> {
                                        Potential newpotential;
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
                        case 2 -> {
                            manage.deleteObject(inter1);
                        }
                        default -> {
                        }
                    }
                }
                case 4 -> {
                    isrun = false;
                }
            }

        }


    }

    public static void main(String[] args) throws IOException {
        String leadFile = "src/com/company/leads.csv";

        //Initializing
        FileProcessor fp = new FileProcessor();
        Manage.setFP(fp);
        Manage.setFilePath(leadFile);

//        //Test part
//        LocalDate dob = LocalDate.of(2017, 1, 13);
//        Lead l = new Lead(1, "john", "09029332", true, dob, "johnwick@gmail.com", "100 Tran Phu");
//        Interaction inter = new Interaction(1, dob, l, "123@gmail.com", "positive");
//        System.out.println(inter.getLead().getId());
//        System.out.println(inter.getInterDate());
//        System.out.println(inter.getLead().getId());
//        System.out.println(inter.getPotential().toString());
//        InteractionManagement manage = new InteractionManagement();
//        manage.initFile();
//        manage.write(inter.toArray(), true);

        interManagement();


    }
}


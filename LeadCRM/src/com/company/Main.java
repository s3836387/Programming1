package com.company;

import com.company.myPackage.*;

import java.text.NumberFormat;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.IOException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {
        String leadFile = "src/com/company/leads.csv";

        //Initializing
        FileProcessor fp = new FileProcessor();
        Manage.setFP(fp);
        Manage.setFilePath(leadFile);

        InteractionManagement manageInters = new InteractionManagement();
        manageInters.initFile();

        //Add new lead
        int yn = Console.validateInt("Create new lead? (1.Yes  2.No): ",1,2);
        if (yn == 1) {
            Manage.addNewLead();
        }


        Manage.showLeadRecords();


        //Delete & Update chosen lead
        Manage.readLeadFile();
        int indexOfLead = Manage.chooseLeadByID();
        int choice = Console.validateInt("1.UPDATE 2.DELETE 3.CANCEL: ",1,3);
        if (choice == 1) {
            Manage.updateLead(indexOfLead);
        } else if (choice == 2) {
            System.out.println("Interaction related to this lead:");
            List<Integer> relateIntersID = Manage.showRelatedInteractionID(manageInters.getAll(), Manage.getLeads().get(indexOfLead).getId());
            for (int id : relateIntersID) {
                System.out.println("inter_" + String.format("%03d",id));
            }
            System.out.println("Process to delete chosen lead will also delete any related interactions, continue?");
            choice = Console.validateInt("1.Yes 2.No: ",1,2);
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

        //Test part
//        LocalDate dob = LocalDate.of(2017, 1, 13);
//        Lead l = new Lead(1,"john","09029332",true,dob,"johnwick@gmail.com","100 Tran Phu");
//        Interaction inter = new Interaction(1,dob,l,"123@gmail.com", "positive");
    //        System.out.println(inter.getLead().getId());
    //        System.out.println(inter.getInterDate());
    //        System.out.println(inter.getLead().getId());
    //        System.out.println(inter.getPotential().toString());
        InteractionManagement manage = new InteractionManagement();
    //        manage.initFile();
    //        manage.write(inter.toArray(), true);
        Interaction inter1 = (Interaction) manage.getObject(1);
        manage.deleteObject(inter1);

    }
}


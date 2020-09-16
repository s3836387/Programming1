package com.company;

import com.company.myPackage.*;

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

        //Add new lead
        String yn = Console.charIn("Create new lead? y/n: ");
        if (yn.equals("y") || yn.equals("Y")) {
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
            Manage.removeLead(indexOfLead);
        }

        Manage.showLeadRecords();
        Report report = new Report(Manage.getLeads());
        report.showRecordAge();

        //Test part
        LocalDate dob = LocalDate.of(2017, 1, 13);
        Lead l = new Lead(1,"john","09029332",true,dob,"johnwick@gmail.com","100 Tran Phu");
        Interaction inter = new Interaction(1,dob,l,"123@gmail.com", "positive");
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


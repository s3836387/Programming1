package com.company;

import com.company.myPackage.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.IOException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {
//        String leadFile = "src/com/company/leads.csv";
//
//        //Initializing
//        FileProcessor fp = new FileProcessor();
//        Manage.setFP(fp);
//        Manage.setFilePath(leadFile);
//
//        //Add new lead
//        String yn = Console.charIn("Create new lead? y/n: ");
//        if (yn.equals("y") || yn.equals("Y")) {
//            Lead newLead = Manage.addNewLead();
//            Manage.file().writeNewLead(newLead.leadToString());
//        }
//        Manage.file().showRecords();
//
//
//        //Delete & Update chosen lead
//        List<List<String>> tempStringList = Manage.file().readFile();
//        List<Lead> tempLeadList = Manage.dataToLeads(tempStringList);
//        int indexOfLead = Manage.chooseLeadByID(tempLeadList, Console.validateID(tempLeadList));
//        int choice = Console.validateInt("1.UPDATE 2.DELETE 3.CANCEL: ",1,3);
//        if (choice == 1) {
//            Manage.updateLead(tempLeadList,indexOfLead);
//        } else if (choice == 2) {
//            tempLeadList.remove(indexOfLead);
//        }
//
//        tempStringList = Manage.leadsListToString(tempLeadList);
//        Manage.file().updateFile(tempStringList);
//        Manage.file().showRecords();
//        Report report = new Report(tempLeadList);
//        report.showRecordAge();

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
        Interaction inter1 = manage.getObject(2);
        manage.deleteObject1(inter1);


    }
}

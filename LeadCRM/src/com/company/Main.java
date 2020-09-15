package com.company;

import com.company.myPackage.Lead;


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
            Lead newLead = Manage.addNewLead();
            Manage.file().writeNewLead(newLead.leadToString());
        }
        Manage.file().showRecords();


        //Delete & Update chosen lead
        List<List<String>> tempStringList = Manage.file().readFile();
        List<Lead> tempLeadList = Manage.dataToLeads(tempStringList);
        int indexOfLead = Manage.chooseLeadByID(tempLeadList, Console.validateID(tempLeadList));
        int choice = Console.validateInt("1.UPDATE 2.DELETE 3.CANCEL: ",1,3);
        if (choice == 1) {
            Manage.updateLead(tempLeadList,indexOfLead);
        } else if (choice == 2) {
            Manage.removeLead(tempLeadList,indexOfLead);
        }

        Manage.file().showRecords();
        Report report = new Report(tempLeadList);
        report.showRecordAge();


    }
}

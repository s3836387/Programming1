package com.company;

import com.company.myPackage.Lead;

import java.io.IOException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {
        String leadFile = "C:/Users/quoct/IdeaProjects/Programming1/LeadCRM/src/com/company/leads.csv";

        //Initializing
        FileProcessor fp = new FileProcessor();
        Manage.setFP(fp);
        Manage.setFilePath(leadFile);

        //Add new lead
        String yn = Console.getInstance().stringIN("Create new lead? y/n: ");
        if (yn.equals("y")) {
            Lead newLead = Manage.addNewLead();
            Manage.file().writeNewLead(newLead.leadToString());
            Manage.file().showRecords();
        } else {
            Manage.file().showRecords();
        }


        //Delete & Update chosen lead
        List<List<String>> tempStringList = Manage.file().readFile();
        List<Lead> tempLeadList = Manage.dataToLeads(tempStringList);
        String chosenID = Console.getInstance().stringIN("Type in the id of lead you want to modify: ");
        int indexOfLead = Manage.chooseLeadByID(tempLeadList, Integer.parseInt(chosenID));
        int choice = Console.getInstance().intIn("1.Update 2.Remove 3.Cancel: ");
        if (choice == 1) {
            Manage.updateLead(tempLeadList,indexOfLead);
        } else if (choice == 2) {
            tempLeadList.remove(indexOfLead);
        }

        tempStringList = Manage.leadsListToString(tempLeadList);
        Manage.file().updateFile(tempStringList);
        Manage.file().showRecords();





    }
}

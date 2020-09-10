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
        Lead newLead = Manage.addNewLead();
        Manage.file().writeNewLead(newLead.dataToString());
        Manage.file().showRecords();

        //Delete chosen lead
        List<List<String>> tempStringList = Manage.file().readFile();
        List<Lead> tempLeadList = Manage.dataToLeads(tempStringList);
        String chosenID = Console.getInstance().stringIN("Type in the id of lead you want to delete: ");
        int indexOfLead = Manage.chooseLeadByID(tempLeadList, Integer.parseInt(chosenID));
        tempLeadList.remove(indexOfLead);
        tempStringList = Manage.leadsListToString(tempLeadList);
        Manage.file().updateFile(tempStringList);
        Manage.file().showRecords();



    }
}

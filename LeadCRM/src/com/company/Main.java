package com.company;

import com.company.myPackage.Lead;

import java.io.IOException;

import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {
        String leadFile = "C:/Users/quoct/IdeaProjects/Programming1/LeadCRM/src/com/company/leads.csv";
        FileProcessor leadCSV = new FileProcessor();
        leadCSV.setFilePath(leadFile);
        Manage.setFP(leadCSV);
        Lead cus = Manage.addNewLead();
        leadCSV.writeNewLead(cus.dataToString());
        leadCSV.showRecords();
        List<Lead> tempLead = Manage.dataToLeads(leadCSV.readFile());
        String choice = Console.getInstance().dataIn("type in id to delete desired lead ");
        int indexLead = Manage.chooseLeadByID(tempLead,Integer.parseInt(choice));
        tempLead.remove(indexLead);
        leadCSV.updateFile(Manage.leadsListToString(tempLead));
        leadCSV.showRecords();


    }
}

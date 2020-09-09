package com.company;

import com.company.myPackage.Lead;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        String field1, field2, field3;
        Lead customer = new Lead();
        String[] test = {"john", "johnwick@gmail.com"};
        String csvFile = "C:/Users/quoct/IdeaProjects/Programming1/LeadCRM/src/com/company/leads.csv";
        FileProcessor.write(csvFile,test);
        FileProcessor.showRecords(csvFile);

    }
}

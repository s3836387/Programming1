package com.company;

import com.company.myPackage.Lead;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileProcessor {
    private static String filePath = "leads.csv";

    
//write into the file by line
    public static void write(String csvFile, String[] data) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(csvFile, true));
        String id = generateID();
        String delimiter = ",";
        pw.print(id);
        for (String record : data) {
            pw.print(delimiter + record);
        }
        pw.println();
        pw.close();
    }
//read file by line and show records to the console
    public static void showRecords(String csvFile) throws FileNotFoundException {
        Scanner input = new Scanner(new File(csvFile));
        String line;
        System.out.println("ID\tname\tEmail");
        while (input.hasNext()) {
            line = input.nextLine();
            StringTokenizer inReader = new StringTokenizer(line, ",");
            String id = inReader.nextToken();
            String name = inReader.nextToken();
            String email = inReader.nextToken();
            System.out.println(id + "\t" + name + "\t" + email);
        }
        input.close();

    }

//generate an id base on the last line of csv file, if the file is empty return lead_001
    private static String generateID() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner input = new Scanner(new File(filePath));
        String id ="lead_001";
        String line;
        if (file.length() == 0) {
            id = "lead_001";
        } else while (input.hasNext()) {
            line = input.nextLine();
            StringTokenizer inReader = new StringTokenizer(line,",");
            id = inReader.nextToken();
            int temp = extractLeadID(id) + 1;
            id = formatLeadID(temp);

        }
        input.close();
        return id;

    }
//get integer from lead_xxx
    private static int extractLeadID(String id) {
        int tempID;
        id = id.substring(5,8);
        while (id.indexOf("0") == 0) {
            id = id.substring(1);
        }
        tempID = Integer.parseInt(id);
        return tempID;
    }
//format integer into lead_xxx
    private static String formatLeadID(int id) {
        return "lead_" + String.format("%03d",id);
    }
}

package com.company;

import com.company.myPackage.*;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InteractionManagement implements DAO {
    private static InteractionManagement MANAGE = new InteractionManagement();
    private static final String filePath = "src/com/company/dummy.csv";

    // ------- Initialize file --------
    public static boolean initFile() {
        try {
            File myObj = new File(filePath);
            return myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    // ------- File Writer --------
    public static void write(String interaction) {
        try {
            FileWriter myWriter = new FileWriter(filePath, true);
            myWriter.append(interaction + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    // ------- End --------
    // ------- File Reader --------
    @Override
    public void readAll() throws FileNotFoundException {
        String temp = "temp";
        String row;
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
            while ((row = csvReader.readLine()) != null) {
                System.out.println(row);
                String[] data = row.split(",");
                temp = data[0];
            }
            csvReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println(temp);
    }

    // ------- Data selection --------
    @Override
    public crmObject getObject(String id) throws FileNotFoundException {
        String row;
        String temp;
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
            while ((row = csvReader.readLine()) != null) {
                System.out.println(row);
                String[] data = row.split(",");
                temp = data[0];
            }
            csvReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }
  
    // ------- End Readers --------
    // ------- Data process --------
    private static int extractID(String id) {
        int tempID;
        id = id.substring(id.lastIndexOf("_") + 1);
        tempID = Integer.parseInt(id);
        return tempID;
    }

    private static String generateID() throws FileNotFoundException {
        File file = new File(filePath);
        String id = "inter_001", temp = "inter_001";
        String row;
        if (file.length() > 0) {
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    temp = data[0];
                }
                csvReader.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            id = formatID(extractID(temp));
        }
        return id;
    }
    //format integer into inter_xxx
    private static String formatID(int id) {
        return "inter_" + String.format("%03d", id);
    }



    @Override
    public void updateObject(crmObject interaction) {

    }

    @Override
    public void deleteObject(crmObject interaction) {

    }
}

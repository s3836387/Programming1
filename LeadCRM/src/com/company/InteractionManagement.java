package com.company;

import com.company.myPackage.*;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;


public class InteractionManagement implements DAO {
    private static InteractionManagement MANAGE = new InteractionManagement();
    private static final String filePath = "src/com/company/dummy2.csv";

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
    public static void write(crmObject inter, boolean isappend, boolean isDel) {
        List <String> interaction = ((Interaction)inter).toArray();
        try {
            FileWriter myWriter = new FileWriter(filePath, isappend);
            if (isDel){
                myWriter.append(formatID(inter.getId())).append(",").append(String.join(",", interaction)).append("\n");
            }else{
                myWriter.append(generateID()).append(",").append(String.join(",", interaction)).append("\n");
            }

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
    public List<crmObject> getAll() throws FileNotFoundException {
        List<crmObject> interactions = new ArrayList<>();
        String temp = "temp";
        String row;
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                Lead lead = new Lead();
                lead.setId(extractID(data[2]));
                interactions.add(new Interaction(extractID(data[0]),LocalDate.parse(data[1]),lead,data[3],data[4]));
            }
            csvReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return interactions;
    }
    // ------- Data selection --------
    @Override
    public crmObject getObject(String id) throws FileNotFoundException {
        List<crmObject> interList = getAll();
        Interaction temp;
        return null;
    }
    public Interaction getObject(int id) throws FileNotFoundException {
        List<crmObject> interList = getAll();
        for (crmObject i : interList) {
            if (id == i.getId()){
                return (Interaction)i;
            }
        }
        return null;
    }
  
    // ------- End Readers --------


    // ------- Data process --------
    // ------- ID processors --------
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
                    if (!data[0].equals("")) {
                        temp = data[0];
                    }
                }
                csvReader.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            id = formatID(extractID(temp)+1);
        }
        return id;
    }

    //format integer into inter_xxx
    private static String formatID(int id) {
        return "inter_" + String.format("%03d", id);
    }
    //------- End ID process --------

    @Override
    public void updateObject(crmObject interaction) {

    }

    @Override
    public void deleteObject(crmObject interaction) {

    }

    public void deleteObject1(crmObject interaction) throws IOException {
        List<crmObject> interList = getAll();
        if(interList.get(0).getId() != interaction.getId()){
            write(((Interaction)interList.get(0)),false,true);
        }
        for (int i =1; i < interList.size(); i++) {
            if (interList.get(i).getId() == interaction.getId()){
                continue;
            }else{
                write(((Interaction)interList.get(i)),true,true);
            }
        }
    }
}

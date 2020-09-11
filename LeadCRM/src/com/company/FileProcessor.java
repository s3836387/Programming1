package com.company;

import java.io.*;
import java.util.*;

public class FileProcessor implements I_FileProcessor {
    private String filePath;

    @Override
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    //write into the file by line
    @Override
    public void writeNewLead(List<String> data) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(filePath, true));
        String id = generateLeadID();
        String delimiter = ",";
        pw.print(id);
        for (String record : data) {
            pw.print(delimiter + record);
        }
        pw.println();
        pw.flush();
        pw.close();
    }

    //update data in the file
    @Override
    public void updateFile(List<List<String>> listData) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(filePath, false));
        for (List<String> row : listData) {
            ListIterator<String> iterator = row.listIterator(1);
            pw.print(row.get(0));
            while (iterator.hasNext()) {
                pw.print("," + iterator.next());
            }
            pw.println();
        }
        pw.close();
    }

    //Show records to the console
    @Override
    public void showRecords() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        String line;
        System.out.println("ID\tname\tEmail");
        while ((line = csvReader.readLine()) != null) {
            String[] data = line.split(",");
            for (String record : data) {
                System.out.print(record + "\t");
            }
            System.out.println();
        }
        csvReader.close();

    }

    //read all the data into array list
    @Override
    public List<List<String>> readFile() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        String row;
        List<List<String>> records = new ArrayList<>();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            records.add(Arrays.asList(data));
        }
        csvReader.close();
        return records;
    }




    //generate an id base on the last line of csv file, if the file is empty return lead_001
    @Override
    public String generateLeadID() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner input = new Scanner(new File(filePath));
        String id = "lead_001";
        String line;
        if (file.length() == 0) {
            id = "lead_001";
        } else while (input.hasNext()) {
            line = input.nextLine();
            StringTokenizer inReader = new StringTokenizer(line, ",");
            id = inReader.nextToken();
            int temp = extractID(id) + 1;
            id = formatLeadID(temp);

        }
        input.close();

        return id;

    }



    //get integer from id_xxx
    @Override
    public int extractID(String id) {
        int tempID;
        id = id.substring(id.lastIndexOf("_") + 1);
        tempID = Integer.parseInt(id);
        return tempID;
    }


    //format integer into lead_xxx
    @Override
    public String formatLeadID(int id) {
        return "lead_" + String.format("%03d", id);
    }
}

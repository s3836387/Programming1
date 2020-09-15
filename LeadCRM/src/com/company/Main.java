package com.company;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;
import com.company.myPackage.*;
import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        // write your code here
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        Date dob = new Date();
//        try {
//            dob = simpleDateFormat.parse("20-07-2001");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
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

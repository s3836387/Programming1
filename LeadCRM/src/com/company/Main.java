package com.company;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.company.myPackage.*;
import java.io.IOException;


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
//        Lead l = new Lead(1,"john","09029332",true,dob,"johnwick@gmail.com","100 Tran Phu");
//        Interaction inter = new Interaction(1,dob,l,"123@gmail.com", Potential.positive);
//        System.out.println(inter.getLead().getId());
//        System.out.println(inter.getInterDate());
//        System.out.println(inter.getLead().getId());
//        System.out.println(inter.getEmail());
        InteractionManagement inter = new InteractionManagement();
        inter.initFile();
        inter.readAll();
    }
}

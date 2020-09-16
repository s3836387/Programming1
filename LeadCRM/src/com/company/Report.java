package com.company;

import com.company.myPackage.Lead;

import java.util.ArrayList;
import java.util.List;

public class Report {
    private List<Lead> data;

    public Report(List<Lead> data) {
        this.data = data;
    }

    public void showRecordAge() {
        List<Integer> ageList = new ArrayList<>();
        int counter1 = 0, counter2 = 0, counter3 = 0,counter4 = 0;
        for (Lead lead : data) {
            lead.ageCalculator();
            ageList.add(lead.getAge());
        }

        for (int age : ageList) {
            if (age <= 10) {
                counter1++;
            } else if (age <= 20) {
                counter2++;
            } else if (age <= 60) {
                counter3++;
            } else {
                counter4++;
            }
        }
        System.out.println();
        System.out.println("-----Number of customers classified by age----");
        System.out.println("<= 10 years old: " + counter1);
        System.out.println("11 - 20 years old: " + counter2);
        System.out.println("21 - 60 years old: " + counter3);
        System.out.println(">60 years old: " + counter4);
        System.out.println();
    }
}

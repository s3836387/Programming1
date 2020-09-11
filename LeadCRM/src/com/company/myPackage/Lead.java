package com.company.myPackage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lead extends crmObject {
    private String name;
    private String phoneNumber;
    private boolean gender;
    private Date birthDate;
    private String email;
    private String address;

    //Constructor
    public Lead() {
    }

    public Lead(int id, String name, String phoneNumber, boolean gender, Date birthDate, String email, String address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.address = address;
    }

    //Getter
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isGender() {
        return gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    //Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //Methods
    @Override
    boolean equal(Object x ,int choice) {
        //write something
        return false;
    }

    public List<String> leadToString() {
        List<String> data = new ArrayList<>();
        data.add(name);
        data.add(email);
        return data;
    }
}

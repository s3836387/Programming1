package com.company.myPackage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Interaction extends crmObject {
    private LocalDate interDate;
    private Lead lead;
    private String email;
    private Potential potential;

    //Constructor
    public Interaction() {
    }

    public Interaction(int id, LocalDate interDate, Lead lead, String email, String potential) {
        this.id = id;
        this.interDate = interDate;
        this.lead = lead;
        this.email = email;
        switch (potential.toLowerCase()){
            case ("positive"):
                this.potential= Potential.positive;
            case ("negative"):
                this.potential= Potential.negative;
            case ("neutral"):
            default:
                this.potential= Potential.neutral;
        }
    }

    //Getter
    public LocalDate getInterDate() {
        return interDate;
    }

    public Lead getLead() {
        return lead;
    }

    public String getEmail() {
        return email;
    }

    public Potential getPotential() {
        return potential;
    }

    //Setter
    public void setInterDate(LocalDate interDate) {
        this.interDate = interDate;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPotential(int choice) {
        switch (choice){
            case(0):
                this.potential = Potential.positive;
                break;
            case(2):
                this.potential = Potential.negative;
                break;
            case(1):
            default:
                this.potential = Potential.neutral;
                break;
        }
    }

    //Methods
    @Override
    boolean equal(Object x ,int choice) {
        //write something
        return false;
    }

    public List<String> toArray() {
        String leadid = new String("lead_" + String.format("%03d",lead.getId()));
        List<String> data = new ArrayList<>();
        data.add(interDate.toString());
        data.add(leadid);
        data.add(email);
        data.add(potential.toString());
        return data;
    }


}

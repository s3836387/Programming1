package com.company.myPackage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Interaction extends crmObject {
    private LocalDate interDate;
    private Lead lead;
    private String interMedium;
    private Potential potential;

    //Constructor
    public Interaction() {
    }

    public Interaction(int id, LocalDate interDate, Lead lead, String interMedium, String potential) {
        this.id = id;
        this.interDate = interDate;
        this.lead = lead;
        this.interMedium = interMedium;
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

    public String getInterMedium() {
        return interMedium;
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

    public void setInterMedium(String interMedium) {
        this.interMedium = interMedium;
    }

    public void setPotential(int choice) {
        switch (choice){
            case(3):
                this.potential = Potential.positive;
                break;
            case(1):
                this.potential = Potential.negative;
                break;
            case(2):
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

    public LocalDate stringToDate(String dateString) {
        return LocalDate.parse(dateString);
    }

    public List<String> toArray() {
        String leadid = new String("lead_" + String.format("%03d",lead.getId()));
        List<String> data = new ArrayList<>();
        data.add(interDate.toString());
        data.add(leadid);
        data.add(interMedium);
        data.add(potential.toString());
        return data;
    }


}

package com.company.myPackage;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Interaction extends crmObject {
    private Date interDate;
    private Lead lead;
    private String email;
    private Potential potential;

    //Constructor
    public Interaction() {
    }

    public Interaction(int id, Date interDate, Lead lead, String email, Potential potential) {
        this.id = id;
        this.interDate = interDate;
        this.lead = lead;
        this.email = email;
        this.potential= potential;
    }

    //Getter
    public Date getInterDate() {
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
    public void setInterDate(Date interDate) {
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
}

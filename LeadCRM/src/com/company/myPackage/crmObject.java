package com.company.myPackage;

abstract class crmObject {
    protected int id;
    public crmObject() {}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    abstract void update(int choice);
}

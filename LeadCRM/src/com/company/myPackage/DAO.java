package com.company.myPackage;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DAO {
    public crmObject getObject(String id) throws FileNotFoundException;
    public void readAll() throws FileNotFoundException;
    public void updateObject(crmObject student);
    public void deleteObject(crmObject student);
}

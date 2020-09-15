package com.company.myPackage;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DAO {
    public crmObject getObject(int id) throws FileNotFoundException;
    public void readAll() throws FileNotFoundException;
    public void updateObject(crmObject student)throws IOException;
    public void deleteObject(crmObject student) throws IOException;
}

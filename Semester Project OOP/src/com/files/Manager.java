package com.files;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Manager extends Employee implements Serializable {

    int exp;

    public Manager(String name, String pN, String mail, int i, Date date , int e ) {
        super(name, pN, mail, i , date);
        this.exp = e;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
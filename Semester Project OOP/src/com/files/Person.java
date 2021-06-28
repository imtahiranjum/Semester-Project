package com.files;

public class Person {
    String name;
    String phoneNo;
    String email;

    public Person(String n, String p, String em)
    {
        name = n;
        phoneNo = p;
        email = em;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

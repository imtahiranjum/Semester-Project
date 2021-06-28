package com.files;

import java.util.Scanner;

public class Account {
    private String id;
    private String password;
    private String status;

    Account(String id , String pass, String sta){
        this.id = id;
        this.password=pass;
        this.status=sta;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    Scanner scan = new Scanner(System.in);



    void ResetPassword(){
        System.out.println("Enter Old Password :");
        String s = scan.nextLine();

        if (s.equals(password))
        {
            System.out.println("Enter New Password :");
            String s1 = scan.nextLine();
            System.out.println("Enter Confirm Password :");
            String s2 = scan.nextLine();

            if (s1.equals(s2))
            {
                password=s1;
            }
        }
    }
}
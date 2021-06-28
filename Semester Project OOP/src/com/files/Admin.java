package com.files;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Admin {

    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<Manager> managers = new ArrayList<>();

    private String userName="1";
    private String password="1";

    Account admin = new Account(userName,password,"Active");
    Scanner scanner = new Scanner(System.in);

    ArrayList<Integer> price = new ArrayList<>();
    ArrayList<String> nameOfItem = new ArrayList<>();


    Admin()  {
        readEmployee();
    }

    void adminMainMenu() throws IOException, ClassNotFoundException {

        char choice ;

        while (true) {
            System.out.println("""
                    1-For LOGIN\s
                     2-PasswordReset
                    Enter Choice:\s""");
            choice = scanner.next().charAt(0);
            Scanner scan = new Scanner(System.in);
            switch (choice) {
                case '1' -> {
                    System.out.println("Enter Username:- ");
                    String s = scan.nextLine();
                    System.out.println("Enter Password:-");
                    String p = scan.nextLine();
                    if (s.equals(admin.getId()) && p.equals(admin.getPassword())) {
                        System.out.println("Login successfully.");
                        adminDrivenMenu();
                    }
                }
                case '2' -> admin.ResetPassword();
                default -> System.out.println("Nothing....!!");
            }

            System.out.println("Enter y to back to main menu");
            char ch = (new Scanner(System.in)).next().charAt(0);
            if (ch=='y' || ch=='Y')
            {
                break;
            }
        }



    }

    void adminDrivenMenu() throws IOException, ClassNotFoundException {
        readFile();

        while (true){
            System.out.println("Press 1 to see all items present in Menu");
            System.out.println("Press 2 to delete specific items present in Menu");
            System.out.println("Press 3 to add new items in Menu");
            System.out.println("Press 4 to update new items in Menu");

            System.out.println("Press 5 to add Manager");
            System.out.println("Press 6 to see details");

            System.out.println("Enter choice:   ");

            int choice;
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> Display();
                case 2 -> Delete();
                case 3 -> Add();
                case 4 -> Update();
                case 5 -> AddManger();
                case 6 -> Print();
            }

            System.out.println("press Y or y if you want to go back to Main menu \n or press any key to remain in current Menu :- ");
            char ch=scanner.next().charAt(0);
            if (ch=='y' || ch=='Y')
            {
                break;
            }

        }
    }


    private void Update() throws IOException {
        System.out.println("Enter product# you want to update ");
        int pNo = scanner.nextInt();



            while (true){


                Display();
                System.out.println("Select # you want to change:- ");
                int index=(new Scanner(System.in)).nextInt();

                int choice;
                System.out.println("1-> Name-- \n 2-> Price :");
                choice=scanner.nextInt();

                switch (choice){
                    case 1:
                        System.out.println("Enter name:");
                        String name =(new Scanner(System.in)).nextLine()  ;
                        nameOfItem.set(index-1 , name);

                        break;

                    case 2:
                        System.out.println("Enter price");
                        int p =(new Scanner(System.in)).nextInt()  ;
                        price.set(index-1 , p);

                        break;

                }

                System.out.println("Press 1 if you want to update again or any key to exit: ");
                char ch = scanner.next().charAt(0);
                if (ch!='1')break;
            }

        updateFile();
    }

    private void Delete() throws IOException {
        System.out.println("Enter product# you want to delete ");
        int pNo = scanner.nextInt();
        price.remove(pNo-1);
        nameOfItem.remove(pNo-1);
        updateFile();
    }

    private void Add() throws IOException {
        Scanner dummy = new Scanner(System.in);
        System.out.println("Enter product name you want to add ");
        String s = dummy.nextLine();
        System.out.println("Enter price of product");
        int pNo = scanner.nextInt();
        price.add(pNo);
        nameOfItem.add(s);
        updateFile();
    }

    private void updateFile() throws IOException {
        FileWriter fileWriter = new FileWriter("items.txt");
        for (int i = 0; i < price.size(); i++) {
            fileWriter.write(nameOfItem.get(i)+ "\t\t"+ price.get(i) + "\n");
        }
        fileWriter.close();
    }

    void readFile() throws IOException {

        File file = new File("items.txt");

        if (file.createNewFile()) {
            System.out.println("File created");
        }
        else {
            System.out.println("File exists..");
        }

        Scanner filescanner = new Scanner(file);

        while (filescanner.hasNextLine())
        {

            String s="";
            String str = filescanner.nextLine();
            for (int i = str.length() - 1; i>=0 ; i--)
            {
                if (str.charAt(i)==' ' || str.charAt(i)=='\t')
                {
                    break;
                }
                else {
                    s=s+str.charAt(i);
                }
            }

            String p = "";
            for (int  i = s.length()-1; i>=0; i--) {
                p = p + s.charAt(i);
            }

            s="";

            for (int i = 0; i <str.length()-1 ; i++) {
                if ((str.charAt(i)== ' ' && str.charAt(i+1)== ' ') || (str.charAt(i)== '\t' && str.charAt(i+1)== '\t') )
                {
                    break;
                }
                s=s+str.charAt(i);
            }

                nameOfItem.add(s);
                price.add(Integer.parseInt(p));
        }
        filescanner.close();
    }



    void Display() throws IOException {


        for (int i=0 ; i<nameOfItem.size() ; i++)
        {
            System.out.printf("%7d%25s%10d\n",i+1 ,nameOfItem.get(i) ,price.get(i));
        }
    }

    public  void AddManger() throws IOException {


        Date today = new Date();
        System.out.println("Enter a name: ");
        String name= new Scanner(System.in).nextLine();

        System.out.println("Enter phone#: ");
        String p=new Scanner(System.in).nextLine();

        System.out.println("Enter a email: ");
        String m=new Scanner(System.in).nextLine();


        Manager manager = new Manager(name,p,m, managers.size()+1 , today , 7);
        managers.add(manager);

        FileOutputStream fos = new FileOutputStream("manager");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (int i = 0; i < managers.size(); i++) {

            try {
                oos.writeObject(managers.get(i));
                System.out.println("Data is added.");
            }
            catch (Exception e){

            }

        }

        fos.close();
    }

    void readEmployee()  {
        try {
            FileInputStream fileIn = new FileInputStream("manager");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            while (true) {

                Manager a = (Manager) objectIn.readObject();
                managers.add(a);

            }

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // e.printStackTrace();
        }
        System.out.println("??????????????????");

    }


    void Print()  {


        for (int i = 0; i <managers.size(); i++) {

            System.out.println(managers.get(i).toString());

        }

    }
}
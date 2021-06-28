package com.files;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Employee extends Person implements Serializable {
    int employeId;
    Date dateJoined;

    ArrayList<Integer> arrayList = new ArrayList<>();

    public Employee(String name, String pN, String mail, int i, Date date) {
        super(name,pN,mail);
        employeId = alotTable();
        dateJoined = date;
    }

    @Override
    public String toString() {
        return  super.toString() + "Employee{" +
                "employeId=" + employeId +
                ", dateJoined=" + dateJoined +
                '}';
    }

    int alotTable(){
        Random random = new Random();
        int i = 0;
        boolean status=true;
        while (status){
            i = random.nextInt(100);
            for (Integer integer : arrayList) {

                if (integer == i) {
                    status = true;
                    break;
                } else
                    status = false;


            }

            if (arrayList.size()==0){
                arrayList.add(i);
            }
            if (!status)
                arrayList.add(i);
        }

        return i;
    }

}



package com.files;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Reservation implements Serializable {

    int peopleCount;
    String reservationStatusStr = "";
    boolean reservationStatus ;
    Date checkInTime;
    String input ;
    Calendar bookingDate;
    int talbeId;

    ArrayList<Integer> arrayList = new ArrayList<>();


        public Reservation(int peopleCount, boolean reservationStatus, Date checkInTime, String input , Calendar c ) {
        this.peopleCount = peopleCount;
        this.reservationStatus=reservationStatus;
        this.checkInTime = checkInTime;
        this.input = input;
        this.bookingDate = c;
        talbeId = alotTable();

    }

    public Reservation() {
    }



    @Override
    public String toString() {
        return "Reservation Details: -\n" +
                "Number of people: " + peopleCount +
                "\nReservation Status: " + reservationStatusStr +
                "\nCheck-in Time: " + checkInTime +
                "\nBooking Date: " + bookingDate.getTime() +"\n___******___******___\n";
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
                    reservationStatusStr = "Reserved";
                    break;
                } else
                    status = false;
                    reservationStatusStr = "NOT Reserved";


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
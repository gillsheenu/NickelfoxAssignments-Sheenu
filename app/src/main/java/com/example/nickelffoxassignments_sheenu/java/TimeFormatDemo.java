package com.example.nickelffoxassignments_sheenu.java;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class TimeFormatDemo {

    public static void main(String[] args) {
       LocalDate localDate=LocalDate.now();
        System.out.println("localDate is : "+ localDate);

        LocalTime localTime=LocalTime.now();
        System.out.println(localTime);

        LocalDateTime t1=LocalDateTime.now();
        System.out.println(t1);

        DateTimeFormatter dd=DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm:ss");
        String s= t1.format(dd);
        System.out.println(s);
    }


}

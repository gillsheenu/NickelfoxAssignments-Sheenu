package com.example.nickelffoxassignments_sheenu.java;

import java.util.Date;

public class DateClassDemo {

    public static void main(String[] args) {

//        long currentTime=System.currentTimeMillis();
//        System.out.println(currentTime);
        Date d= new Date();
        Date d1 =new Date(2000,12,3);
        Date d2 =new Date(2023,6,30);
//        System.out.println(d1);
        System.out.println(d.getHours());

    }
}

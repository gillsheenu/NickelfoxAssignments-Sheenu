package com.example.nickelffoxassignments_sheenu.java;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Calendar;

public class CalenderClassDemo {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args) {

        Calendar c=Calendar.getInstance();
        System.out.println(c);
        System.out.println(c.getCalendarType());
        System.out.println(c.getTimeZone().getID());
        System.out.println(c.get(Calendar.DAY_OF_MONTH));
        System.out.println(c.getMaximum(Calendar.DAY_OF_WEEK_IN_MONTH));
    }
}

package com.example.nickelffoxassignments_sheenu.java;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileNumberValidation {

    public static boolean isValidNumber(String mobile) {
        String regrex="(0/91)?[7-9][0-9]{9}";
        Pattern pattern=Pattern.compile(regrex);
        Matcher matcher= pattern.matcher(mobile);
        return (matcher.find()&&matcher.group().equals(mobile));
    }


    public static void main(String[] args) {
        System.out.println("Enter the Mobile Number: ");
        Scanner inputMobile = new Scanner(System.in);
        String mobile = inputMobile.next();

        if(isValidNumber(mobile)){
            System.out.println("Given Mobile Number is valid");
        }else{
            System.out.println("Given  is not Mobile Number is not valid");
        }
    }


}

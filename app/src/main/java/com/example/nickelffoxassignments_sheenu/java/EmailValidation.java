package com.example.nickelffoxassignments_sheenu.java;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {
    public static void main(String[] args) {
        System.out.println("Enter the Email Address: ");
        Scanner inputEmail = new Scanner(System.in);
        String email= inputEmail.next();

        String regrex="[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+";
        Pattern pattern=Pattern.compile(regrex);
        Matcher matcher= pattern.matcher(email);

        if(matcher.matches()){
            System.out.println("Given email id is valid");
        }else{
            System.out.println("Given email id is not valid");
        }


    }
}

package com.example.nickelffoxassignments_sheenu.java.multithreading;

class Thread1 extends Thread{
    @Override
    public void run() {
        try{
            int i=0;
            while(i<500) {
                System.out.println("Thread of  class thread1 with id " + Thread.currentThread().getId() + "  is running");
                i++;
            }
        }catch (Exception e){
            System.out.println("Exception " + e +" occured in thread1");
        }
    }
}

class Thread2 extends Thread{
    @Override
    public void run() {
        try{
            int i=0;
            while(i<200) {
                System.out.println("Thread of class Thread2 with id " + Thread.currentThread().getId() + "  is running");
                i++;
            }
        }catch (Exception e){
            System.out.println("Exception " + e +" occured in thread2");
        }
    }
}


public class ThreadClassExtended {
    public static void main(String[] args) {
        Thread1 thread1=new Thread1();
        Thread2 thread2=new Thread2();
        thread1.start();
        thread2.start();
    }
}

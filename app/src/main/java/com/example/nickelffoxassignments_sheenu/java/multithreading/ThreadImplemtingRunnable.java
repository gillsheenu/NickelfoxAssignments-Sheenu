package com.example.nickelffoxassignments_sheenu.java.multithreading;


class ThreadR1 implements Runnable{

    @Override
    public void run() {
        try{
            int i=0;
            while(i<500) {
                System.out.println("Thread of  class thread1 with id " + Thread.currentThread().getId() + "  is running");
                i++;
            }
        }finally {
            System.out.println(Thread.currentThread().getName() +"stops");
        }

    }
}

public class ThreadImplemtingRunnable {
    public static void main(String[] args) {
        Thread thread1=new Thread(new ThreadR1());
        Thread thread2=new Thread(new ThreadR1(),"ThreadRunnable");
        thread1.start();
        thread2.start();
        thread2.getName();
    }




}

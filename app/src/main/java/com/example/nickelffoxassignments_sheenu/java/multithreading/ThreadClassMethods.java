package com.example.nickelffoxassignments_sheenu.java.multithreading;

class ThreadM1 extends Thread{
    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Sub Thread");
                Thread.sleep(4000);
            }
        }catch(InterruptedException e ){
            System.out.println("sub Thread is interrupted");
        }
        System.out.println("Thread is running");
    }
}
class ThreadM2 extends Thread{
    @Override
    public void run() {
        int i=0;
        while(i<200){
            System.out.println("ThreadM2 is running");
            i++;
        }

    }
}

public class ThreadClassMethods {
    public static void main(String[] args) throws InterruptedException {
        ThreadM1 threadM1=new ThreadM1();
        ThreadM2 threadM2=new ThreadM2();
        threadM1.start();
        threadM1.interrupt();
        threadM1.join();
        threadM2.start();
        System.out.println("MainThread is running");
    }



}

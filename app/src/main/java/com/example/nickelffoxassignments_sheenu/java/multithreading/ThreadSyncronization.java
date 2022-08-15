package com.example.nickelffoxassignments_sheenu.java.multithreading;

class counter{
    int count;
    public synchronized void increment() {
        count++;
    }
}

public class ThreadSyncronization {
    public static void main(String[] args) throws InterruptedException {
        counter counter =new counter();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=1000;i++){
                    counter.increment();

                }
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=1000;i++){
                    counter.increment();

                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("count value is "+ counter.count);
    }
}

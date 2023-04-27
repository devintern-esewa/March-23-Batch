package com.esewa.internship.gyalbusherpa.esewaIntern.week_3.day11_MultiThreading;

class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("child"+ Thread.currentThread().getName());

        for (int i = 0; i < 10; i++) {
            System.out.println("child");
        }
    }
}
public class F3_Runnable {
    /*
    We can define a thread by implementing Runnable Interface.
    Runnable Interface is present in java.lang package, and it contains only one method i.e. run()
     */

    /*
        Constructors (8)

        Thread t = new Thread();
        Thread t = new Thread(Runnable r);
        Thread t = new Thread(String name);
        Thread t = new Thread(Runnable r, String name);
        Thread t = new Thread(ThreadGroup g, String name);
        Thread t = new Thread(ThreadGroup g, Runnable r);
        Thread t = new Thread(ThreadGroup g, Runnable r, String name);
        Thread t = new Thread(ThreadGroup g, Runnable r, String name);
        Thread t = new Thread(ThreadGroup g, Runnable r, String name,long stackSize);
     */
    public static void main(String[] args) {
        MyRunnable r = new MyRunnable();
        Thread t1 = new Thread(r); // target runnable
        t1.start();

        System.out.println("main"+ Thread.currentThread().getName());

        for (int i = 0; i < 10; i++) {
            System.out.println("main");
        }
    }
}

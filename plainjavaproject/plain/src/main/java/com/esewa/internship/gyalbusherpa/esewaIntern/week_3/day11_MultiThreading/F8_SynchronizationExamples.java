package com.esewa.internship.gyalbusherpa.esewaIntern.week_3.day11_MultiThreading;

class Dis{
    public void displayn(){
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){

            }
        }
    }
    public synchronized void displayc(){
        for (int i = 0; i <= 75; i++) {
            System.out.println((char)i);
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){

            }
        }
    }
}

class MyThread1 extends Thread{
    Dis d;
    MyThread1(Dis d){
        this.d = d;
    }
    public void run(){
        d.displayn();
    }
}
class MyThread2 extends Thread{
    Dis d;
    MyThread2(Dis d){
        this.d = d;
    }
    public void run(){
        d.displayc();
    }
}
public class F8_SynchronizationExamples {
    public static void main(String[] args) {
        Dis d = new Dis();
        MyThread1 t1 = new MyThread1(d);
        MyThread2 t2 = new MyThread2(d);
        t1.start();
        t2.start();
    }
}

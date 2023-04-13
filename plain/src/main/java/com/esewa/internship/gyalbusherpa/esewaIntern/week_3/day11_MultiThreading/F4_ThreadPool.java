package com.esewa.internship.gyalbusherpa.esewaIntern.week_3.day11_MultiThreading;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class PrintJob implements Runnable{
    String name;
    PrintJob(String name){
        this.name = name;

    }
    public void run(){
        System.out.println(name + ".. job started by Thread : " + Thread.currentThread().getName());
        try{
            Thread.sleep(5000);
        }
        catch (InterruptedException e){

        }
    }
}
public class F4_ThreadPool {

    /*
        Creating a new thread for every job may create performance and memory problem.
        To overcome this, we should go for thread pool.
        Thread pool is pool of already created thread ready to do our job.
        Java 1.5v introduces thread pool framework to implement thread pools.
        Thread pool framework also known as executor framework.

        We can create a Thread pool as follows:

        ExecutorService service = Executors.newFixedThreadPool(3);

        We can submit a runnable job by using submit method:
        service.submit(job);

        We can shut down executor service by using shutdown method:
        service.shutdown();
     */

    /*
        While designing web servers and application servers, we can use thread pool concept.
     */


    public static void main(String[] args) {
        PrintJob[] jobs = {
                new PrintJob("don"),
                new PrintJob("son"),
                new PrintJob("gon"),
                new PrintJob("won"),
                new PrintJob("uon"),
        };
        ExecutorService service = Executors.newFixedThreadPool(5);
        for(PrintJob job: jobs){
            service.submit(job);
        }
        service.shutdown();
    }
}

package com.esewa.internship.gyalbusherpa.esewaIntern.week_3.day11_MultiThreading;

class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Child thread");
        }
    }

    public void run(int i) {
        System.out.println(i + "method overloaded");
    }

    public void start() {
        super.start();
        System.out.println("personal start");
    }
}

public class F2_Thread {
    /*
        Defining a thread

        We can define the thread in following two ways:
        1) By extending Thread class
        2) By implementing Runnable interface
     */
    /*
    CASE 1:
        Thread Scheduler (First come first server, round-robin, the shortest job first)

            It is the part of jvm. It is responsible schedule threads,i.e. if the multiple thread are waiting to get
            chance of execution, then in which order threads will be executed is decided by thread scheduler.
            We can't expect exact algorithm followed by thread scheduler, it varies from jvm to jvm. Hence, we can't
            expect thread execution order and exact output. Hence, whenever situation comes to multi threading there
            is no guarantee for exact output, but we can provide several possible outputs.
     */
    /*
    CASE 2:
        Difference between t.start() and t.run()

        In the case of t.start(), a new thread will be created which is responsible for the execution of run method
        but in the case of t.run() a new thread won't be created and run() method will be executed just like a normal
        method call by main thread. Hence, it the below program if we replace t.start() with t.run(), then the
        output is child thread 10 times followed by main thread 10 times.

     */
    /*
    CASE 3:
        Importance of thread class start method

        Thread class start method is responsible to register the thread with thread scheduler and all other mandatory
         activities. Hence, without thread class start method there is no chance of starting a new thread in java.
         Due to this thread class start method is considered as heart of multithreading.

        start(){
            1. Register this thread with thread scheduler
            2. perform all other mandatory activities.
            3. Invoke run();
        }
     */
    /*
    CASE 4:
        Overloading of run method

        Overloading of run method is always possible, but thread class start invokes no-arg run method. The other
        overloaded run method explicitly, like a normal method call.

     */
    /*
    CASE 5:
        If we are not overriding run method, then thread class run method will be executed which has empty
        implementation. Hence, we won't get any output.
        It is highly recommended to override run method, otherwise don't go for multithreading concept.

     */
    /*
    CASE 6:
        Overriding of start method:
        If we override start method then our start method will be executed just like a normal method call and new
        thread won't be created.

     */

    /*
    CASE 8: Thread lifeCycle

       new/born state ---->t.start()----->ready/runnable--->if Thread scheduler allocate processor---->running-if run
       ()method completes--->dead

     */
    /*
    CASE 9:
        After starting a thread, if we try to start the thread again, then we'll get runtime exception saying
        IllegalThreadStartException.
     */

    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("main thread");
        }

    }
}

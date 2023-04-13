package com.esewa.internship.gyalbusherpa.esewaIntern.week_3.day11_MultiThreading;

class Display {
    public static synchronized void wish(String name) {
        for (int i = 0; i < 10; i++) {
            System.out.println("Good morning");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
            System.out.println(name);
        }
    }
}

class Tt extends Thread {
    Display d;
    String name;

    public Tt(Display d, String name) {
        this.d = d;
        this.name = name;
    }

    public void run() {
        d.wish(name);
    }
}

public class F8_Synchronization {
    /*
        Synchronize is the modifier applicable for only method and class but not for classes and variable.
        if multiple threads are trying to operate simultaneously on the same java object then there may be chance of
        data inconsistency problem. To overcome this problem we should go for synchronize keyword. If a method or
        block declared as synchronized, then at a time only one thread is allowed to execute that method or block on
        the given object, so the data inconsistency problem will be resolved.

        The main advantage of synchronize keyword is we can resolve data inconsistency problems but the main
        disadvantage of synchronize keyword is it increases waiting time of threads and creates performance problems.
        Hence, if there is no specific requirement then it is not recommended to use synchronize keyword.
     */

    /*
        Internally synchronization concept is used by implementing lock. every object in java has a unique lock.
        Whenever we are using synchronize keyword then only lock concept will come into the picture.
        if a thread wants to execute synchronize on the given object, first it has to get lock of that object. Once
        the thread is locked then it is allowed to execute any synchronize method on that object. Once method
        execution completes, automatically thread releases the lock.
        Acquiring and releasing lock is internally taken care by jvm and programmer is not responsible for this
        activity.
     */

    /*
        Class level lock:
        Every class in java has a unique lock which is nothing but class level lock.
        If a thread wants to execute a static synchronize method then thread require class level lock. Once thread
        got class level lock then it is allowed to execute any static synchronize method of that class.
        Once a method execution completes, automatically thread releases lock.


        while executing static sync method, the remaining threads are not allowed to execute any static sync method of
         that class simultaneously, but remaining threads are allowed to execute the following methods simultaneously:
        1) normal static method
        2) sync instance method
        3) normal instance method
     */
    public static void main(String[] args) {
        Display d = new Display();
        Display d2 = new Display();
        Tt t = new Tt(d, "don");
        Tt t2 = new Tt(d, "hero");
        Tt t3 = new Tt(d2, "son");
        Tt t4 = new Tt(d2, "zero");
        t.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

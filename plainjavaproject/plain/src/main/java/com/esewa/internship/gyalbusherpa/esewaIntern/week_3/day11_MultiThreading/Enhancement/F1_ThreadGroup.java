package com.esewa.internship.gyalbusherpa.esewaIntern.week_3.day11_MultiThreading.Enhancement;

public class F1_ThreadGroup {
    /*
        Based on functionality we can group threads into a single unit, which is nothing but thread group, i.e.
        thread group contains a group of threads.
        In addition to threads, thread group can also contain sub-thread groups.

        The main advantage of maintaining threads in the form of thread group is we can perform common operations
        very easily.

        Every thread in java belong to some group, main thread belongs to main group.
        Every thread group in java is the child group of system group either directly or indirectly, Hence, system
        group acts as root for all thread groups in java.

        System group contains several system level threads like:
        - finalizer (garbage collector)
        - reference handler
        - signal dispatcher
        - attach listener. etc.

        thread group is a java class present in java.land package, and it is the direct child class of Object.

        Constructors:
        1) ThreadGroup g = new ThreadGroup(String gName);
        2) ThreadGroup g = new ThreadGroup(ThreadGroup parentGroup, String gName);


     */
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getThreadGroup().getName());
        System.out.println(Thread.currentThread().getThreadGroup().getParent().getName());

        ThreadGroup g1 = new ThreadGroup("First");
        System.out.println(g1.getParent().getName()); // main

        ThreadGroup g2 = new ThreadGroup(g1,"First");
        System.out.println(g2.getParent().getName()); // First

    }
}

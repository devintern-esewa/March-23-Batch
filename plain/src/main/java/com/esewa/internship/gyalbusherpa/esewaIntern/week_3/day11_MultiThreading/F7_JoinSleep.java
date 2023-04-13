package com.esewa.internship.gyalbusherpa.esewaIntern.week_3.day11_MultiThreading;

class T extends Thread{
    public void run(){
        for (int i = 0; i < 10000; i++) {
            System.out.println("I am lazy Thread - " + i);
        }
        System.out.println("I am entering into sleeping state");
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e){
            System.out.println("I got interrupted");
        }
    }

    /*
        Properties          yield()             join()              sleep()

        Purpose             for pausing         for waiting         don't want to perform anything for some interval of time
        Is it overloaded    no                  yes                 yes
        Is it final         no                  yes                 no
        Does it throw IE?   no                  yes                 yes
        Is it native        yes                 no                  sleep(long ms) -> native, other one is not

     */
}
public class F7_JoinSleep {
    /*
        Waiting of child thread until main thread

        public static native void sleep(long ms)
        public static native void sleep(long ms, int ns)
     */
    public static void main(String[] args) {
        T t = new T();
        t.start();
        t.interrupt();
        System.out.println("End of main thread");
    }
}

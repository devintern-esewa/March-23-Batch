package com.esewa.internship.gyalbusherpa.esewaIntern.week_3.day11_MultiThreading;

class MThread extends Thread{
    public void run(){
        for (int i = 0; i < 10; i++) {
//            System.out.println("Child Thread");
//            Thread.yield();

            System.out.println("C thread");
            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e){

            }
        }
    }
}
public class F6_YieldJoin {
    /*
        We can prevent a thread execution by using the following methods:
        1) yield
        2) join
        3) sleep

        1) Yield:
         Yield method causes to pass current executing thread to give the chance for waiting threads of same priority.
         If there is no waiting thread or all waiting thread have low priority, then same thread can continue its
         execution.

         If multiple threads are waiting with the same priority then which waiting thread will get the chance, we
         can't expect, it depends on thread scheduler.
         The thread which is yielded, when it will get the chance again, it depends on thread scheduler, and we can't
         expect exactly.

         public static native void yield();

         Some platforms won't provide proper support for yield method.

        Join:
         If a thread wants to wait until completing some other thread, then we should go for join method.
         For example, if a thread t1 wants to wait until completing t2, then t1 has to call t2.join().
         If t1 executes t2.join(), then immediately t1 will enter waiting state until t2 completes.
         Once t2 completes then t1 can continue its execution.

         public final void join()
         public final void join(long ms)
         public final void join(long ms, int ns)


     */
    public static void main(String[] args) throws InterruptedException {

        MThread t = new MThread();
        t.start();
        t.join();
        for (int i = 0; i < 10; i++) {
            System.out.println("main thread");
        }

    }
}

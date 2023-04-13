package com.esewa.internship.gyalbusherpa.esewaIntern.week_3.day11_MultiThreading.Enhancement;

public class F2_Concurrent {
    /*
        The problems with traditional synchronize keyword we are not having any flexibility to try for the lock
        without waiting.
        There was no way to specify max waiting time for a thread to get lock so that thread will wait until getting
        the lock which may create performance problems, which may cause deadlock. If a thread releases a lock then
        which waiting thread will get the lock we are having control on this.
        There is no api to list out all waiting thread for the lock.

        To overcome problems in synchronize keyword, java.util.concurrent.locks was introduced in java 1.5v
        It also provides several enhancement to the programmer to provide more control on concurrency.

        Lock interface:
        lock object is similar to implicit lock acquired by a thread to execute a sync method or sync block.
        lock implementation provide more extensive than traditional implicit locks.

        important methods of lock interface:
            1) void lock();
                we can use this method to acquire a lock. If lock is already available the immediately current thread
                 will get the lock. If it is not available then it will wait until getting lock. It is exact behavior
                  of traditional sync keyword.

            2) boolean tryLock();
                if(l.tryLock){
                    perform safe operation
                }
                else{
                    perform alternative operation
                }
                to acquire the lock without waiting, if the lock is available then the thread acquire the lock and
                returns true. If the lock is not available, then this method returns false and can continue its
                execution without waiting. In this case, thread never be entered into waiting state.

            3) boolean tryLock(long time, TimeUnit unit)
                if lock is available then thread will get lock and continue its execution. If the lock is not
                available, then the thread will wait until specified amount of time, still if the lock is not
                available then thread can continue its execution.

                if(l.tryLock(1000,TimeUnit.MILLISECONDS)){
                }

                enum TimeUnit{
                    NANOSECONDS,
                    MICROSECONDS,
                    MILLISECONDS,
                    SECONDS,
                    MINUTES,
                    HOURS,
                    DAY
                    }

            4) void lockInterruptibly()
                Acquires the lock if it is available and returns immediately. If the lock is not available then it
                will wait. While waiting, if the thread is interrupted, then thread won't get the lock.

            5) void unlock()
                To release the lock.
                To call this method compulsory, current thread should be owner of the lock, otherwise we'll get
                runtime exception saying IllegalMonitorStateException.

        Implementation class is ReentrantLock(C):
     */



}

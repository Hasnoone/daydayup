package org.example.day1102.threaddemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private ReentrantLock lock = new ReentrantLock();

    private int count;

    public void increase() {
        synchronized (this) {
            count++;
        }
    }


    private void threadSafeIncrease() {
        try {
            if (lock.tryLock(1, TimeUnit.MILLISECONDS)) {
                lock.lock();
                try {
                    count++;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}

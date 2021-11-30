package org.example.day1130;

public class BThread extends Thread {

    public BThread() {
        super("[BThread] Thread");
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName+" start ···");
        for (int i = 0; i < 5; i++) {
            System.out.println(threadName + " loop at " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(threadName+" end ···");
    }
}

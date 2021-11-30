package org.example.day1130;

public class AThread extends Thread {


    BThread bThread;


    public AThread(BThread bThread) {
        super("[AThread] Thread");
        this.bThread = bThread;

    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName+" start ···");
            try {
                bThread.join();
                System.out.println(threadName+" end ···");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}

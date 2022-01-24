package org.example.countdownlatchStudy;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

    private CountDownLatch startSingle;
    private CountDownLatch endSingle;


    public Worker(CountDownLatch startSingle, CountDownLatch endSingle) {
        this.startSingle = startSingle;
        this.endSingle = endSingle;
    }



    @Override
    public void run() {
        try {
            startSingle.await();
            System.out.println("==================_+_++++++++++++");
            endSingle.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

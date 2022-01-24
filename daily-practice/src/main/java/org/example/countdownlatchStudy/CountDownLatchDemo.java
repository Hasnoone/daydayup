package org.example.countdownlatchStudy;

import java.util.concurrent.CountDownLatch;


/**
 * 让一个或者多个线程等待一系列的操作全部完成
 */
public class CountDownLatchDemo {


    public static void main(String[] args) {
        int count = 10;

        CountDownLatch startSingle = new CountDownLatch(1);
        CountDownLatch endSingle = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(new Worker(startSingle, endSingle)).start();
            startSingle.countDown();
            System.out.println("+++++++++++++++++++++++++++++");
            endSingle.countDown();
        }


    }


}

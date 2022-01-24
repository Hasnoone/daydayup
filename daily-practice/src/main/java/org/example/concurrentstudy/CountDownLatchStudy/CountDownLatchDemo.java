package org.example.concurrentstudy.CountDownLatchStudy;


import java.util.Random;
import java.util.concurrent.CountDownLatch;


/**
 * CountDownLatch 理解为计数器
 * 比如说一个主线程，要等5个线程执行完毕后才能够进行。
 *
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new MyThread("学生" + i + "交卷了", countDownLatch).start();
        }
        countDownLatch.await();
        System.out.println("所有学生交卷结束");
    }

    static class MyThread extends Thread{
        private CountDownLatch downLatch;
        private Random random = new Random();

        public MyThread(String name, CountDownLatch downLatch) {
            super(name);
            this.downLatch = downLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"运行结束");
            downLatch.countDown();
        }
    }
}

package org.example.concurrentstudy.CyclicBarrierStudy;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        int count = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < count; i++) {
            new MyThread("学生" + i, cyclicBarrier).start();
        }
    }









    static class MyThread extends Thread{
        private CyclicBarrier barrier;
        private Random random = new Random();

        public MyThread(String name, CyclicBarrier cyclicBarrier) {
            super(name);
            this.barrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
            Thread.sleep(random.nextInt(2000));
            System.out.println(Thread.currentThread().getName() + " - 已经到达公司");
            barrier.await();
            Thread.sleep(random.nextInt(2000));
            System.out.println(Thread.currentThread().getName() + " - 已经笔试结束");
            barrier.await();
            Thread.sleep(random.nextInt(2000));
            System.out.println(Thread.currentThread().getName() + " - 已经⾯试结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        }
    }
}

package org.example.day1130;

public class TestThreadJoinDemo {


    public static void main(String[] args) {

        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start.");
        BThread bt = new BThread();

        AThread aT = new AThread(bt);

        try {
            bt.start();
            Thread.sleep(1000);
            aT.start();
            aT.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadName + " end.");


    }


}

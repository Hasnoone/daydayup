package org.example.day1201;

public class DeadLockDemo {

    public static void main(String[] args) {
        deadLock();
    }

    private static void deadLock() {

        Object obj1 = new Object();
        Object obj2 = new Object();

        new Thread(() -> {
            synchronized (obj1) {
                System.out.println(Thread.currentThread().getName()+"====获取锁1");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2) {
                    System.out.println(Thread.currentThread().getName() + "====获取锁2");

                }
            }

        }).start();


        new Thread(() -> {
            synchronized (obj2) {
                System.out.println(Thread.currentThread().getName()+"====获取锁2");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1) {
                    System.out.println(Thread.currentThread().getName() + "====获取锁1");

                }
            }

        }).start();





    }

}

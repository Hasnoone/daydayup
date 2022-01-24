package org.example.concurrentstudy.semaphorestudy;

import java.util.Random;
import java.util.concurrent.Semaphore;


/**
 * semaphore 计数信号量。
 * 就是控制数量的。可以当成一个池化的概念，表示同时允许执行线程的数量。
 * 每一个acquire方法都回阻塞，直到获得一个许可
 * 每一个release方法都回释放或者说添加一个许可
 *
 * 主要使用的方法
 * public Semaphore(int permits) {
 *         sync = new NonfairSync(permits);
 *     }
 *
 *     Semaphore(int permits, boolean fair)
 *
 * acquire() Acquires a permit from this semaphore, blocking until one is
 *       available, or the thread
 *  release ：Releases a permit, returning it to the semaphore.
 */
public class SemaphoreDemo extends Thread {


    private final Semaphore semaphore ;
    private final Random random = new Random();

    public SemaphoreDemo(String name, Semaphore semaphore) {
        super(name);
        this.semaphore = semaphore;
    }


    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+"占座成功");
            Thread.sleep(random.nextInt(1000));
            System.out.println(Thread.currentThread().getName()+"离开座位");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
    }


    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i < 5; i++) {
            new SemaphoreDemo("学生" + i, semaphore).start();
        }
    }
}

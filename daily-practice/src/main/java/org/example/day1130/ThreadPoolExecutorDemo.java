package org.example.day1130;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 10,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(2), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("自定义拒绝策略！");
            }
        });
        for (int i = 0; i < 6; i++) {
            threadPoolExecutor.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + "=====" + j);
                }
            });
        }
        threadPoolExecutor.shutdown();
    }
}

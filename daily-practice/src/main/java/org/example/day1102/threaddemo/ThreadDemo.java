package org.example.day1102.threaddemo;

import java.util.concurrent.*;

public class ThreadDemo {


    public static void main(String[] args) {
        //创建一个线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(ThreadPoolConfig.corePoolSize,
                ThreadPoolConfig.maximumPoolSize, ThreadPoolConfig.keepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(ThreadPoolConfig.queueSize));

        CompletableFuture<Void> one = CompletableFuture.runAsync(new Task(5), threadPoolExecutor);
        CompletableFuture<Void> two = CompletableFuture.runAsync(new Task(4), threadPoolExecutor);
        CompletableFuture<Void> three = CompletableFuture.runAsync(new Task(3), threadPoolExecutor);

        CompletableFuture.allOf(one, two, three);
    }


}

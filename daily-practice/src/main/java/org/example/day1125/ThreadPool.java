package org.example.day1125;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class ThreadPool {


    private static final int DEFAULT_WORKQUEUE_SIZE = 5;

    private BlockingDeque<Runnable> workQueue;

    private List<WorkThread> workThreads = new ArrayList<>();


    public ThreadPool(int poolSize, BlockingDeque<Runnable> workQueue) {
        this.workQueue = workQueue;
        IntStream.range(0, poolSize).forEach(i -> {
            WorkThread workThread = new WorkThread();
            workThread.start();
            workThreads.add(workThread);
        });
    }

    public ThreadPool(int poolSize) {
        this(poolSize, new LinkedBlockingDeque<>(DEFAULT_WORKQUEUE_SIZE));
    }


    public void execute(Runnable task) {
        try {
            workQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    class WorkThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable take = workQueue.take();
                    take.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

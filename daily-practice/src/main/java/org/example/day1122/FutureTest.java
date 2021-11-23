package org.example.day1122;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.concurrent.*;

public class FutureTest {

    public static void main(String[] args) {

        final MedalService medalService = new MedalService();
        final UserInfoService infoService = new UserInfoService();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();

        FutureTask<String> userInfoTask = new FutureTask<>(() -> infoService.getUserInfo());

        executorService.submit(userInfoTask);

        FutureTask<String> medalTask = new FutureTask<>(() -> medalService.getMedalInfo());
        executorService.submit(medalTask);


        try {
            String userName = userInfoTask.get();//Future.get() 就是阻塞调用
            String medal = medalTask.get();
            System.out.println(userName);
            System.out.println(medal);
            System.out.println("总共用时" + (System.currentTimeMillis() - start) + "ms");
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testFuture() {
        ExecutorService executorService = Executors. newFixedThreadPool(2);
        ListenableFutureTask<String> futureTask = ListenableFutureTask.create(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);//执行了这段代码后，线程就会结束，不会再继续执行
                return "执行结果";
            }
        });
        executorService.submit(futureTask);
        Futures.addCallback(futureTask, new FutureCallback<String>() {
            public void onSuccess(@Nullable String s) {
                System.out.println(s);
                System.out.println("成功");
            }
            public void onFailure(Throwable throwable) {
                System.out.println("失败");
            }
        },Executors.newSingleThreadScheduledExecutor());
    }


    /**
     * 使用countdownlatch的方法
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void testCountDownLatch() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        executorService.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("获取航班号");
            countDownLatch.countDown();
        });
        executorService.submit(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("定酒店");
            countDownLatch.countDown();
        });
        countDownLatch.await();
        Future<String> carOrder = executorService.submit(() -> "完成了");
        System.out.println(carOrder.get());
    }
}

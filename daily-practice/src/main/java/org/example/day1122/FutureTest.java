package org.example.day1122;

import com.google.common.util.concurrent.*;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;

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
    public void testFuture() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

        ListenableFuture<String> futureTask = listeningExecutorService.submit(() -> {
            Thread.sleep(100);//执行了这段代码后，线程就会结束，不会再继续执行
            return "执行结果";
        });
        Futures.addCallback(futureTask, new FutureCallback<String>() {
            public void onSuccess(@Nullable String s) {
                System.out.println(s);
                System.out.println("成功");
            }
            public void onFailure(Throwable throwable) {
                System.out.println("失败");
            }
        },executorService);


//        Thread.sleep(5000);
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


    /**
     * 用CompletableFuture改造上面的例子
     */
    @Test
    public void  test() throws ExecutionException, InterruptedException {


        CompletableFuture<Void> first = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("获取航班号");
        });


        CompletableFuture<Void> second = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("定酒店");
        });

        CompletableFuture<String> third = first.thenCombine(second, new BiFunction<Void, Void, String>() {
            @Override
            public String apply(Void aVoid, Void aVoid2) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "完成";
            }
        });

        System.out.println(third.get());


    }

}

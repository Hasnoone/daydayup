package org.example.day1122;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.function.*;


/**
 * https://mp.weixin.qq.com/s?src=11&timestamp=1637563321&ver=3451&signature=M9BqRwcFn6pMM8urvqe8vg8vZsJLviUKOAwLqyO-PBbtIKA0gTTRrkQigy-27iyg1YHb5p7sz0EA9*rbXAeql1DApaALP6OAb05UD6p-7rsFBAxmot*ZzeSnq2s0b65N&new=1
 */
public class CompletableFutureDemo {


    /**
     *
     *supplyAsync执行CompletableFuture任务，支持返回值
     *  1.  //使用默认内置线程池ForkJoinPool.commonPool()，根据supplier构建执行任务
     *      public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
     *      //自定义线程，根据supplier构建执行任务
     *      public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
     * runAsync执行CompletableFuture任务，没有返回值。
     *      //使用默认内置线程池ForkJoinPool.commonPool()，根据runnable构建执行任务
     *      public static CompletableFuture<Void> runAsync(Runnable runnable)
     *      //自定义线程，根据runnable构建执行任务
     *      public static CompletableFuture<Void> runAsync(Runnable runnable,  Executor executor)
     *
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    @Test
    public void testSupply() throws InterruptedException, ExecutionException, TimeoutException {
        MedalService medalService = new MedalService();
        UserInfoService infoService = new UserInfoService();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();
        CompletableFuture<String> medalInfoFuture = CompletableFuture.supplyAsync(() -> medalService.getMedalInfo());
        CompletableFuture<String> userInfoFuture = CompletableFuture.supplyAsync(() -> infoService.getUserInfo());
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> medalService.getMedalInfo());
        Void aVoid = voidCompletableFuture.get();
        String medal = medalInfoFuture.get(2, TimeUnit.MILLISECONDS);
        String username = userInfoFuture.get();
    }


    /**
     * CompletableFuture的thenRun方法，通俗点讲就是，做完第一个任务后，再做第二个任务。某个任务执行完成后，执行回调方法；但是前后两个任务没有参数传递，第二个任务也没有返回值
     */
    @Test
    public void testThenRunThenRunAsyn() throws ExecutionException, InterruptedException {

        CompletableFuture<String> firstTask = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行第一个任务");
            return "第一个任务";
        });
        CompletableFuture<Void> secondTask = firstTask.thenRun(() -> System.out.println("执行第二个人任务"));
        System.out.println(secondTask.get());
    }


    /**
     * CompletableFuture的thenAccept方法表示，第一个任务执行完成后，执行第二个回调方法任务，会将该任务的执行结果，作为入参，传递到回调方法中，但是回调方法是没有返回值的。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testThenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一次执行任务");
            return "第一次执行任务";
        });
        CompletableFuture<Void> thenA = first.thenAccept(a -> System.out.println("拿到supplyAsync的执行结果:"+a));
        System.out.println(first.get());
        System.out.println(thenA.get());
    }


    /**
     * CompletableFuture的thenApply方法表示，第一个任务执行完成后，执行第二个回调方法任务，会将该任务的执行结果，作为入参，传递到回调方法中，并且回调方法是有返回值的。
     */

    @Test
    public void testThenApply() throws ExecutionException, InterruptedException {


        CompletableFuture<String> firstTaskResult = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一次执行任务");
            return "第一次执行任务的结果";
        });


        CompletableFuture<String> secondTaskResult = firstTaskResult.thenApply(s -> {
            System.out.println("第一次任务的执行结果：" + s);
            return "第二次执行任务结果";
        });

        System.out.println(firstTaskResult.get());
        System.out.println(secondTaskResult.get());
    }


    /**
     * CompletableFuture的exceptionally方法表示，某个任务执行异常时，执行的回调方法;并且有抛出异常作为参数，传递到回调方法。
     */
    @Test
    public void testExceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture<String> firstTaskResult = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException();
        });
        CompletableFuture<String> exceptionally = firstTaskResult.exceptionally(throwable -> {
            throwable.printStackTrace();
            return "你程序异常了";
        });
        System.out.println(exceptionally.get());
    }


    /**
     * CompletableFuture的whenComplete方法表示，某个任务执行完成后，执行的回调方法，无返回值；并且whenComplete方法返回的CompletableFuture的result是上个任务的结果。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testWhenComplete() throws ExecutionException, InterruptedException {
        CompletableFuture<String> firstTask = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "第一个任务的执行结果";
        });

        CompletableFuture<String> secondTask = firstTask.whenComplete((a, throwable) -> {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            System.out.println("上个任务执行完啦，还把" + a + "传过来");
        });


        System.out.println(secondTask.get());

    }


    /**
     * CompletableFuture的handle方法表示，某个任务执行完成后，执行回调方法，并且是有返回值的;并且handle方法返回的CompletableFuture的result是回调方法执行的结果。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testHandle() throws ExecutionException, InterruptedException {
        CompletableFuture<String> firstTask = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "第一个任务的执行结果";
        });


        CompletableFuture<String> second = firstTask.handle(new BiFunction<String, Throwable, String>() {
            @Override
            public String apply(String s, Throwable throwable) {
                System.out.println(s);
                return "第er个任务的执行结果";

            }
        });
        System.out.println(second.get());
    }
}

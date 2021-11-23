package org.example.day1122;

import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) {

        MedalService medalService = new MedalService();
        UserInfoService infoService = new UserInfoService();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();

        FutureTask<String> userInfoTask = new FutureTask<>(new Callable<String>() {
            public String call() throws Exception {
                return infoService.getUserInfo();
            }
        });

        executorService.submit(userInfoTask);

        FutureTask<String> medalTask = new FutureTask<>(new Callable<String>() {
            public String call() throws Exception {
                return medalService.getMedalInfo();
            }
        });
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
}

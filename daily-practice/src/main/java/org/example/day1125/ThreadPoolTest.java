package org.example.day1125;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(10);


        IntStream.range(0, 10).forEach(i -> threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + "--->> Hello ThreadPool")));


    }

}

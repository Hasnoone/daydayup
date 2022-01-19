package org.example;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


/**
 * 创建会话
 */
public class CreateSession implements Watcher {


    //这个类是使主线程等待,主要不让main方法结束.
    private static CountDownLatch countDownLatch = new CountDownLatch(1);


    public void process(WatchedEvent watchedEvent) {

        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {

            countDownLatch.countDown();


        }

    }


    public static void main(String[] args) throws IOException, InterruptedException {

        ZooKeeper zooKeeper = new ZooKeeper("192.168.81.128:2181",5000, new CreateSession());
        System.out.println(zooKeeper.getState());
        countDownLatch.await();

        System.out.println("========Client connected to zookeeper=========");



    }
}

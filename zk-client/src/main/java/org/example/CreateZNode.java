package org.example;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;


/**
 * 创建会话
 */
public class CreateZNode implements Watcher {

    private static ZooKeeper zooKeeper;

    //这个类是使主线程等待,主要不让main方法结束.
    private static CountDownLatch countDownLatch = new CountDownLatch(1);


    public void process(WatchedEvent watchedEvent) {




        try {
            createZNode();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {

        zooKeeper = new ZooKeeper("192.168.81.128:2181", 5000, new CreateZNode());

        countDownLatch.await();


    }



    private void createZNode() throws KeeperException, InterruptedException, UnsupportedEncodingException {

        String node1 = zooKeeper.create("/lg_persistence", "持久节点".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        String node2 =zooKeeper.create("/lg_persistence_s", "持久顺序节点".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        String node3 =zooKeeper.create("/lg_ephemer", "临时节点内容".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);


        System.out.println(node1);
        System.out.println(node2);
        System.out.println(node3);


    }
}

package org.example.day1130.shouxieHash;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 没有虚拟节点的hash算法
 */
public class ConsistentHashNoVirtual {

    public static void main(String[] args) {
        //初始化,把服务器映射到节点上
        String[] servers = {"10.78.12.3", "113.25.63.1", "126.12.3.8", "126.12.124.7", "126.12.124.9"};
        SortedMap<Integer, String> hashServerMap = new TreeMap<>();
        for (String server : servers) {
            int index = Math.abs(server.hashCode());
            //存储server和节点的对应关系
            hashServerMap.put(index, server);
        }
        //针对客户端,求出hash值
        String[] clients = {"10.128.12.3", "113.124.63.1", "126.102.3.8"};
        for (String client : clients) {
            int clientHash = Math.abs(client.hashCode());
            //根据客户端的ip去找哪一个服务器能够处理
            //针对客户端找到能够处理的服务器,顺时针查找

            SortedMap<Integer, String> integerStringSortedMap = hashServerMap.tailMap(clientHash);
            if (integerStringSortedMap.isEmpty()) {
                //取hash环上的第一台服务器
                Integer index = hashServerMap.firstKey();
                System.out.println("客户端：" + client + "被路由到编号为：" + hashServerMap.get(index) + "的路由器");

            }else {
                //否则就取顺时针第一个服务器
                Integer index = integerStringSortedMap.firstKey();
                System.out.println("客户端：" + client + "被路由到编号为：" + integerStringSortedMap.get(index) + "的路由器");

            }
        }
    }



}

package org.example.day1130.shouxieHash;


public class GeneralHash {


    public static void main(String[] args) {
        //定义客户端ip
        String[] clients = {"10.78.12.3", "113.25.63.1", "126.12.3.8"};
        //定义服务端
        int serverCount = 3;//编号对应0,1,2
        //路由
        //hash(ip)%nodeCounts=index
        for (String client : clients) {
            int hashCode = Math.abs(client.hashCode());
            int index = hashCode % serverCount;
            System.out.println("客户端：" + client + "被路由到编号为：" + index + "的路由器");
        }
    }
}

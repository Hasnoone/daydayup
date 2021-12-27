package org.example.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NIOServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        System.out.println("服务端启动成功...");
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (null == socketChannel) {//如果是阻塞io的话，这里就会阻塞在这里。
                System.out.println("还没有客户端进行连接");
                Thread.sleep(1000);
                continue;
            }
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int read = socketChannel.read(byteBuffer);
            //read 如果大于1，表示读取到的有效字节数，如果是0，表示没有读到字节数， -1表示末尾
//            if (-1 != read) {
                System.out.println("客户端消息："+new String(byteBuffer.array(),0,
                        read, StandardCharsets.UTF_8));
//            }
            socketChannel.write(ByteBuffer.wrap("没钱".getBytes(StandardCharsets.UTF_8)));
            socketChannel.close();
        }

    }



}

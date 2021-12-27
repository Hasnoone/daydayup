package org.example.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 *
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(9999));

        String content = "有钱吗";
        socketChannel.write(ByteBuffer.wrap(content.getBytes(StandardCharsets.UTF_8)));

        ByteBuffer allocate = ByteBuffer.allocate(1024);
        int read = socketChannel.read(allocate);
        System.out.println("服务端返回的数据是："+new String(allocate.array(),0,
                read, StandardCharsets.UTF_8));

        socketChannel.close();

    }





}

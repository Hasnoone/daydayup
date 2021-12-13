package org.example.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerDemo {


    public static void main(String[] args) throws IOException {
        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(999);
        while (true) {
            final Socket accept = serverSocket.accept();
            executorService.execute(new Runnable() {
                public void run() {
                    handle(accept);
                }
            });
        }

    }

    private static void handle(Socket socket) {
        System.out.println("线程ID" + Thread.currentThread().getId() + "线程名：" + Thread.currentThread().getName());
        try {
            //从链接中获取输入流
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int read = inputStream.read(bytes);
            System.out.println("客户端：" + new String(bytes, 0, read));
            //获取输出流并回话
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("没钱".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}

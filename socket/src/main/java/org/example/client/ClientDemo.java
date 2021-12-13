package org.example.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientDemo {


    public static void main(String[] args) throws IOException {
        while (true) {


            Socket socket = new Socket("localhost", 999);
            OutputStream outputStream = socket.getOutputStream();
//            outputStream.write();
            System.out.println("请输入");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            outputStream.write(s.getBytes());
            //从连接中去除输入流 并接收回话

            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int read = inputStream.read(bytes);
            System.out.println("老板说：" + new String(bytes, 0, read));
            socket.close();

        }




    }


}

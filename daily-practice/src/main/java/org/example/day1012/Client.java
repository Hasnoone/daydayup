package org.example.day1012;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final int port = 9983;
    private static final String host = "localhost";


    public static void main(String[] args) {
        Client client = new Client();
        client.init();
    }

    private void init() {
        System.out.println("Client socket starting...");
        //1.创建一个socket
        while (true) {
            Socket socket = null;
            try {
                socket = new Socket(host, port);
                InputStream inputStream = socket.getInputStream();
                //读取服务端消息
                BufferedReader inStream = new BufferedReader(new InputStreamReader(inputStream));
                //向服务器发送消息
                PrintStream outStream = new PrintStream(socket.getOutputStream());
                System.out.println("请输入：");
                String consoleInpput = new BufferedReader(new InputStreamReader(System.in)).readLine();
                outStream.println(consoleInpput);
                String s1 = inStream.readLine();
                System.out.println("从服务端发送过来的消息：" + s1);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        //2.设置host和port，并且链接


        //3.生成输出流

    }


}

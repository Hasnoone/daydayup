package org.example.day1009;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClassLoaderTest {

    public static void main(String[] args) throws IOException {
//        String property = System.getProperty("sun.boot.class.path");
        String property = System.getProperty("java.ext.dirs");
        String[] split = property.split(";");
        for (int i = 0; i < split.length; i++) {

            System.out.println(split[i]);
        }


        ServerSocket serverSocket = new ServerSocket();
        Socket accept = serverSocket.accept();
        InputStream inputStream = accept.getInputStream();







    }


}

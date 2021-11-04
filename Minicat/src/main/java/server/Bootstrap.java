package server;

import pojo.Request;
import util.HttpProtocolUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Bootstrap {


    private int port = 8080;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * 进行端口的监听
     */
    private void start() {
        //1.0 版本 浏览器请求http://localhost:8080,返回一个固定的字符串 “Hello Minicat”

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("开始监听");
            //这是1.0
/*            while (true) {
                Socket socket = serverSocket.accept();
                //首先这里是服务端，是向客户端输出
                OutputStream outputStream = socket.getOutputStream();
                String data = "Hello Minicat";
                String responseContent = HttpProtocolUtil.getHttpHeader200(data.getBytes().length) + data;
                outputStream.write(responseContent.getBytes());
                socket.close();
            }*/

            /**
             * Minicat2.0的需求
             * 1.封装request和response对象，返回静态资源文件；
             *
             */

            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();

                Request request = new Request(inputStream);


            }



        } catch (IOException e) {
            e.printStackTrace();
        }




    }


    /**
     * Minicat 的启动入口
     *
     * @param args
     */
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.start();
    }


}

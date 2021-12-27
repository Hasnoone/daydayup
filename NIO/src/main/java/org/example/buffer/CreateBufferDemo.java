package org.example.buffer;

import java.nio.ByteBuffer;

public class CreateBufferDemo {


    public static void main(String[] args) {
        //创建置顶长度的缓冲区 byteBuffer为例
        ByteBuffer allocate = ByteBuffer.allocate(5);
//        for (int i = 0; i < 5; i++) {
//            System.out.println(allocate.get());//从缓冲区拿去数据
//        }
//        System.out.println(allocate.get());//这时候就会报错、



        //创建一个有内容的缓冲区
        byte[] bytes = new byte[1024];
        String content = "hello";
        ByteBuffer wrap = ByteBuffer.wrap(content.getBytes());
        for (int i = 0; i < content.length(); i++) {
            System.out.println(wrap.get());
        }




    }


}

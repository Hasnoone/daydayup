package org.example.buffer;

import com.sun.org.apache.bcel.internal.generic.ALOAD;

import java.nio.ByteBuffer;

/**
 * 向缓冲区添加数据
 */
public class PutBufferDemo {


    public static void main(String[] args) {
        //1.创建指定长度缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(10);
//        System.out.println(allocate.position());//当前索引位置
//        System.out.println(allocate.limit());//最多能操作到哪个索引
//        System.out.println(allocate.capacity());//缓冲容量
//        System.out.println(allocate.remaining());//还剩多少个可以操作

        //修改当前索引位置
//        allocate.position(1);
//        System.out.println(allocate.position());//当前索引位置

        //添加一个字节
        allocate.put((byte) 678);
        System.out.println(allocate.get());
        System.out.println(allocate.position());//当前索引位置
        System.out.println(allocate.limit());//最多能操作到哪个索引
        System.out.println(allocate.capacity());//缓冲容量
        System.out.println(allocate.remaining());//还剩多少个可以操作



    }


}

package pojo;


import util.HttpProtocolUtil;
import util.StaticResourceUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 需要依赖 outputStream
 *
 * 该对象需要提供核心方法,输出html
 */
public class Response {


    private OutputStream outputStream;


    /**
     * 输出指定字符串
     */
    public void output(String content) throws IOException {
        outputStream.write(content.getBytes());
    }


    /**
     *
     * @param path 根据url 获取到静态资源的绝对路径 进一步根据绝对路径读取静态资源文件 并最终通过输出流输出
     */
    public void getOutPutHtml(String path) throws IOException {
        //获取静态资源的绝对路径
        String absoluteResourcePath = StaticResourceUtil.absolutePath(path);

        File file = new File(absoluteResourcePath);

        if (file.exists()) {
            //输出静态资源文件

            StaticResourceUtil.getOutputStream(new FileInputStream(file), outputStream);

        }else {
            //输出404
            output(HttpProtocolUtil.getHttpHeader404());
        }






    }


    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;





    }



}

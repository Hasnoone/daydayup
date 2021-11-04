package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StaticResourceUtil {

    public static String absolutePath(String path) {
        String absolutePath = StaticResourceUtil.class.getResource("/").getPath();

        return absolutePath.replaceAll("\\\\", "/")+path;
    }


    /**
     * 读取静态资源文件输入流,通过输出流输出
     *
     * @param inputStream
     * @param outputStream
     */
    public static void getOutputStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        int count = 0;
        while (count == 0) {
            count = inputStream.available();
        }
        int resourceSize = count;
        outputStream.write(HttpProtocolUtil.getHttpHeader200(resourceSize).getBytes());
        long written = 0;
        int byteSize = 1024;
        byte[] bytes = new byte[byteSize];
        while (written < resourceSize) {
            if (written + byteSize > resourceSize) {//剩余文件大小不足1024的长度,那就按真实车的长度处理
                byteSize = (int) (resourceSize - written);//剩余文件长度
                bytes = new byte[byteSize];
            }
            inputStream.read(bytes);
            outputStream.write(bytes);
            outputStream.flush();
            written += byteSize;
        }
    }



}

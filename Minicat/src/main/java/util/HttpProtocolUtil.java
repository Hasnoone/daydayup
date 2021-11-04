package util;

/**
 * http 协议工具类 提供响应头信息 只提供200
 */
public class HttpProtocolUtil {


    public static String getHttpHeader200(long contentLength) {
        return "Http/1.1 200 ok \n" +
                "content-type: text/html; charset=UTF-8\n" +
                "content-length: " + contentLength + "\n" +
                "\r\n";
    }



    public static String getHttpHeader404() {
        String str404 = "<h1>404 not found</h1>";
        return "Http/1.1 404 NOT FOUND \n" +
                "content-type: text/html; charset=UTF-8\n" +
                "content-length: " + str404.getBytes().length + "\n" +
                "\r\n"+str404;
    }


}

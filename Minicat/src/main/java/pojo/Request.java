package pojo;


import java.io.IOException;
import java.io.InputStream;

/**
 * 把请求信息封装为request对象（根据Inpurt输入流封装）
 */
public class Request {


    private String method;//GET POST

    private String url;//index.xml

    private InputStream inputStream;//其他属性从输入流中解析

    public Request(InputStream inputStream) throws IOException {
        this.inputStream = inputStream;
        //从输入流获取请求头信息
        //因为此处avaiable可能为0
        int count = 0;
        if (count == 0) {
            count = inputStream.available();
        }
        byte[] bytes = new byte[count];
        inputStream.read(bytes);
        String inputStr = new String(bytes);
        //获取第一行请求头信息
        String firstLineStr = inputStr.split("\\n")[0];
        String[] s = firstLineStr.split(" ");
        this.method = s[0];
        this.url = s[1];

        System.out.println("method=====>"+this.method);
        System.out.println("url=====>"+this.url);


    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}

package server;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import pojo.Request;
import pojo.Response;
import util.HttpProtocolUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Bootstrap {


    private int port = 8080;
    //key->url
    private Map<String, HttpServlet> servletMap = new HashMap<>();

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
        loadServlet();

        //定义一个线程池
        int corePoolSize = 10;
        int maximumPoolSize = 50;
        long keepAliveTime = 100L;
        TimeUnit unit = TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(50);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize
        ,maximumPoolSize,keepAliveTime,unit,workQueue,threadFactory,handler);

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
/*
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                Request request = new Request(inputStream);
                Response response = new Response(socket.getOutputStream());
                response.getOutPutHtml(request.getUrl());
                socket.close();
            }*/


            //加载解析配置文件 调用loadServlet()方法
            //3.0 可以请求动态资源
/*            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                Request request = new Request(inputStream);
                Response response = new Response(socket.getOutputStream());
                //处理静动态资源
                if (null == servletMap.get(request.getUrl())) {
                    //静态资源
                    response.getOutPutHtml(request.getUrl());
                }else {
                    //动态资源
                    HttpServlet httpServlet = servletMap.get(request.getUrl());
                    httpServlet.service(request,response);
                }
                socket.close();
            }*/


            //4.0 多线程版本
            //3.0的版本是一个bio的形式.我们来实现一个多线程版本
/*            while (true) {
                Socket socket = serverSocket.accept();
                RequestProcessor requestProcessor = new RequestProcessor(socket, servletMap);
                requestProcessor.start();
            }*/

            //5.0 线程池版本
            while (true) {
                System.out.println("===多线程改造======");
                Socket socket = serverSocket.accept();
                RequestProcessor requestProcessor = new RequestProcessor(socket, servletMap);
                threadPoolExecutor.execute(requestProcessor);
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 加载解析web.xml文件,初始化servlet 就是解析下面的
     * <servlet>
     *     <servlet-name>myServlet</servlet-name>
     *     <servlet-class>server.MyServlet</servlet-class>
     * </servlet>
     * <servlet-mapping>
     *     <servlet-name>myServlet</servlet-name>
     *     <url-pattern>/</url-pattern>
     * </servlet-mapping>
     */
    private void loadServlet()  {
        //解析类路径下的web.xml文件 ,解析成流的格式
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("web.xml");
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(resourceAsStream);

        Element rootElement = document.getRootElement();
        //1.找到servlet标签
        List<Element> selectNodes = rootElement.selectNodes("//servlet");
        for (int i = 0; i < selectNodes.size(); i++) {
            Element element = selectNodes.get(i);
            Element servletElement = (Element) element.selectSingleNode("servlet-name");
            String servletName = servletElement.getStringValue();
            Element servletClassElement = (Element) element.selectSingleNode("servlet-class");
            String servletClass= servletClassElement.getStringValue();
            //2.解析servlet-mapping
            Element servletMapping = (Element) rootElement.selectSingleNode("/web-app/servlet-mapping[servlet-name='" + servletName + "']");
            String urlPattern = servletMapping.selectSingleNode("url-pattern").getStringValue();
            servletMap.put(urlPattern, (HttpServlet) Class.forName(servletClass).newInstance());
        }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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

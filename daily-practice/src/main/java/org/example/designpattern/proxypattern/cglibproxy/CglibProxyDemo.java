package org.example.designpattern.proxypattern.cglibproxy;


/**
 * 代理模式,一个类代表另一个类.属于结构型.
 *
 */
public class CglibProxyDemo {


    public static void main(String[] args) {
        Subject proxy = (Subject) CglibProxy.getProxy(new Subject());


        proxy.sell();


    }

}

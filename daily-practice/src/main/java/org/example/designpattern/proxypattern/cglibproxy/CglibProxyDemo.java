package org.example.designpattern.proxypattern.cglibproxy;

public class CglibProxyDemo {


    public static void main(String[] args) {
        Subject proxy = (Subject) CglibProxy.getProxy(new Subject());


        proxy.sell();


    }

}

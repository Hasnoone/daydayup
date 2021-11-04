package org.example.designpattern.proxypattern.jdkproxy;

import java.lang.reflect.Proxy;

public class Client {


    public static void main(String[] args) {
        Subject realSubject = new RealSubject();


        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(realSubject);
        Subject proxySubject = (Subject) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Subject.class}, myInvocationHandler);

        proxySubject.sell();

    }

}

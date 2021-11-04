package org.example.designpattern.proxypattern.jdkproxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {


    private Subject realSubject;

    public MyInvocationHandler(Subject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始调用代理");
        if (method.getName().equalsIgnoreCase("sell")) {
            Object invoke = method.invoke(realSubject, args);
            return invoke;
        }
        return null;
    }
}

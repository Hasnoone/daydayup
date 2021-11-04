package org.example.designpattern.proxypattern.jdkproxy;

public class RealSubject implements Subject {

    @Override
    public void sell() {
        System.out.println("卖!");
    }
}

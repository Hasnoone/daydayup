package org.example.designpattern.proxypattern;

public class ProxyPatternDemo {


    public static void main(String[] args) {
        Image proxyImage = new ProxyImage("123.txt");


        proxyImage.disPlay();
        proxyImage.disPlay();


    }


}

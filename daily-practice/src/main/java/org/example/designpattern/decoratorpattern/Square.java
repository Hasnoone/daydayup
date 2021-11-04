package org.example.designpattern.decoratorpattern;

public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("我是正方形!");
    }
}

package org.example.designpattern.factorypattern;

public class TestDemo {

    public static void main(String[] args) {
        Shape circle = ShapeFactory.getShape("circle");
        circle.draw();
    }


}

package org.example.designpattern.factorypattern;

import org.example.designpattern.factorypattern.car.shape.Car;
import org.example.designpattern.factorypattern.car.shape.CarFactory;
import org.example.designpattern.factorypattern.shape.Shape;
import org.example.designpattern.factorypattern.shape.ShapeFactory;

public class TestDemo {

    public static void main(String[] args) {
        Shape circle = ShapeFactory.getShape("circle");
        circle.draw();


        Car car = CarFactory.getShape("benz");
        car.drive();
    }


}

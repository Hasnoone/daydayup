package org.example.designpattern.abstractfactorypattern;

import org.example.designpattern.factorypattern.car.shape.Car;

/**
 * 抽象工厂方法 可以理解为工厂方法的工厂方法
 */
public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {

        AbstractFactory abstractFactory = FactoryProducer.getFactory("car");
        Car bmw = abstractFactory.getCar("bmw");
        bmw.drive();





    }



}

package org.example.designpattern.abstractfactorypattern;

import org.example.designpattern.factorypattern.car.shape.Benz;
import org.example.designpattern.factorypattern.car.shape.Bmw;
import org.example.designpattern.factorypattern.car.shape.Car;
import org.example.designpattern.factorypattern.shape.Circle;
import org.example.designpattern.factorypattern.shape.Shape;
import org.example.designpattern.factorypattern.shape.Square;

public class CarFactory extends AbstractFactory {

    private Car car;

    @Override
    Car getCar(String type) {
        if (type.equalsIgnoreCase("benz")) {
            car = new Bmw();
        } else {
            car = new Benz();
        }
        return car;
    }


    @Override
    Shape getShape(String type) {
        return null;
    }
}

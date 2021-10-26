package org.example.designpattern.abstractfactorypattern;

import org.example.designpattern.factorypattern.car.shape.Car;
import org.example.designpattern.factorypattern.shape.Circle;
import org.example.designpattern.factorypattern.shape.Shape;
import org.example.designpattern.factorypattern.shape.Square;

public class ShapeFactory extends AbstractFactory {

    private Shape shape;

    @Override
    Car getCar(String type) {
        return null;
    }


    @Override
    Shape getShape(String type) {
        if (type.equalsIgnoreCase("circle")) {
            shape = new Circle();
        } else {
            shape = new Square();
        }
        return shape;
    }
}

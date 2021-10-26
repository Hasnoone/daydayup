package org.example.designpattern.abstractfactorypattern;

import org.example.designpattern.factorypattern.car.shape.Car;
import org.example.designpattern.factorypattern.car.shape.CarFactory;
import org.example.designpattern.factorypattern.shape.Shape;
import org.example.designpattern.factorypattern.shape.ShapeFactory;

public abstract class AbstractFactory {
    abstract Car getCar(String type);

    abstract Shape getShape(String type);
}

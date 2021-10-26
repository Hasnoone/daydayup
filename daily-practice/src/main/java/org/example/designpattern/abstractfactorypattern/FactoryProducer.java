package org.example.designpattern.abstractfactorypattern;

public class FactoryProducer {

    public static AbstractFactory getFactory(String type) {
        if (type.equalsIgnoreCase("car")) {
            return new CarFactory();
        } else {
            return new ShapeFactory();
        }
    }

}

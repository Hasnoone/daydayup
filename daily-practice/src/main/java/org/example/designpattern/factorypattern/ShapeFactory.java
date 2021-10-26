package org.example.designpattern.factorypattern;

public class ShapeFactory {

    private static Shape shape;


    public static Shape getShape(String type) {
        if (type.equalsIgnoreCase("circle")) {
            shape = new Circle();
        } else {
            shape = new Square();
        }
        return shape;
    }


}

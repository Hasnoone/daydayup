package org.example.designpattern.factorypattern.shape;

import org.example.designpattern.factorypattern.shape.Circle;
import org.example.designpattern.factorypattern.shape.Shape;
import org.example.designpattern.factorypattern.shape.Square;

/**
 * 工厂模式
 * 属于创建型模式。提供了一种创建对象的方法。隐藏了创建对象的细节和逻辑。并且是使用一个共同的方法
 */
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

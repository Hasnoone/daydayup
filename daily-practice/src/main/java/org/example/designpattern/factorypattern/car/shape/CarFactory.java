package org.example.designpattern.factorypattern.car.shape;

/**
 * 工厂模式
 * 属于创建型模式。提供了一种创建对象的方法。隐藏了创建对象的细节和逻辑。并且是使用一个共同的方法
 */
public class CarFactory {

    private static Car car;


    public static Car getShape(String type) {
        if (type.equalsIgnoreCase("benz")) {
            car = new Bmw();
        } else {
            car = new Benz();
        }
        return car;
    }


}

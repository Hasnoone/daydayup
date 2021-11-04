package org.example.designpattern.decoratorpattern;


/**
 * 创建实现了shape的抽象装饰类
 */
public abstract class ShapeDecorator implements Shape {

    protected Shape decoratorShape;


    public ShapeDecorator(Shape decoratorShape) {
        this.decoratorShape = decoratorShape;
    }


    @Override
    public void draw() {
        decoratorShape.draw();
    }
}

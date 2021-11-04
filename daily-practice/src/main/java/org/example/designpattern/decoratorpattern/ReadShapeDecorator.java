package org.example.designpattern.decoratorpattern;


/**
 * 创建扩展了ShapeDecorator的装饰器类
 */
public class ReadShapeDecorator extends ShapeDecorator {
    public ReadShapeDecorator(Shape decoratorShape) {
        super(decoratorShape);
    }


    @Override
    public void draw() {
        decoratorShape.draw();

    }



    private void setBoard() {
        System.out.println("红色边框");
    }
}

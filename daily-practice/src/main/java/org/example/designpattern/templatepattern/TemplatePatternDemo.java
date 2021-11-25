package org.example.designpattern.templatepattern;

/**
 * 模板设计模式
 * 一个类抽象定义执行他的方法模板，子类按需求重新方法，调用是调用的抽象方法中的。
 * 术语行为模式
 */
public class TemplatePatternDemo {


    public static void main(String[] args) {

        Game game = new Cricket();

        game.play();

        game = new FootBall();

        game.play();

    }
}

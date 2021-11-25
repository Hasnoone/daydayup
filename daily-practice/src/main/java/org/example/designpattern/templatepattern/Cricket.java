package org.example.designpattern.templatepattern;

public class Cricket extends Game{

    @Override
    protected void initialize() {
        System.out.println("Cricket initialize···");
    }

    @Override
    protected void startPlay() {
        System.out.println("Cricket startPlay···");
    }

    @Override
    protected void endPlay() {
        System.out.println("Cricket startPlay···");
    }
}

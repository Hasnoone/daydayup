package org.example.designpattern.callbackpattern;

public class SimpleTask extends Task {
    protected void execute() {
        System.out.println("Do some tasks before the callback method.");
    }
}

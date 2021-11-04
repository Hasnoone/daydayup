package org.example.designpattern.observerpattern;

public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        //观察者模式的玄机就在这里。就是把自己交给对象，存起来
        this.subject.attach(this);

    }

    @Override
    public void update() {
        System.out.println("Binary String: "+Integer.toBinaryString( subject.getState() ));
    }
}

package org.example.designpattern.templatepattern;

public abstract class Game {

    protected abstract void initialize();

    protected abstract void startPlay();

    protected abstract void endPlay();


    public void play() {

        initialize();

        startPlay();

        endPlay();


    }


}

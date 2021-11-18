package org.example.designpattern.callbackpattern;

public abstract class Task {

    public final void endWithExec(CallBack callBack) {
        execute();
        if(null!=callBack) callBack.exec();
    }

    protected abstract void execute();


}

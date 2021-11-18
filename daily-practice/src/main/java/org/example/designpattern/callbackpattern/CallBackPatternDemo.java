package org.example.designpattern.callbackpattern;

/**
 * 回调设计模式
 */
public class CallBackPatternDemo {

    public static void main(String[] args) {

        Task task = new SimpleTask();

        task.endWithExec(new CallBack() {
            public void exec() {
                System.out.println("The callback method has been called!");
            }
        });

        task.execute();



    }


}

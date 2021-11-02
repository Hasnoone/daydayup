package org.example.day1102.threaddemo;

public class Task implements Runnable {

    private int code;

    public Task(int code) {
        this.code = code;
    }




    @Override
    public void run() {

        for (int i = code; i >= 0; i--) {
            System.out.println("I'm " + i);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package org.example.day1122;

public class MedalService {

    public String getMedalInfo()  {
        try {
            Thread.sleep(500); //模拟调用耗时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "勋章";
    }

}

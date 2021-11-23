package org.example.day1122;

public class UserInfoService {
    public String getUserInfo()  {
        try {
            Thread.sleep(300);//模拟调用耗时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "获取用户信息"; //一般是查数据库，或者远程调用返回的
    }
}

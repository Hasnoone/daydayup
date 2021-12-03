package org.example.day1203;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisDemo {


    public static void main(String[] args) {

        Jedis jedis = new Jedis("192.168.81.128", 6379);


        jedis.set("test", "test");

    }



}

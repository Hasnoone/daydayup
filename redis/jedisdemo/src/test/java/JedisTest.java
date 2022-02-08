import redis.clients.jedis.Jedis;

public class JedisTest {


    public static void main(String[] args) {
        Jedis redis = new Jedis("192.168.81.128", 6379);
        //在Redis中写字符串 key value
        redis.set("jedis:name:1", "jd-zhangfei");
        //获得Redis中字符串的值
        System.out.println(redis.get("jedis:name:1"));
        //在Redis中写list
        redis.lpush("jedis:list:1", "1", "2", "3", "4", "5");
        //获得list的长度
        System.out.println(redis.llen("jedis:list:1"));
        System.out.println(redis.lrange("jedis:list:1",0,-1));


    }


}

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.io.Serializable;

@ContextConfiguration({ "classpath:redis.xml" })
public class RedisTest extends AbstractJUnit4SpringContextTests {


    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;


    @Test
    public void test() {
        redisTemplate.opsForValue().set("name","zhangfei");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }







}

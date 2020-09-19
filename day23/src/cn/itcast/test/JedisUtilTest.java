package cn.itcast.test;

import cn.itcast.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * jedis测试类
 */
public class JedisUtilTest {
    @Test
    public void testJedisPoolUtil(){
        Jedis jedis = JedisPoolUtils.getJedis();
        jedis.set("name","张三");
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
    }
}

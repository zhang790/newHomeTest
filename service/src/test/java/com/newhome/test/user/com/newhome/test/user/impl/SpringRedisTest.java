package com.newhome.test.user.com.newhome.test.user.impl;

import com.newhome.test.user.AbstractServiceTest;
import com.newhome.util.bean.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

/**
 * 测试使用redis
 *
 * @author zhangjiayu zhangjiayu
 * @create 2017/10/21
 */
public class SpringRedisTest extends AbstractServiceTest{

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testSpringRedis(){

        redisUtil.set("zhangjiayu","ceshi1");
        System.out.println("插入成功");
        String text = (String) redisUtil.get("zhangjiayu");
        System.out.println(text);

    }
}

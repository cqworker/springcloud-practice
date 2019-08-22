package com.kw13;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 2019/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void stringTest() throws Exception {
        // 保存字符串        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }
    @Test
    public void listTest() throws Exception {
        // 保存list
        List<String> list = new ArrayList<>();
        list.add("12");
        list.add("13");
        list.add("14");
        redisTemplate.opsForValue().set("20190909-doctor-queen", list);
        Object o = redisTemplate.opsForValue().get("20190909-doctor-queen");
        System.out.println(o);
        Assert.assertNotNull(o);
    }
    @Test
    public void mapTest() throws Exception {
        // 保存hash
        Map<String,String> map = new HashMap<>();
        map.put("roomid","123");
        map.put("doctorid","321");
        map.put("status","1");
        redisTemplate.opsForValue().set("room-serial", map);
        Object room = redisTemplate.opsForValue().get("room-serial");
        System.out.println(room);
        Assert.assertNotNull(room);
    }

}

package com.kw13.common.config.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Date 2019/3/11
 */
@Service
public class RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public Object getValue(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void setValue(final String key, final String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setValue(final String key, final String value, final long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public String getStrValue(final String key) {
        return redisTemplate.opsForValue().get(key, 0, -1);
    }

    public long incrementLong(final String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public long getLongValue(final String key) {
        return Long.parseLong(redisTemplate.opsForValue().get(key, 0, -1));
    }

    public void setHValue(final String key, final String hashkey, final String value) {
        redisTemplate.opsForHash().put(key, hashkey, value);
    }

    public String getStrHashValue(final String key, final String hashkey) {
        return redisTemplate.opsForHash().get(key, hashkey).toString();
    }

    public int getIntHashValue(final String key, final String hashkey) {
        return Integer.parseInt(redisTemplate.opsForHash().get(key, hashkey).toString());
    }

    public long getLongHashValue(final String key, final String hashkey) {
        return Long.parseLong(redisTemplate.opsForHash().get(key, hashkey).toString());
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
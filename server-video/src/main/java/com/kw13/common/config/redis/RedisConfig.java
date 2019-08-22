package com.kw13.common.config.redis;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

/**
 * @Desc redis配置类
 * @Date 2019/3/11
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    //使用Redis作为缓存实现
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration config =  RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(1));
        RedisCacheManager cacheManager =  RedisCacheManager.builder(factory).cacheDefaults(config).build();
        return cacheManager;
    }
}
package com.share.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

/**
 * Created by antLuck on 2017/8/9.
 */
@Configuration
@EnableCaching        //开启项目支持缓存
public class RedisConfiguration extends CachingConfigurerSupport {

    @Override
    public KeyGenerator keyGenerator(){
        return new KeyGenerator(){
            public Object generate(Object o, Method method,Object ...objects){
                //格式化缓存key字符串
                StringBuilder sb = new StringBuilder();
                //追加类名
                sb.append(o.getClass().getName());
                //追加 方法名
                sb.append(method.getName());
                //遍历参数并追加
                for(Object obj:objects){
                    sb.append(obj.toString());
                }
                System.out.println("key  ==  "+sb.toString());
                return sb.toString();
            }
        };
    }



    /**
     * 采用RedisCacheManage作为缓存管理器
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){
        return new RedisCacheManager(redisTemplate);
    }
}

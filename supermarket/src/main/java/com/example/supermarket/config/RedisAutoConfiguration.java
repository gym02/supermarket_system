package com.example.supermarket.config;

/**
 * @author 高垚淼
 * @version 1.0
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.net.UnknownHostException;

/**
 * @Author author
 * @Create 2023-09-26 16:24
 */
@Configuration
public class RedisAutoConfiguration {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(RedisSerializer.string());
        //将任何Java对象转换为JSON格式的二进制数据，也可以将JSON格式的二进制数据转换为Java对象。
        template.setValueSerializer(RedisSerializer.json());
        template.setHashKeySerializer(RedisSerializer.string());
        //将任何Java对象转换为JSON格式的二进制数据，也可以将JSON格式的二进制数据转换为Java对象。
        template.setHashValueSerializer(RedisSerializer.json());
        return template;
    }
}
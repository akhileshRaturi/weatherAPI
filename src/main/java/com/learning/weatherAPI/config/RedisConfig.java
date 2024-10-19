package com.learning.weatherAPI.config;

import com.learning.weatherAPI.entity.Weather;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RedisConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public RedisTemplate<String, Weather> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Weather> connection = new RedisTemplate<>();
        connection.setConnectionFactory(redisConnectionFactory);
        connection.setKeySerializer(new StringRedisSerializer());
        connection.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return connection;
    }


}

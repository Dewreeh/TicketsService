package org.repin.configuration;

import org.repin.model.Ticket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Long, Long> redisTemplate(){
        RedisTemplate<Long, Long> template = new RedisTemplate<>();
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Ticket.class));
        return new RedisTemplate<Long, Long>();
    }
}

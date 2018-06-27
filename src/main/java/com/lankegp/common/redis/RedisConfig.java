package com.lankegp.common.redis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableAutoConfiguration
public class RedisConfig {

    @Bean(name = "spring.data.redis.pool")
    public JedisPoolConfig getRedisConfig(@Value("${spring.data.redis.pool.max-active}") int maxTotal,
                                          @Value("${spring.data.redis.pool.max-idle}") int maxIdle,
                                          @Value("${spring.data.redis.pool.max-wait}") int maxWaitMillis) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        return config;
    }

    @Bean(name = "spring.data.redis")
    public JedisConnectionFactory getConnectionFactory(@Qualifier("spring.data.redis.pool") JedisPoolConfig config,
                                                       @Value("${spring.data.redis.host}") String host,
                                                       @Value("${spring.data.redis.port}") int port) {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setPoolConfig(config);
        factory.setHostName(host);
        factory.setPort(port);
        return factory;
    }

    @Bean
    public RedisTemplate<?, ?> getRedisTemplate(@Qualifier("spring.data.redis") JedisConnectionFactory factory) {
        RedisTemplate<?, ?> template = new StringRedisTemplate(factory);
        return template;
    }

}
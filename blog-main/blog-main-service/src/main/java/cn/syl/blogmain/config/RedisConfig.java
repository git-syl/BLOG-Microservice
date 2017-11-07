package cn.syl.blogmain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * redis单机版
 * @author: syl  Date: 2017/11/6 Email:nerosyl@live.com
 */
@Configuration
public class RedisConfig {
    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Bean
    public JedisPool jedisPool(){
        return new JedisPool(host,port);
    }

}

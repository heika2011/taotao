package cn.tt.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.Jedis;

/**
 * Redis配置类
 * 谢
 */
@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {

    @Value("${redis.url}")
    private String url;
    @Value("${redis.port}")
    private Integer port;

    @Bean
    public Jedis jedis(){

        return new Jedis(url,port);
    }
}

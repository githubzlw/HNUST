package com.cn.hnust.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis配置，项目启动注入JedisPool
 * http://www.cnblogs.com/GodHeng/p/9301330.html
 * @author Wang926454
 * @date 2018/9/5 10:35
 */
@Configuration
@PropertySource("classpath:shiro.properties")
public class JedisConfig {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisConfig.class);
    
	 @Autowired
	 private Environment environment;

    @Bean
    public JedisPool redisPoolFactory() {
        try {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(Integer.parseInt(environment.getProperty("redis.maxIdle")));
            jedisPoolConfig.setMaxWaitMillis(Integer.parseInt(environment.getProperty("redis.maxWait")));
            jedisPoolConfig.setMaxTotal(Integer.parseInt(environment.getProperty("redis.maxActive")));
            jedisPoolConfig.setMinIdle(Integer.parseInt(environment.getProperty("redis.minIdle")));
            // JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
            JedisPool jedisPool = new JedisPool(jedisPoolConfig, environment.getProperty("redis.host"), Integer.parseInt(environment.getProperty("redis.port")), Integer.parseInt(environment.getProperty("redis.timeout")), null);
            return jedisPool;
        } catch (Exception e) {
            LOGGER.error("初始化Redis连接池JedisPool异常:" + e);
        }
        return null;
    }

 
}

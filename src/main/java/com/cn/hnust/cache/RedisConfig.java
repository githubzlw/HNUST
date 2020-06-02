/*package com.cn.hnust.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.jcache.config.JCacheConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig extends JCacheConfigurerSupport {
	 @Autowired
	 private Environment environment;
	 
	 @Bean
	 public RedisConnectionFactory redisConnectionFactory() {
	 JedisConnectionFactory fac = new JedisConnectionFactory();
	 fac.setHostName(environment.getProperty("redis.host"));
	 fac.setPort(Integer.parseInt(environment.getProperty("redis.port")));
	 fac.setPassword(environment.getProperty("redis.password"));
	 fac.setTimeout(Integer.parseInt(environment.getProperty("redis.timeout")));
	 fac.getPoolConfig().setMaxIdle(Integer.parseInt(environment.getProperty("redis.maxIdle")));
	 fac.getPoolConfig().setMaxTotal(Integer.parseInt(environment.getProperty("redis.maxTotal")));
	 fac.getPoolConfig().setMaxWaitMillis(Integer.parseInt(environment.getProperty("redis.maxWaitMillis")));
	 fac.getPoolConfig().setMinEvictableIdleTimeMillis(
	 Integer.parseInt(environment.getProperty("redis.minEvictableIdleTimeMillis")));
	 fac.getPoolConfig()
	 .setNumTestsPerEvictionRun(Integer.parseInt(environment.getProperty("redis.numTestsPerEvictionRun")));
	 fac.getPoolConfig().setTimeBetweenEvictionRunsMillis(
	 Integer.parseInt(environment.getProperty("redis.timeBetweenEvictionRunsMillis")));
	 fac.getPoolConfig().setTestOnBorrow(Boolean.parseBoolean(environment.getProperty("redis.testOnBorrow")));
	 fac.getPoolConfig().setTestWhileIdle(Boolean.parseBoolean(environment.getProperty("redis.testWhileIdle")));
	 System.out.println("初始化参数");
	 return fac;
	 }
	 
	 @Bean
	 public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		 RedisTemplate<String, Object> redis = new RedisTemplate<>();
		 redis.setConnectionFactory(redisConnectionFactory);
	 
	// 设置redis的String/Value的默认序列化方式
		 DefaultStrSerializer stringRedisSerializer = new DefaultStrSerializer();
		 redis.setKeySerializer(stringRedisSerializer);
		 redis.setValueSerializer(stringRedisSerializer);
		 redis.setHashKeySerializer(stringRedisSerializer);
		 redis.setHashValueSerializer(stringRedisSerializer);
		 
		 redis.afterPropertiesSet();
		 return redis;
	 }
}
*/
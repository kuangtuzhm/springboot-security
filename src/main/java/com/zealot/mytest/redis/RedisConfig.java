package com.zealot.mytest.redis;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;  
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;  
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
  



import redis.clients.jedis.JedisPool;  
import redis.clients.jedis.JedisPoolConfig; 

@Configuration  
@EnableAutoConfiguration
public class RedisConfig {
    

	private static Logger logger = Logger.getLogger(RedisConfig.class);  
    
	@Value("${spring.redis.host}")
    private String hostName;  
  
	@Value("${spring.redis.port}")
    private int port;  
  
	@Value("${spring.redis.password}")
    private String password;  
  
	@Value("${spring.redis.timeout}")
    private int timeout;  
	
	@Value("${spring.redis.pool.maxTotal}")
    private int maxTotal; 
	
	@Value("${spring.redis.pool.maxIdle}")
    private int maxIdle; 
	
	@Value("${spring.redis.pool.maxWaitMillis}")
    private int maxWaitMillis; 
      
    @Bean  
    @ConfigurationProperties(prefix="spring.redis")
    public JedisPoolConfig getRedisConfig(){  
        JedisPoolConfig config = new JedisPoolConfig();  
        config.setMaxTotal(maxTotal);  
        config.setMaxIdle(maxIdle);  
        config.setMaxWaitMillis(maxWaitMillis); 
        return config;  
    }  
      
    @Bean  
    public JedisPool getJedisPool(){  
        JedisPoolConfig config = getRedisConfig();  
        if(StringUtils.isEmpty(password))
        {
        	password = null;
        }
        JedisPool pool = new JedisPool(config,hostName,port,timeout,password);  
        logger.info("init JredisPool ...");  
        return pool;  
    }  
}

package com.zealot.mytest.redis.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.zealot.mytest.redis.BaseRedisCache;
import com.zealot.mytest.redis.service.IRedisService;

@Service("redisService")
public class RedisServiceImpl extends BaseRedisCache implements IRedisService {

	private static Logger logger = Logger.getLogger(RedisServiceImpl.class);  
	
	@Override
	public void set(String key, String value) {
		Jedis jedis=null;  
        try{  
            jedis = getResource();  
            jedis.set(key, value);
            logger.debug("Redis set success - " + key + ", value:" + value);  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);  
        }finally{  
            returnResource(jedis);  
        }
	}
	
	@Override
	public void set(String key, String value, int seconds) {
		Jedis jedis=null;  
        try{  
            jedis = getResource();  
            jedis.setex(key, seconds, value);
            logger.debug("Redis set success - " + key + ", value:" + value);  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);  
        }finally{  
            returnResource(jedis);  
        }
	}

	@Override
	public String get(String key) {
		String result = null;  
        Jedis jedis=null;  
        try{  
            jedis = getResource();  
            result = jedis.get(key);  
            logger.debug("Redis get success - " + key + ", value:" + result);  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + result);  
        }finally{  
            returnResource(jedis);  
        }  
        return result;  
	}

}

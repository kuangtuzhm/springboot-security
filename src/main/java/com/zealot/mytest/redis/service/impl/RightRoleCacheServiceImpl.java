package com.zealot.mytest.redis.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.zealot.mytest.redis.BaseRedisCache;
import com.zealot.mytest.redis.service.RightRoleCacheService;
import com.zealot.mytest.util.SerializeUtil;

@Service("rightRoleCacheService")
public class RightRoleCacheServiceImpl extends BaseRedisCache implements RightRoleCacheService {

	private final static Logger logger = LoggerFactory.getLogger(RightRoleCacheServiceImpl.class);
	
	@Override
	public void set(String rightCode, Set<Integer> roleSet) {
		Jedis jedis=null;  
        try{  
            jedis = getResource();  
            jedis.set(rightCode.getBytes() , SerializeUtil.toByteArray(roleSet));
            logger.debug("Redis set success rightCode- " + rightCode);  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error("Redis set error: "+ e.getMessage() +" rightCode- " + rightCode);  
        }finally{  
            returnResource(jedis);  
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Integer> get(String rightCode) {
		Set<Integer> result = null;  
        Jedis jedis=null;  
        try{  
            jedis = getResource();  
            byte [] value = jedis.get(rightCode.getBytes());  
            if(value != null)
            {
            	result = (Set<Integer>)SerializeUtil.toObject(value);
            }
            logger.debug("Redis get success - " + rightCode);  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error("Redis set error: "+ e.getMessage() +" - " + rightCode);  
        }finally{  
            returnResource(jedis);  
        }  
        return result;
	}

}

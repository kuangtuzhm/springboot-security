package com.zealot.mytest.redis.service;

public interface IRedisService {
  
    public void set(String key, String value);  
    
    public void set(String key, String value, int seconds);
  
    public String get(String key);
}

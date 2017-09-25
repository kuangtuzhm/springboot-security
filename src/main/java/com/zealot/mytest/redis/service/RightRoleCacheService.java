package com.zealot.mytest.redis.service;

import java.util.Set;

public interface RightRoleCacheService {

	public void set(String rightCode, Set<Integer> roleSet);
	
	public Set<Integer> get(String rightCode);
}

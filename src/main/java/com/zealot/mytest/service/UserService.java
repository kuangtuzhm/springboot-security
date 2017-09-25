package com.zealot.mytest.service;

import com.zealot.mytest.po.User;


public interface UserService {

	public User findUserByName(String name);
	
	public User findUserByLoginName(String loginName);
}

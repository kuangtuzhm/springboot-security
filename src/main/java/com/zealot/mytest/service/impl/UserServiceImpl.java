package com.zealot.mytest.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zealot.mytest.dao.UserDao;
import com.zealot.mytest.dao.extend.UserExtendDao;
import com.zealot.mytest.po.User;
import com.zealot.mytest.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	@Resource
	private UserExtendDao userExtendDao;

	@Override
	public User findUserByName(String name) {

		return userExtendDao.findUserByName(name);
	}

	@Override
	public User findUserByLoginName(String loginName) {

		return userExtendDao.findUserByLoginName(loginName);
	}
}

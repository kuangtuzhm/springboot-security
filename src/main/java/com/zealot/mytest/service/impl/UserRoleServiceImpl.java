package com.zealot.mytest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zealot.mytest.dao.UserRoleDao;
import com.zealot.mytest.dao.extend.UserRoleExtendDao;
import com.zealot.mytest.po.UserRole;
import com.zealot.mytest.service.UserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

	@Resource
	private UserRoleDao userRoleDdao;
	
	@Resource
	private UserRoleExtendDao userRoleExtendDao;
	 
	@Override
	public List<UserRole> findByUser(int uid) {
		
		return userRoleExtendDao.findByUser(uid);
	}

}

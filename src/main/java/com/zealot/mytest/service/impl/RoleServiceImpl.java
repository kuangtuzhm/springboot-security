package com.zealot.mytest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zealot.mytest.dao.RoleDao;
import com.zealot.mytest.po.Role;
import com.zealot.mytest.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;
	
	@Override
	public List<Role> findRoles() {
		
		return null;
	}

}

package com.zealot.mytest.service;

import java.util.Set;

import com.zealot.mytest.po.RoleRight;

public interface RoleRightService {

	public Set<Integer> findRolesByRightCode(String code);
	
	public void save(RoleRight roleRight);
}

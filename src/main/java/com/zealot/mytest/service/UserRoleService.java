package com.zealot.mytest.service;

import java.util.List;

import com.zealot.mytest.po.UserRole;

public interface UserRoleService {

	public List<UserRole> findByUser(int uid);
}

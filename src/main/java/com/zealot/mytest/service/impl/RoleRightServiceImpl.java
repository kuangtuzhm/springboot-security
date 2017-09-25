package com.zealot.mytest.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zealot.mytest.dao.extend.RoleRightExtendDao;
import com.zealot.mytest.po.RoleRight;
import com.zealot.mytest.redis.service.RightRoleCacheService;
import com.zealot.mytest.service.RoleRightService;

@Service("roleRightService")
public class RoleRightServiceImpl implements RoleRightService {

	@Resource(name="rightRoleCacheService")
	private RightRoleCacheService rightRoleCacheService;
	
	@Resource
	private RoleRightExtendDao roleRightExtendDao;
	
	@Override
	public Set<Integer> findRolesByRightCode(String rightCode) {
		Set<Integer> set = rightRoleCacheService.get(rightCode);
		//缓存中没有,去数据库查询
		if(set == null || set.size() == 0)
		{
			set = roleRightExtendDao.findRolesByRightCode(rightCode);
			if(set != null && set.size() > 0)
			{
				rightRoleCacheService.set(rightCode, set);
			}
		}
		return set;
	}

	@Override
	public void save(RoleRight roleRight) {
		// TODO Auto-generated method stub

	}

}

package com.zealot.mytest.dao.extend;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zealot.mytest.po.User;

@Repository
public interface UserExtendDao {

	public User findUserByName(@Param("uname")String uname);
	
	public User findUserByLoginName(@Param("loginName")String loginName);
	
	
}

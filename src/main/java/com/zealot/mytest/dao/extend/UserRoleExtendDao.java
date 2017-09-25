package com.zealot.mytest.dao.extend;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zealot.mytest.po.UserRole;

@Repository
public interface UserRoleExtendDao {

	public List<UserRole> findByUser(@Param("uid")Integer uid);
}

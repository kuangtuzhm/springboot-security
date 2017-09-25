package com.zealot.mytest.dao.extend;

import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRightExtendDao {

	public Set<Integer> findRolesByRightCode(@Param("rightCode")String rightCode);
}

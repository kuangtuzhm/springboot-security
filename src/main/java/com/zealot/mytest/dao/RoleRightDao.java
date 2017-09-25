package com.zealot.mytest.dao;

import com.zealot.mytest.po.RoleRight;
import com.zealot.mytest.po.RoleRightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleRightDao {
    int countByExample(RoleRightExample example);

    int deleteByExample(RoleRightExample example);

    int insert(RoleRight record);

    int insertSelective(RoleRight record);

    List<RoleRight> selectByExample(RoleRightExample example);

    int updateByExampleSelective(@Param("record") RoleRight record, @Param("example") RoleRightExample example);

    int updateByExample(@Param("record") RoleRight record, @Param("example") RoleRightExample example);
}
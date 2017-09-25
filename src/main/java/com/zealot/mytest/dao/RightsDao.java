package com.zealot.mytest.dao;

import com.zealot.mytest.po.Rights;
import com.zealot.mytest.po.RightsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RightsDao {
    int countByExample(RightsExample example);

    int deleteByExample(RightsExample example);

    int deleteByPrimaryKey(String rightCode);

    int insert(Rights record);

    int insertSelective(Rights record);

    List<Rights> selectByExample(RightsExample example);

    Rights selectByPrimaryKey(String rightCode);

    int updateByExampleSelective(@Param("record") Rights record, @Param("example") RightsExample example);

    int updateByExample(@Param("record") Rights record, @Param("example") RightsExample example);

    int updateByPrimaryKeySelective(Rights record);

    int updateByPrimaryKey(Rights record);
}
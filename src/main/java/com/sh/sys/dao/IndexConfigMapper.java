package com.sh.sys.dao;

import com.sh.sys.model.IndexConfig;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IndexConfigMapper {
    int deleteByPrimaryKey(Integer configId);

    int insert(IndexConfig record);

    int insertSelective(IndexConfig record);

    IndexConfig selectByPrimaryKey(Integer configId);

    int updateByPrimaryKeySelective(IndexConfig record);

    int updateByPrimaryKey(IndexConfig record);

    @Select("select config_sub_id from sys_index_config where config_type=#{configType} and is_expire=0")
    List<Integer> selectSubIdsByConfigType(byte configType);

    @Select("select count(*) from sys_index_config where config_type=#{configType}")
    int selectCount(byte configType);

    @Select("select * from sys_index_config where config_type=#{configType} limit #{offset},#{limit}")
    List<IndexConfig> selectByPage(@Param("configType")byte configType,
                                   @Param("offset") int offset,
                                   @Param("limit") int limit);
}
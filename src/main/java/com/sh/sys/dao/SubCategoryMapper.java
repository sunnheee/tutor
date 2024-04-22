package com.sh.sys.dao;

import com.sh.sys.model.SubCategory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SubCategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(SubCategory record);

    int insertSelective(SubCategory record);

    SubCategory selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(SubCategory record);

    int updateByPrimaryKey(SubCategory record);

    @Select("select * from sys_sub_category where is_expire=0")
    List<SubCategory> selectAllContent();

    @Select("select parent_id from sys_sub_category where category_id = #{categoryId}")
    int selectParentIdByCategoryId(int categoryId);

    @Select("select * from sys_sub_category where parent_id=#{parentId} and category_level=#{level} limit #{offset},#{size}")
    List<SubCategory> selectByParentIdAndPage(@Param("parentId") int parentId,
                                           @Param("level") int level,
                                           @Param("offset") int offset,
                                           @Param("size") int size);

    int selectCount(@Param("parentId") int parentId,
                    @Param("level") int level);

    @Update("update sys_sub_category set is_expire=1 where category_id=#{id}")
    int deletedCategory(int id);


    @Select("select * from sys_sub_category where category_level=#{level} and is_expire=0")
    List<SubCategory> selectByLevel(int level);

    @Select("select * from sys_sub_category where category_id=#{id} ")
    SubCategory selectById(int id);

    @Select("select * from sys_sub_category where parent_id=#{parentId}")
    List<SubCategory> selectByParentId(int parentId);
}
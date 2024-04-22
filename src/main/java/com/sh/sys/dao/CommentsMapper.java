package com.sh.sys.dao;

import com.sh.sys.model.Comments;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CommentsMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comments record);

    int insertSelective(Comments record);

    Comments selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKey(Comments record);

    @Select("select * from sys_comments where sub_id = #{subId} and is_deleted=0")
    List<Comments> selectBySubId(int subId);

    @Select("select count(*) from sys_comments where sub_id = #{subId} and is_deleted=0")
    int selectCountBySubId(int subId);

    @Select("select count(*) from sys_comments")
    int selectCount();

    @Select("select * from sys_comments limit #{offset},#{limit}")
    List<Comments> selectByPage(@Param("offset") int offset, @Param("limit")int limit);

    @Update("update sys_comments set is_deleted=1 where comment_id=#{id}")
    int deletedComments(int id);
}
package com.sh.sys.dao;

import com.sh.sys.model.UserColl;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserCollMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserColl record);

    int insertSelective(UserColl record);

    UserColl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserColl record);

    int updateByPrimaryKey(UserColl record);

    @Select("select tutor_sub_id from sys_user_coll where user_id = #{userId} and id_deleted=0")
    List<Integer> getIdsByUserId(Long userId);

    @Select("select count(1) from sys_user_coll where user_id = #{userId} and id_deleted=0")
    int selectCountByUserId(Long userId);

    @Select("select count(1) from sys_user_coll where tutor_sub_id = #{tutorSubId} and id_deleted=0")
    int selectCountByTutorSubId(int tutorSubId);

    @Select("select * from sys_user_coll where user_id = #{userId} and tutor_sub_id = #{subId}")
    UserColl selectByUserIdAndSubId(@Param("userId") Long userId,@Param("subId") int subId);

    @Update("update sys_user_coll set id_deleted = 1 where user_id = #{userId} and tutor_sub_id=${subId}")
    int deleteByUserIdAndSubId(@Param("userId") Long userId,@Param("subId") int subId);
}
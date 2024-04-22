package com.sh.sys.dao;

import com.sh.sys.model.UserWish;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserWishMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserWish record);

    int insertSelective(UserWish record);

    UserWish selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserWish record);

    int updateByPrimaryKey(UserWish record);

    @Select("select tutor_sub_id from sys_user_wish where user_id = #{userId} and id_deleted=0")
    List<Integer> getIdsByUserId(Long userId);

    @Select("select count(1) from sys_user_wish where user_id = #{userId} and id_deleted=0")
    int selectCountByUserId(Long userId);

    @Select("select count(1) from sys_user_wish where tutor_sub_id = #{tutorSubId} and id_deleted=0")
    int selectCountByTutorSubId(int tutorSubId);

    @Select("select * from sys_user_wish where user_id = #{userId} and tutor_sub_id = #{subId}")
    UserWish selectByUserIdAndSubId(@Param("userId") Long userId, @Param("subId") int subId);

    @Update("update sys_user_wish set id_deleted = 1 where user_id = #{userId} and tutor_sub_id=${subId}")
    int deleteByUserIdAndSubId(@Param("userId") Long userId,@Param("subId") int subId);
}
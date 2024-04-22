package com.sh.sys.dao;

import com.sh.sys.model.Tutor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TutorMapper {
    int deleteByPrimaryKey(Integer tutorId);

    int insert(Tutor record);

    int insertSelective(Tutor record);

    Tutor selectByPrimaryKey(Integer tutorId);

    int updateByPrimaryKeySelective(Tutor record);

    int updateByPrimaryKey(Tutor record);

    List<Tutor> selectByIds(List<Integer> ids);

    @Select("select * from sys_tutor where tutor_id = #{id}")
    Tutor selectById(int id);

    @Select("select * from sys_tutor where tutor_phone = #{phone}")
    Tutor selectByPhone(String phone);

    @Select("select count(*) from sys_tutor")
    int selectCount();

    @Select("select * from sys_tutor order by tutor_id desc limit #{offset},#{size}")
    List<Tutor> selectByPage(@Param("offset") int offset,
                             @Param("size") int size);

    @Update("update sys_tutor set is_expire=1,effective=0 where tutor_id=#{id}")
    int deleteTutor(int id);

    @Update("update sys_tutor set effective=1 where tutor_id=#{id}")
    int approvalTutor(int id);

    @Select("select tutor_id from sys_tutor where user_id = #{userId}")
    int selectIdByUserId(Long userId);
}
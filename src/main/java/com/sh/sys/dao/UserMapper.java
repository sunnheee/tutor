package com.sh.sys.dao;

import com.sh.sys.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("select * from sys_user where user_id = #{id}")
    User selectById(Long id);

    @Update("update sys_user set phone = #{phone} where user_id = #{id}")
    int updatePhoneById(@Param("id") Long id,
                        @Param("phone") String phone);

    @Select("select * from sys_user where login_phone=#{phone}")
    User selectByPhone(String phone);

    @Insert("insert into sys_user (login_phone,password) values(#{phone},#{password})")
    int insertUser(User user);

    @Select("select * from sys_user where login_phone=#{phone} and password=#{password}")
    User selectByPhoneAndPassword(@Param("phone") String phone,
                                  @Param("password") String password);

    @Update("update sys_user set password = #{password} where user_id = #{id}")
    int updatePasswordById(@Param("id") Long id,
                           @Param("password") String password);

    @Select("select count(*) from sys_user")
    int selectCount();

    @Select("select * from sys_user limit #{offset},#{limit}")
    List<User> selectByPage(@Param("offset") int offset, @Param("limit")int limit);

    int lockByIds(@Param("lockStatus") int lockStatus,
                  @Param("ids") List<Long> ids);

    @Update("update sys_user set tutor_flag = 1 where user_id = #{userId}")
    int tutorFlagChange(Long userId);
}
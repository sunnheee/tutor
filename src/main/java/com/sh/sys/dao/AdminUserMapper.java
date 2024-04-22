package com.sh.sys.dao;

import com.sh.sys.model.AdminUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AdminUserMapper {
    int deleteByPrimaryKey(Integer adminUserId);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

    @Select("select * from sys_admin_user where username=#{name} and password=#{password}")
    AdminUser selectByNameAndPassword(@Param("name") String name, @Param("password") String password);
}
package com.sh.sys.service;

import com.sh.sys.model.User;
import com.sh.sys.vo.AdminUserVo;
import com.sh.sys.vo.JsonResult;

import javax.servlet.http.HttpSession;

public interface UserService {

     JsonResult save(User user, HttpSession session);

     JsonResult changePhone(Long userId,String phone,HttpSession session);

     JsonResult changePassword(Long userId,String oldPassword,String newPassword,HttpSession session);

     JsonResult adminLogin(AdminUserVo vo, HttpSession session);
}

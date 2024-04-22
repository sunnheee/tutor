package com.sh.sys.controller;

import com.sh.sys.model.User;
import com.sh.sys.service.impl.UserServiceImpl;
import com.sh.sys.vo.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Resource
    private UserServiceImpl userService;

    @PostMapping("saveUserEdit")
    public JsonResult saveUserEdit(User user, HttpSession session){
        return userService.save(user,session);
    }

    @PostMapping("changePhone")
    public JsonResult changePhone(Long userId,String phone,HttpSession session){
        return userService.changePhone(userId,phone,session);
    }

    @PostMapping("changePassword")
    public JsonResult changePassword(Long userId,String oldPassword,String newPassword,HttpSession session){
        return userService.changePassword(userId,oldPassword,newPassword,session);
    }
}

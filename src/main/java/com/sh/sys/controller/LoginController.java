package com.sh.sys.controller;

import com.sh.sys.service.impl.LoginServiceImpl;
import com.sh.sys.vo.JsonResult;
import com.sh.sys.vo.UserVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Resource
    private LoginServiceImpl loginService;

    @PostMapping("login")
    public JsonResult login(UserVo uv, HttpSession session){
        return loginService.login(uv,session);
    }

}

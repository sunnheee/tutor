package com.sh.sys.controller;

import com.sh.sys.service.impl.RegisterServiceImpl;
import com.sh.sys.vo.JsonResult;
import com.sh.sys.vo.UserVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RegisterController {

    @Resource
    private RegisterServiceImpl registerServiceImpl;

    @PostMapping("register")
    public JsonResult register(UserVo uv){
        return registerServiceImpl.register(uv);
    }
}

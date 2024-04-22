package com.sh.sys.service.impl;

import com.sh.sys.constants.ResultEnum;
import com.sh.sys.dao.UserMapper;
import com.sh.sys.model.User;
import com.sh.sys.service.RegisterService;
import com.sh.sys.vo.JsonResult;
import com.sh.sys.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private UserMapper userMapper;

    @Override
    public JsonResult register(UserVo uv) {
        String captcha = uv.getCaptcha();
        if(!"123456".equals(uv.getCaptcha())) return JsonResult.fail(ResultEnum.CAPTCHA_ERROR);
        User tmp = userMapper.selectByPhone(uv.getPhone());
        if(tmp!=null) return JsonResult.fail(ResultEnum.USER_EXISTS);
        User u = convert(uv);
        userMapper.insertUser(u);
        return JsonResult.success();
    }

    public User convert(UserVo vo){
        User u = new User();
        u.setPhone(vo.getPhone());
        u.setPassword(vo.getPassword());
        return u;
    }
}

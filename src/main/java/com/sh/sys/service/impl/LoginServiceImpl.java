package com.sh.sys.service.impl;

import com.sh.sys.constants.Constants;
import com.sh.sys.constants.ResultEnum;
import com.sh.sys.dao.UserCollMapper;
import com.sh.sys.dao.UserMapper;
import com.sh.sys.dao.UserWishMapper;
import com.sh.sys.model.User;
import com.sh.sys.service.LoginService;
import com.sh.sys.util.ThreadLocalUtil;
import com.sh.sys.vo.JsonResult;
import com.sh.sys.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ThreadLocalUtil threadLocalUtil;

    @Resource
    private UserCollMapper userCollMapper;

    @Resource
    private UserWishMapper userWishMapper;

    @Override
    public JsonResult login(UserVo uv, HttpSession session) {
        String captcha = (String)session.getAttribute(Constants.CAPTCHA_SESSION_KEY);
        if("".equals(captcha)||captcha == null) return JsonResult.fail(ResultEnum.SYSTEM_ERROR);
        if(!captcha.equalsIgnoreCase(uv.getCaptcha())){
            return JsonResult.fail(ResultEnum.CAPTCHA_ERROR);
        }
        User u = userMapper.selectByPhoneAndPassword(uv.getPhone(),uv.getPassword());
        if(u == null) return JsonResult.fail(ResultEnum.USERNAME_OR_PASSWORD_ERROR);
        else{
            session.setAttribute(Constants.USER_COLLECT_NUM,userCollMapper.selectCountByUserId(u.getUserId()));
            session.setAttribute(Constants.USER_WISH_NUM,userWishMapper.selectCountByUserId(u.getUserId()));
            session.setAttribute(Constants.LOGIN_USER_KEY,u);
            threadLocalUtil.setSession(session);
            return JsonResult.success();
        }
    }

    public User convert(UserVo vo){
        User u = new User();
        u.setPhone(vo.getPhone());
        u.setPassword(vo.getPassword());
        return u;
    }
}

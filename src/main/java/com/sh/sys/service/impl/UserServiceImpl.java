package com.sh.sys.service.impl;

import com.sh.sys.constants.Constants;
import com.sh.sys.constants.ResultEnum;
import com.sh.sys.dao.AdminUserMapper;
import com.sh.sys.dao.UserMapper;
import com.sh.sys.model.AdminUser;
import com.sh.sys.model.User;
import com.sh.sys.service.UserService;
import com.sh.sys.vo.AdminUserVo;
import com.sh.sys.vo.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public JsonResult save(User user, HttpSession session) {
        session.setAttribute(Constants.LOGIN_USER_KEY,user);
        userMapper.updateByPrimaryKeySelective(user);
        return JsonResult.success();
    }

    @Override
    public JsonResult changePhone(Long userId, String phone,HttpSession session) {
        if("".equals(phone) || phone == null){
            return JsonResult.fail(ResultEnum.SYSTEM_ERROR);
        }
        User user = userMapper.selectById(userId);
        if(phone.equals(user.getPhone())){
            return JsonResult.fail(ResultEnum.PHONE_ERROR);
        }
        user.setPhone(phone);
        session.setAttribute(Constants.LOGIN_USER_KEY,user);
        userMapper.updatePhoneById(userId,phone);
        return JsonResult.success();
    }

    @Override
    public JsonResult changePassword(Long userId,String oldPassword,String newPassword,HttpSession session){
        if(!oldPassword.equals(userMapper.selectById(userId).getPassword())){
            return JsonResult.fail(ResultEnum.PASSWORD_ERROR);
        }
        if(oldPassword.equals(newPassword)){
            return JsonResult.fail(ResultEnum.SAME_PASSWORD);
        }
        if(newPassword.length()<6){
            return JsonResult.fail(ResultEnum.SHORT_PASSWORD);
        }
        userMapper.updatePasswordById(userId,newPassword);
        session.invalidate();
        return JsonResult.success();
    }

    @Override
    public JsonResult adminLogin(AdminUserVo vo, HttpSession session){

        String captcha = (String)session.getAttribute(Constants.CAPTCHA_SESSION_KEY);
        if(!captcha.equalsIgnoreCase(vo.getVerifyCode())){
            return JsonResult.fail(ResultEnum.CAPTCHA_ERROR);
        }
        String password = vo.getPassword();
        AdminUser adminUser = adminUserMapper.selectByNameAndPassword(vo.getUserName(), password);
        if(adminUser != null){
            session.setAttribute(Constants.ADMIN_LOGIN_USER_KEY,adminUser);
            return JsonResult.success();
        }
        return JsonResult.fail(ResultEnum.USERNAME_OR_PASSWORD_ERROR);
    }
}

package com.sh.sys.service.admin.impl;

import com.sh.sys.constants.Constants;
import com.sh.sys.dao.AdminUserMapper;
import com.sh.sys.dao.UserMapper;
import com.sh.sys.model.AdminUser;
import com.sh.sys.model.User;
import com.sh.sys.service.admin.AdminUserService;
import com.sh.sys.vo.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public JsonResult list(int page, int limit){
        Map<String,Object> res = new HashMap<>();
        int offset = (page-1)*limit;
        int count = userMapper.selectCount();
        int totalPage = count/limit;
        if(count%limit>0){
            totalPage++;
        }
        List<User> users = userMapper.selectByPage(offset, limit);
        res.put("list",users);
        res.put("totalCount",count);
        res.put("totalPage",totalPage);
        return JsonResult.success(res);
    }

    @Override
    public JsonResult lock(int lockStatus,List<Long> ids){
        userMapper.lockByIds(lockStatus,ids);
        return JsonResult.success();
    }

    @Override
    public JsonResult rename(HttpSession session, String loginUserName, String nickName){
        AdminUser curUser = (AdminUser) session.getAttribute(Constants.ADMIN_LOGIN_USER_KEY);
        curUser.setNickname(nickName);
        curUser.setUsername(loginUserName);
        adminUserMapper.updateByPrimaryKeySelective(curUser);
        return JsonResult.success();
    }

    @Override
    public String repassword(HttpSession session,String originalPassword,String newPassword){
        AdminUser curUser = (AdminUser) session.getAttribute(Constants.ADMIN_LOGIN_USER_KEY);
        if(originalPassword.equals(curUser.getPassword())){
            curUser.setPassword(newPassword);
            adminUserMapper.updateByPrimaryKeySelective(curUser);
            return "success";
        }
        return "fail";
    }
}

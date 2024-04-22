package com.sh.sys.service.impl;

import com.sh.sys.constants.Constants;
import com.sh.sys.dao.UserWishMapper;
import com.sh.sys.model.User;
import com.sh.sys.service.UserWishService;
import com.sh.sys.vo.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class UserWishServiceImpl implements UserWishService {

    @Resource
    private UserWishMapper userWishMapper;

    @Override
    public JsonResult deleteItem(int id, HttpSession session) {
        User u =(User) session.getAttribute(Constants.LOGIN_USER_KEY);
        Long userId = u.getUserId();
        userWishMapper.deleteByUserIdAndSubId(userId,id);
        return JsonResult.success();
    }
}

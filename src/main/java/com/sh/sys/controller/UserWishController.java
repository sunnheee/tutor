package com.sh.sys.controller;

import com.sh.sys.service.impl.UserWishServiceImpl;
import com.sh.sys.vo.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class UserWishController {


    @Resource
    private UserWishServiceImpl userWishService;

    @PostMapping("deleteWishItem")
    public JsonResult deleteItem(int id, HttpSession session){
        return userWishService.deleteItem(id,session);
    }
}

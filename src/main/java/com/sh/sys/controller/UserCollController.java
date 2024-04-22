package com.sh.sys.controller;

import com.sh.sys.service.impl.UserCollServiceImpl;
import com.sh.sys.vo.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class UserCollController {

    @Resource
    private UserCollServiceImpl userCollService;

    @PostMapping("deleteCollItem")
    public JsonResult deleteItem(int id, HttpSession session){
        return userCollService.deleteItem(id,session);
    }
}

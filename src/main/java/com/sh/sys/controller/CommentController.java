package com.sh.sys.controller;

import com.sh.sys.service.impl.CommentServiceImpl;
import com.sh.sys.vo.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class CommentController {

    @Resource
    private CommentServiceImpl commentService;

    @PostMapping("sendComment")
    public JsonResult sendComment(HttpSession session,String comment,int subId){
        return commentService.sendComment(comment,session,subId);
    }

    @PostMapping("like")
    public JsonResult like(int id,HttpSession session){
        return commentService.like(id,session);
    }

    @PostMapping("addCollection")
    public JsonResult addCollection(int subId,HttpSession session){
        return commentService.addCollection(subId,session);
    }

    @PostMapping("addWish")
    public JsonResult addWish(int subId,HttpSession session){
        return commentService.addWish(subId,session);
    }
}

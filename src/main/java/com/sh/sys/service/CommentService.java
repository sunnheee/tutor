package com.sh.sys.service;

import com.sh.sys.vo.CommentVo;
import com.sh.sys.vo.JsonResult;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CommentService {

    List<CommentVo> getComments(int subId);

    int getCount(int subId);

    JsonResult sendComment(String comment, HttpSession session,int subId);

    JsonResult like(int commentId,HttpSession session);

    JsonResult addWish(int subId,HttpSession session);

    JsonResult addCollection(int subId,HttpSession session);
}

package com.sh.sys.service.impl;

import com.sh.sys.constants.Constants;
import com.sh.sys.constants.ResultEnum;
import com.sh.sys.dao.CommentsMapper;
import com.sh.sys.dao.UserCollMapper;
import com.sh.sys.dao.UserMapper;
import com.sh.sys.dao.UserWishMapper;
import com.sh.sys.model.Comments;
import com.sh.sys.model.User;
import com.sh.sys.model.UserColl;
import com.sh.sys.model.UserWish;
import com.sh.sys.service.CommentService;
import com.sh.sys.vo.CommentVo;
import com.sh.sys.vo.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentsMapper commentsMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserCollMapper userCollMapper;

    @Resource
    private UserWishMapper userWishMapper;

    @Override
    public List<CommentVo> getComments(int subId){
        List<CommentVo> vo = new ArrayList<>();
        List<Comments> comments = commentsMapper.selectBySubId(subId);
        comments.forEach(comment -> {
            CommentVo cv = new CommentVo();
            User user = userMapper.selectById(comment.getUserId());
            cv.setContent(comment.getContent());
            cv.setCreateTime(comment.getCreateTime());
            cv.setLikeNum(comment.getLikeNum());
            cv.setUserAvatar(user.getAvatarUrl());
            cv.setUserName(user.getNickname());
            cv.setCommentId(comment.getCommentId());
            vo.add(cv);
        });
        return vo;
    }

    @Override
    public int getCount(int subId){
        return commentsMapper.selectCountBySubId(subId);
    }

    @Override
    public JsonResult sendComment(String comment, HttpSession session,int subId) {
        User u = (User) session.getAttribute(Constants.LOGIN_USER_KEY);
        if(u == null){
            return JsonResult.fail(ResultEnum.NOT_LOGIN);
        }
        if("".equals(comment) || comment == null){
            return JsonResult.fail(ResultEnum.COMMENT_NULL);
        }
        Comments c = new Comments();
        c.setContent(comment);
        c.setUserId(u.getUserId());
        c.setSubId(subId);
         if(commentsMapper.insertSelective(c)>0){
             return JsonResult.success();
         }else return JsonResult.fail(ResultEnum.SYSTEM_ERROR);

    }

    @Override
    public JsonResult like(int commentId,HttpSession session) {
        if(session.getAttribute(Constants.LOGIN_USER_KEY) == null){
            return JsonResult.fail(ResultEnum.NOT_LOGIN);
        }
        Comments comments = commentsMapper.selectByPrimaryKey(commentId);
        comments.setLikeNum(comments.getLikeNum()+1);
        commentsMapper.updateByPrimaryKeySelective(comments);
        return JsonResult.success();
    }

    @Override
    public JsonResult addWish(int subId, HttpSession session) {
        User u = (User) session.getAttribute(Constants.LOGIN_USER_KEY);
        if(u == null) return JsonResult.fail(ResultEnum.NOT_LOGIN);
        if(userWishMapper.selectByUserIdAndSubId(u.getUserId(),subId) != null){
            return JsonResult.fail(ResultEnum.CONTENT_ALREADY_EXISTS);
        }
        UserWish uw = new UserWish();
        uw.setUserId(u.getUserId());
        uw.setTutorSubId(subId);
        uw.setIdDeleted((byte)0);
        if(userWishMapper.insertSelective(uw) > 0) {
            session.setAttribute(Constants.USER_WISH_NUM,userWishMapper.selectCountByUserId(u.getUserId()));
            return JsonResult.success();
        }
        else return JsonResult.fail(ResultEnum.SYSTEM_ERROR);
    }

    @Override
    public JsonResult addCollection(int subId, HttpSession session) {
        User u = (User) session.getAttribute(Constants.LOGIN_USER_KEY);
        if(u == null) return JsonResult.fail(ResultEnum.NOT_LOGIN);
        if(userCollMapper.selectByUserIdAndSubId(u.getUserId(),subId) != null){
            return JsonResult.fail(ResultEnum.CONTENT_ALREADY_EXISTS);
        }
        UserColl uc = new UserColl();
        uc.setUserId(u.getUserId());
        uc.setTutorSubId(subId);
        uc.setIdDeleted((byte)0);
        if(userCollMapper.insertSelective(uc) > 0) {
            session.setAttribute(Constants.USER_COLLECT_NUM,userCollMapper.selectCountByUserId(u.getUserId()));
            return JsonResult.success();
        }
        else return JsonResult.fail(ResultEnum.SYSTEM_ERROR);
    }
}

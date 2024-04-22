package com.sh.sys.service.admin.impl;

import com.sh.sys.constants.Constants;
import com.sh.sys.dao.CommentsMapper;
import com.sh.sys.model.AdminUser;
import com.sh.sys.model.Comments;
import com.sh.sys.service.admin.AdminCommentService;
import com.sh.sys.vo.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminCommentServiceImpl implements AdminCommentService {

    @Resource
    private CommentsMapper commentsMapper;

    @Override
    public JsonResult list(int page, int limit){
        Map<String,Object> res = new HashMap<>();
        int offset = (page-1)*limit;
        int count = commentsMapper.selectCount();
        int totalPage = count/limit;
        if(count%limit>0){
            totalPage++;
        }
        List<Comments> comments = commentsMapper.selectByPage(offset, limit);
        res.put("list",comments);
        res.put("totalCount",count);
        res.put("totalPage",totalPage);
        return JsonResult.success(res);
    }

    @Override
    public JsonResult save(Comments comment, HttpSession session){
        AdminUser adminUser = (AdminUser) session.getAttribute(Constants.ADMIN_LOGIN_USER_KEY);
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        comment.setLikeNum(0);
        comment.setUserId((long)0);
        comment.setIsDeleted((byte)0);
        commentsMapper.insert(comment);
        return JsonResult.success();
    }

    @Override
    public JsonResult delete(List<Integer> ids){
        for(int id:ids){
            commentsMapper.deletedComments(id);
        }
        return JsonResult.success();
    }
}

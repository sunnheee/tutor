package com.sh.sys.service.admin;

import com.sh.sys.model.Comments;
import com.sh.sys.vo.JsonResult;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AdminCommentService {

    JsonResult list(int page, int limit);

    JsonResult save(Comments comments, HttpSession session);

    JsonResult delete(List<Integer> ids);
}

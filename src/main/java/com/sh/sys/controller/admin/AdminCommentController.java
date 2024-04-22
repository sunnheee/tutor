package com.sh.sys.controller.admin;

import com.sh.sys.model.Comments;
import com.sh.sys.service.admin.impl.AdminCommentServiceImpl;
import com.sh.sys.vo.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminCommentController {


    @Resource
    private AdminCommentServiceImpl adminCommentService;

    @GetMapping("comments/list")
    public JsonResult getList(int page, int limit){
        return adminCommentService.list(page,limit);
    }

    @PostMapping("comments/save")
    public JsonResult save(@RequestBody Comments comment, HttpSession session){
        return adminCommentService.save(comment,session);
    }

    @PostMapping("comments/delete")
    public JsonResult delete(@RequestBody List<Integer> ids) {
        return adminCommentService.delete(ids);
    }
}

package com.sh.sys.controller.admin;

import com.sh.sys.constants.Constants;
import com.sh.sys.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private UserServiceImpl userService;

    @GetMapping({"/","index"})
    public String indexView(){
        return "admin/index";
    }

    @GetMapping("subs")
    public String subs(){
        return "admin/adminSubject";
    }

    @GetMapping("tutors")
    public String tutors(){
        return "admin/adminTutor";
    }

    @GetMapping("users")
    public String users(){
        return "admin/adminUsersManager";
    }

    @GetMapping("comments")
    public String comments(){
        return "admin/adminComment";
    }

    @GetMapping("indexConfigs")
    public String indexConfig(Model model, byte configType){
        model.addAttribute("configType",configType);
        return "admin/adminIndexConfig";
    }
    @GetMapping("categories")
    public String category(int parentId,int categoryLevel,int backParentId,Model model){
        model.addAttribute("parentId",parentId);
        model.addAttribute("categoryLevel",categoryLevel);
        model.addAttribute("backParentId",backParentId);
        return "admin/adminCategory";
    }

    @GetMapping("profile")
    public String profile(){
        return "admin/profile";
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute(Constants.ADMIN_LOGIN_USER_KEY);
        return "redirect:loginView";
    }
}

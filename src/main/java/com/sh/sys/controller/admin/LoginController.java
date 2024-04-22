package com.sh.sys.controller.admin;

import com.sh.sys.constants.Constants;
import com.sh.sys.service.impl.UserServiceImpl;
import com.sh.sys.vo.AdminUserVo;
import com.sh.sys.vo.JsonResult;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller("adminLoginController")
@RequestMapping("/admin")
public class LoginController {

    @Resource
    private UserServiceImpl userService;

    @GetMapping("loginView")
    public String loginView(){
        return "admin/login";
    }

    @PostMapping("login")
    public String login(HttpSession session, AdminUserVo vo){
        JsonResult result = userService.adminLogin(vo, session);
        if(result.getCode()==4) {
            session.setAttribute("errorMsg",result.getMsg());
            return "admin/login";
        }else if(result.getCode()==3){
            session.setAttribute("errorMsg",result.getMsg());
            return "admin/login";
        }else {
            return "admin/index";
        }
    }

    @GetMapping("captcha")
    public void captcha(HttpServletResponse response, HttpServletRequest request)throws Exception{
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("image/png");
        SpecCaptcha captcha = new SpecCaptcha(110,40,4);
        captcha.setCharType(Captcha.TYPE_DEFAULT);
        captcha.setCharType(Captcha.FONT_9);
        request.getSession().setAttribute(Constants.CAPTCHA_SESSION_KEY,captcha.text().toLowerCase());
        captcha.out(response.getOutputStream());
    }
}

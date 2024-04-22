package com.sh.sys.controller;

import com.sh.sys.constants.Constants;
import com.sh.sys.dao.SubCategoryMapper;
import com.sh.sys.dao.TutorSubMapper;
import com.sh.sys.model.SubCategory;
import com.sh.sys.model.TutorSub;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ViewController {

    @Resource
    private SubCategoryMapper subCategoryMapper;

    @Resource
    private TutorSubMapper tutorSubMapper;

    @GetMapping("sider")
    public String test(){
        return "sider";
    }

    @GetMapping("registerView")
    public String registerView(){
        return "register";
    }

    @GetMapping("loginView")
    public String loginView(){
        return "login";
    }

    @GetMapping("header")
    public String headerView(){
        return "header";
    }

    @GetMapping("user-center/userInfoEdit")
    public String userInfoView(){
        return "userInfoEdit";
    }

    @GetMapping("user-center/joinTutor")
    public String joinTutorView(){
        return "joinTutor";
    }

    @GetMapping("user-center/accountSecurity")
    public String accountSecurityView(){
        return "accountSecurity";
    }

    @GetMapping("user-center/addSubject")
    public String addSubject(Model model, @RequestParam(defaultValue = "0") int tutorSubId){
        List<SubCategory> firstLevel = subCategoryMapper.selectByLevel(1);
        List<SubCategory> secondLevel = subCategoryMapper.selectByLevel(2);
        if(tutorSubId > 0){
            TutorSub sub = tutorSubMapper.selectByPrimaryKey(tutorSubId);
            int id2 = sub.getSubCategoryId();
            SubCategory cat2 = subCategoryMapper.selectById(id2);
            int id1 = cat2.getParentId();
            model.addAttribute("sub",sub);
            model.addAttribute("firstLevelCategoryId",id1);
            model.addAttribute("secondLevelCategoryId",id2);
        }

            model.addAttribute("firstLevelCategories", firstLevel);
            model.addAttribute("secondLevelCategories", secondLevel);

        return "addSubject";
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

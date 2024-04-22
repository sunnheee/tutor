package com.sh.sys.controller;

import com.sh.sys.constants.Constants;
import com.sh.sys.service.impl.IndexServiceImpl;
import com.sh.sys.service.impl.SubCategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Resource
    private IndexServiceImpl indexService;

    @Resource
    private SubCategoryServiceImpl subCategoryService;

    @GetMapping({"/","index"})
    public String index(Model model){
        model.addAttribute("categorys",subCategoryService.getCategoryMenu());
        model.addAttribute("popularTags",subCategoryService.getHotTags());
        model.addAttribute("latestList",indexService.getTutorByIndexConfigType((byte)1));
        model.addAttribute("hotList",indexService.getTutorByIndexConfigType((byte)2));
        model.addAttribute("recommendedList",indexService.getTutorByIndexConfigType((byte)3));
        return "index";
    }

    @GetMapping("loginOut")
    public String logout(HttpSession session, Model model){
        session.removeAttribute(Constants.LOGIN_USER_KEY);
        return index(model);
    }
}

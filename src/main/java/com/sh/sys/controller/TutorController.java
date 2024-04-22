package com.sh.sys.controller;

import com.sh.sys.service.impl.TutorServiceImpl;
import com.sh.sys.vo.JsonResult;
import com.sh.sys.vo.TutorVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class TutorController {

    @Resource
    private TutorServiceImpl tutorService;

    @PostMapping("submitJoinTutor")
    public JsonResult submitJoinTutor(TutorVo tv,HttpSession session){
        return tutorService.submit(tv,session);
    }

    @PostMapping("getCaptcha")
    public JsonResult getCaptcha(String phone,HttpSession session){
       return tutorService.getCaptcha(phone,session);
    }
}

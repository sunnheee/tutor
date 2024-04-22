package com.sh.sys.service.impl;

import com.sh.sys.constants.Constants;
import com.sh.sys.constants.ResultEnum;
import com.sh.sys.dao.TutorMapper;
import com.sh.sys.model.Tutor;
import com.sh.sys.service.TutorService;
import com.sh.sys.util.RandomUtil;
import com.sh.sys.vo.JsonResult;
import com.sh.sys.vo.TutorVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class TutorServiceImpl implements TutorService {

    @Resource
    private TutorMapper tutorMapper;

    @Override
    public JsonResult submit(TutorVo tv, HttpSession session) {
        String captcha = (String) session.getAttribute(Constants.JOIN_TUTOR_SMS_VERI_CODE);
        if("".equals(captcha)||captcha == null) return JsonResult.fail(ResultEnum.SYSTEM_ERROR);
        if(!captcha.equals(tv.getCaptcha())){
            return JsonResult.fail(ResultEnum.CAPTCHA_ERROR);
        }
        Tutor temp = tutorMapper.selectByPhone(tv.getTutorPhone());
        if(temp != null){
            return JsonResult.fail(ResultEnum.TUTOR_EXISTS);
        }else {
            Tutor t = merge(tv);
            tutorMapper.insertSelective(t);
            return JsonResult.success();
        }
    }

    @Override
    public JsonResult getCaptcha(String phone,HttpSession session){
        if("".equals(phone) || phone == null){
            return JsonResult.fail(ResultEnum.PHONE_NONE);
        }
        String s = RandomUtil.generateRamdamCode();
        System.out.println(s);
        session.setAttribute(Constants.JOIN_TUTOR_SMS_VERI_CODE,s);
        return JsonResult.success();
    }

    public Tutor merge(TutorVo tv){
        Tutor t = new Tutor();
        t.setArea(tv.getArea());
        t.setBriefIntroduction(tv.getBriefIntroduction());
        t.setTutorName(tv.getTutorName());
        t.setTutorAge(tv.getTutorAge());
        t.setTutorGender(tv.getTutorGender());
        t.setTutorPhone(tv.getTutorPhone());
        t.setTutorPhoto(tv.getTutorPhoto());
        return t;
    }
}

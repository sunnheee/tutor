package com.sh.sys.service;

import com.sh.sys.vo.JsonResult;
import com.sh.sys.vo.TutorVo;

import javax.servlet.http.HttpSession;

public interface TutorService {

    JsonResult submit(TutorVo tv, HttpSession session);

    JsonResult getCaptcha(String phone,HttpSession session);
}

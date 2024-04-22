package com.sh.sys.service;

import com.sh.sys.vo.JsonResult;
import com.sh.sys.vo.UserVo;

import javax.servlet.http.HttpSession;

public interface LoginService {

     JsonResult login(UserVo uv, HttpSession session);

}

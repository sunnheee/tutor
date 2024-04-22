package com.sh.sys.service;

import com.sh.sys.vo.JsonResult;

import javax.servlet.http.HttpSession;

public interface UserCollService {


    JsonResult deleteItem(int id, HttpSession session);
}

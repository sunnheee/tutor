package com.sh.sys.service;

import com.sh.sys.vo.JsonResult;

import javax.servlet.http.HttpSession;

public interface UserWishService {

    JsonResult deleteItem(int id, HttpSession session);
}

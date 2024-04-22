package com.sh.sys.service.admin;

import com.sh.sys.vo.JsonResult;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AdminUserService {

    JsonResult list(int page, int limit);

    JsonResult lock(int lockStatus, List<Long> ids);

    JsonResult rename(HttpSession session, String loginUserName, String nickName);

    String repassword(HttpSession session, String originalPassword, String newPassword);
}

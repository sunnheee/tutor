package com.sh.sys.controller.admin;

import com.sh.sys.service.admin.impl.AdminUserServiceImpl;
import com.sh.sys.vo.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController("adminUserController")
@RequestMapping("/admin")
public class UserController {

    @Resource
    AdminUserServiceImpl adminUserService;

    @GetMapping("users/list")
    public JsonResult list(int page, int limit) {
        return adminUserService.list(page, limit);
    }

    @PostMapping("users/lock/{lockStatus}")
    @ResponseBody
    public JsonResult lock(@PathVariable("lockStatus") int lockStatus, @RequestBody List<Long> ids) {
        return adminUserService.lock(lockStatus, ids);
    }

    @PostMapping("profile/name")
    @ResponseBody
    public JsonResult rename(HttpSession session, String loginUserName, String nickName) {
        return adminUserService.rename(session, loginUserName, nickName);
    }

    @PostMapping("profile/password")
    @ResponseBody
    public String repassword(HttpSession session, String originalPassword, String newPassword) {
        return adminUserService.repassword(session,originalPassword,newPassword);
    }

}

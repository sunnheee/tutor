package com.sh.sys.controller.admin;


import com.sh.sys.service.admin.impl.AdminTutorServiceImpl;
import com.sh.sys.vo.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller("adminTutorController")
@RequestMapping("/admin")
public class TutorController {

    @Resource
    private AdminTutorServiceImpl adminTutorService;


    @GetMapping("tutors/list")
    @ResponseBody
    public JsonResult list(int page, int limit){
        return adminTutorService.list(page,limit);
    }


    @PostMapping("tutors/approval")
    @ResponseBody
    public JsonResult approval(@RequestBody List<Integer> ids){
        return adminTutorService.approval(ids);
    }

    @PostMapping("tutors/delete")
    @ResponseBody
    public JsonResult delete(@RequestBody List<Integer> ids){
        return adminTutorService.delete(ids);
    }
}

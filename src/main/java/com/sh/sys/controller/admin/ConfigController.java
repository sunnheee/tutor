package com.sh.sys.controller.admin;

import com.sh.sys.model.IndexConfig;
import com.sh.sys.service.admin.impl.ConfigServiceImpl;
import com.sh.sys.vo.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class ConfigController {

    @Resource
    private ConfigServiceImpl configService;

    @GetMapping("indexConfigs/list")
    public JsonResult getList(byte configType, int page, int limit){
       return configService.configType(configType,page,limit);
    }

    @PostMapping("indexConfigs/save")
    public JsonResult add(@RequestBody IndexConfig indexConfig, HttpSession session){
        return configService.addConfig(indexConfig,session);
    }

    @PostMapping("indexConfigs/update")
    public JsonResult update(@RequestBody IndexConfig indexConfig) {
        return configService.update(indexConfig);
    }

    @PostMapping("indexConfigs/delete")
    public JsonResult delete(@RequestBody List<Integer> ids){
        return configService.delete(ids);
    }
}

package com.sh.sys.controller;

import com.sh.sys.dao.IndexConfigMapper;
import com.sh.sys.model.IndexConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Controller
public class TestController {

    @Resource
    IndexConfigMapper indexConfigMapper;

    @GetMapping("testMethod")
    public String test(){
        IndexConfig config = new IndexConfig();
        config.setIsExpire((byte)1);
        config.setConfigId(1);
        indexConfigMapper.updateByPrimaryKeySelective(config);
        return "index";
    }
}

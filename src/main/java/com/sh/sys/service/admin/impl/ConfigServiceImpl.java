package com.sh.sys.service.admin.impl;

import com.sh.sys.constants.Constants;
import com.sh.sys.dao.IndexConfigMapper;
import com.sh.sys.model.AdminUser;
import com.sh.sys.model.IndexConfig;
import com.sh.sys.service.admin.ConfigService;
import com.sh.sys.vo.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Resource
    private IndexConfigMapper indexConfigMapper;

    @Override
    public JsonResult configType(byte configType, int page, int limit) {

        Map<String,Object> res = new HashMap<>();
        int offset = (page-1)*limit;
        int count = indexConfigMapper.selectCount(configType);
        int totalPage = count/limit;
        if(count%limit>0){
            totalPage++;
        }
        List<IndexConfig> configs = indexConfigMapper.selectByPage(configType, offset, limit);
        res.put("list",configs);
        res.put("totalCount",count);
        res.put("totalPage",totalPage);
        return JsonResult.success(res);
    }

    @Override
    public JsonResult addConfig(IndexConfig indexConfig, HttpSession session) {
        AdminUser adminUser = (AdminUser) session.getAttribute(Constants.ADMIN_LOGIN_USER_KEY);
        indexConfig.setIsExpire((byte)0);
        indexConfig.setCreateTime(new Date());
        indexConfig.setUpdateTime(new Date());
//        indexConfig.setCreateAdminId(adminUser.getAdminUserId());
        indexConfigMapper.insert(indexConfig);
        return JsonResult.success();
    }

    @Override
    public JsonResult update(IndexConfig indexConfig) {
        indexConfigMapper.updateByPrimaryKeySelective(indexConfig);
        return JsonResult.success();
    }

    @Override
    public JsonResult delete(List<Integer> ids){
        for(int id : ids){
            IndexConfig config = new IndexConfig();
            config.setIsExpire((byte)1);
            config.setConfigId(id);
            indexConfigMapper.updateByPrimaryKeySelective(config);
        }
        return JsonResult.success();
    }
}

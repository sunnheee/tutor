package com.sh.sys.service.admin.impl;

import com.sh.sys.constants.Constants;
import com.sh.sys.dao.SubCategoryMapper;
import com.sh.sys.model.AdminUser;
import com.sh.sys.model.SubCategory;
import com.sh.sys.service.admin.AdminCategoryService;
import com.sh.sys.vo.JsonResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {

    @Resource
    private SubCategoryMapper subCategoryMapper;

    @Override
    public JsonResult list(int categoryLevel, int parentId, int page, int limit){
        Map<String,Object> res = new HashMap<>();
        int offset = (page-1)*limit;
        int count = subCategoryMapper.selectCount(parentId,categoryLevel);
        int totalPage = count/limit;
        if(count%limit>0){
            totalPage++;
        }
        List<SubCategory> list = subCategoryMapper.selectByParentIdAndPage(parentId, categoryLevel,offset,limit);
        res.put("list",list);
        res.put("totalCount",count);
        res.put("totalPage",totalPage);
        return JsonResult.success(res);
    }

    @Override
    public JsonResult add(SubCategory category, HttpSession session){
        AdminUser adminUser = (AdminUser) session.getAttribute(Constants.ADMIN_LOGIN_USER_KEY);
        category.setCreateTime(new Date());
        category.setCreateAdminId(1);
        category.setUpdateTime(new Date());
        category.setIsExpire((byte)0);
        category.setUpdateAdminId(1);
        subCategoryMapper.insert(category);
        return JsonResult.success();
    }

    @Override
    public JsonResult delete(List<Integer> ids){
        for(int id:ids){
            subCategoryMapper.deletedCategory(id);
        }
        return JsonResult.success();
    }

    @Override
    public JsonResult update(SubCategory category){
        subCategoryMapper.updateByPrimaryKeySelective(category);
        return JsonResult.success();
    }
}

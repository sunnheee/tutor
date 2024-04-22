package com.sh.sys.service.admin.impl;

import com.sh.sys.dao.TutorSubMapper;
import com.sh.sys.model.TutorSub;
import com.sh.sys.service.admin.SubjectService;
import com.sh.sys.vo.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SubjectServiceImpl implements SubjectService {

    @Resource
    private TutorSubMapper tutorSubMapper;

    @Override
    public JsonResult list(int page, int limit){
        Map<String,Object> res = new HashMap<>();
        int offset = (page-1)*limit;
        int count = tutorSubMapper.selectCount();
        int totalPage = count/limit;
        if(count%limit>0){
            totalPage++;
        }
        List<TutorSub> list = tutorSubMapper.selectByPage(offset,limit);
        res.put("list",list);
        res.put("totalCount",count);
        res.put("totalPage",totalPage);
        return JsonResult.success(res);
    }

    @Override
    public JsonResult status(int status, List<Integer> ids){
        tutorSubMapper.updateStatusByIds(status, ids);
        return JsonResult.success();
    }
}

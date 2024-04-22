package com.sh.sys.service.admin.impl;

import com.sh.sys.dao.TutorMapper;
import com.sh.sys.dao.UserMapper;
import com.sh.sys.model.Tutor;
import com.sh.sys.service.admin.AdminTutorService;
import com.sh.sys.vo.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminTutorServiceImpl implements AdminTutorService {

    @Resource
    private TutorMapper tutorMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public JsonResult list(int page, int limit){
        Map<String,Object> res = new HashMap<>();
        int offset = (page-1)*limit;
        int count = tutorMapper.selectCount();
        int totalPage = count/limit;
        if(count%limit>0){
            totalPage++;
        }
        List<Tutor> list = tutorMapper.selectByPage(offset,limit);
        res.put("list",list);
        res.put("totalCount",count);
        res.put("totalPage",totalPage);
        return JsonResult.success(res);
    }

    @Override
    public JsonResult delete(List<Integer> ids){
        for(int id:ids){
            tutorMapper.deleteTutor(id);
        }
        return JsonResult.success();
    }

    @Override
    public JsonResult approval(List<Integer> ids){
        for(int id:ids){
            Tutor tutor = tutorMapper.selectByPrimaryKey(id);
            Long userId = tutor.getUserId();
            if(userId != 0) {
                userMapper.tutorFlagChange(userId);
            }
            tutorMapper.approvalTutor(id);
        }
        return JsonResult.success();
    }
}

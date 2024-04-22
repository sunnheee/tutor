package com.sh.sys.service.impl;

import com.sh.sys.dao.IndexConfigMapper;
import com.sh.sys.dao.TutorSubMapper;
import com.sh.sys.model.TutorSub;
import com.sh.sys.service.IndexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    private IndexConfigMapper indexConfigMapper;

    @Resource
    private TutorSubMapper tutorSubMapper;

    @Override
    public List<TutorSub> getTutorByIndexConfigType(byte type) {
        List<Integer> ids = indexConfigMapper.selectSubIdsByConfigType(type);
        List<TutorSub> subList = tutorSubMapper.selectByIds(ids);
        return subList;
    }
}

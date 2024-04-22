package com.sh.sys.service.impl;

import com.sh.sys.dao.*;
import com.sh.sys.model.Tutor;
import com.sh.sys.model.TutorSub;
import com.sh.sys.service.DetailService;
import com.sh.sys.vo.DetailVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DetailServiceImpl implements DetailService {


    @Resource
    private TutorSubMapper tutorSubMapper;

    @Resource
    private UserCollMapper userCollMapper;

    @Resource
    private UserWishMapper userWishMapper;

    @Resource
    private TutorMapper tutorMapper;

    @Resource
    private SubCategoryMapper subCategoryMapper;

    @Override
    public DetailVo getDetailVo(int tutorSubId){
        DetailVo vo = new DetailVo();
        TutorSub sub = tutorSubMapper.selectById(tutorSubId);
        Tutor tutor = tutorMapper.selectById(sub.getTutorId());
        int collCount = userCollMapper.selectCountByTutorSubId(tutorSubId);
        int wishCount = userWishMapper.selectCountByTutorSubId(tutorSubId);
        int pid = subCategoryMapper.selectParentIdByCategoryId(sub.getSubCategoryId());
        switch (pid){
            case 1:
                vo.setSubject("高中"+sub.getSubName());break;
            case 2:
                vo.setSubject("初中"+sub.getSubName());break;
            case 3:
                vo.setSubject("小学"+sub.getSubName());break;
            case 4:
                vo.setSubject(sub.getSubName());break;
            default:
                break;
        }
        vo.setAge(tutor.getTutorAge());
        vo.setArea(tutor.getArea());
        vo.setCollectionCount(collCount);
        vo.setPrice(sub.getPriceLesson());
        vo.setState(sub.getStatus());
        vo.setGender(tutor.getTutorGender());
        vo.setTutorName(tutor.getTutorName());
        vo.setPhone(tutor.getTutorPhone());
        vo.setTutorSubId(tutorSubId);
        vo.setWishCount(wishCount);
        vo.setTutorImg(tutor.getTutorPhoto());
        vo.setIntro(tutor.getBriefIntroduction());
        return vo;
    }
}

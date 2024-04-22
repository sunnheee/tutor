package com.sh.sys.service.impl;

import com.sh.sys.dao.SubCategoryMapper;
import com.sh.sys.model.SubCategory;
import com.sh.sys.service.SubCategoryService;
import com.sh.sys.vo.SubCategoryVo;
import com.sh.sys.vo.TagVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Resource
    private SubCategoryMapper subCategoryMapper;

    @Override
    public List<SubCategoryVo> getCategoryMenu() {
        List<SubCategory> l = subCategoryMapper.selectAllContent();

        List<SubCategoryVo> voList = new ArrayList<>();
        // K-V 等级-分类
        Map<Byte, List<SubCategory>> levelMap = l.stream().collect(Collectors.groupingBy(SubCategory::getCategoryLevel));
        // K-V 一级ID-所有二级分类
        Map<Integer, List<SubCategory>> relationMap = levelMap.get((byte)2).stream().collect(Collectors.groupingBy(SubCategory::getParentId));
        List<SubCategory> level1 = levelMap.get((byte)1).stream().sorted((c1, c2) -> c2.getCategoryRank() - c1.getCategoryRank()).collect(Collectors.toList());



        for(int i=0;i<level1.size();i++){
            SubCategory subCategory = level1.get(i);
            //根据一级分类去取二级
            List<SubCategory> secondSubCategorys = relationMap.get(subCategory.getCategoryId()).stream().sorted((c1, c2) -> c2.getCategoryRank() - c1.getCategoryRank()).collect(Collectors.toList());

            List<SubCategoryVo> secVoList = secondSubCategorys.stream().map(category -> {
                SubCategoryVo vo = new SubCategoryVo();
                vo.setCategoryName(category.getSubName());
                vo.setCategoryId(category.getCategoryId());
                return vo;
            }).collect(Collectors.toList());
            SubCategoryVo vo = new SubCategoryVo();
            vo.setCategoryId(subCategory.getCategoryId());
            vo.setCategoryName(subCategory.getSubName());
            vo.setSonList(secVoList);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public List<TagVo> getHotTags(){
        List<TagVo> tagList = new ArrayList<>();
        List<SubCategory> subCategories = subCategoryMapper.selectAllContent();
        Map<Byte, List<SubCategory>> map = subCategories.stream().collect(Collectors.groupingBy(SubCategory::getCategoryLevel));
        List<SubCategory> sortSubs = map.get((byte)2).stream().sorted((c1, c2) -> c2.getCategoryRank() - c1.getCategoryRank()).collect(Collectors.toList());
        int count = sortSubs.size()>10 ? 10:sortSubs.size();
        for(int i=0;i<count;i++){
            TagVo vo = new TagVo();
            SubCategory tmp = sortSubs.get(i);
            vo.setSubId(tmp.getCategoryId());
            switch (tmp.getParentId()){
                case 1:
                    vo.setTagName("高中"+tmp.getSubName());break;
                case 2:
                    vo.setTagName("初中"+tmp.getSubName());break;
                case 3:
                    vo.setTagName("小学"+tmp.getSubName());break;
                case 4:
                    vo.setTagName(tmp.getSubName());break;
                default:
                    break;
            }
            tagList.add(vo);
        }
        return tagList;
    }
}

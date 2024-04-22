package com.sh.sys.service;

import com.sh.sys.vo.SubCategoryVo;
import com.sh.sys.vo.TagVo;

import java.util.List;

public interface SubCategoryService {

     List<SubCategoryVo> getCategoryMenu();

     List<TagVo> getHotTags();
}

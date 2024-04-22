package com.sh.sys.vo;

import lombok.Data;

import java.util.List;

@Data
public class SubCategoryVo {

    private int categoryId;
    private String categoryName;
    private List<SubCategoryVo> sonList;

}

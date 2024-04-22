package com.sh.sys.model;

import lombok.Data;

import java.util.Date;

@Data
public class TutorSub {
    private Integer tutorSubId;

    private Integer subCategoryId;

    private String subName;

    private Integer priceLesson;

    private Integer tutorId;

    private String tutorName;

    private String tutorPhoto;

    private Date createTime;

    private Integer createAdminId;

    private Date updateTime;

    private Integer updateAdminId;

    private Integer status;

    private Integer isExpire;

}
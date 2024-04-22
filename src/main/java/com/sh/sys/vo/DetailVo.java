package com.sh.sys.vo;

import lombok.Data;

@Data
public class DetailVo {

    private Integer tutorSubId;

    private Integer state;

    private String TutorName;

    private String tutorImg;

    private Integer price;

    private String subject;

    private String gender;

    private Integer age;

    private String area;

    private int wishCount;

    private int collectionCount;

    private String phone;

    private String intro;
}

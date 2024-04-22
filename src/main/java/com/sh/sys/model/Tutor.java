package com.sh.sys.model;

import lombok.Data;

import java.util.Date;

@Data
public class Tutor {
    private Integer tutorId;

    private String tutorName;

    private String tutorGender;

    private String tutorPhoto;

    private Integer tutorAge;

    private String tutorPhone;

    private String area;

    private Byte effective;

    private String briefIntroduction;

    private Date createTime;

    private Date updateTime;

    private Byte isExpire;

    private Byte lock;

    private Integer createAdminId;

    private Integer updateAdminId;

    private Long userId;
}
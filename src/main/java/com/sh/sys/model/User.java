package com.sh.sys.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long userId;

    private String loginPhone;

    private String password;

    private String nickname;

    private String phone;

    private String userEmail;

    private String briefIntroduction;

    private String avatarUrl;

    private Byte tutorFlag;

    private Integer score;

    private Date createTime;

    private Date updateTime;

    private Byte isExpire;

    private Byte lock;
}
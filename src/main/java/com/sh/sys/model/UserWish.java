package com.sh.sys.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserWish {
    private Long id;

    private Long userId;

    private Integer tutorSubId;

    private Byte idDeleted;

    private Date createTime;

    private Date updateTime;

}
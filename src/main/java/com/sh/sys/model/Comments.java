package com.sh.sys.model;

import lombok.Data;

import java.util.Date;

@Data
public class Comments {
    private Integer commentId;

    private Long userId;

    private Integer subId;

    private String content;

    private Integer likeNum;

    private Byte isDeleted;

    private Date createTime;

    private Date updateTime;
}
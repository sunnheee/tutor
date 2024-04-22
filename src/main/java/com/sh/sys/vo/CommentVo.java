package com.sh.sys.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVo {

    private String content;

    private Integer likeNum;

    private Date createTime;

    private String userAvatar;

    private String userName;

    private int commentId;
}

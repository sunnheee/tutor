package com.sh.sys.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private int currentPage;
    private int totalPage;
    private List<T> list;
}

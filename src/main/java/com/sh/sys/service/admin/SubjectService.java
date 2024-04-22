package com.sh.sys.service.admin;

import com.sh.sys.vo.JsonResult;

import java.util.List;

public interface SubjectService {


    JsonResult list(int page, int limit);

    JsonResult status(int status, List<Integer> ids);
}

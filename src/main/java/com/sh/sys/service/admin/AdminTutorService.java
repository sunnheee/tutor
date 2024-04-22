package com.sh.sys.service.admin;

import com.sh.sys.vo.JsonResult;

import java.util.List;

public interface AdminTutorService {


    JsonResult list(int page, int limit);

    JsonResult delete(List<Integer> ids);

    JsonResult approval(List<Integer> ids);
}

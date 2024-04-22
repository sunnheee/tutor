package com.sh.sys.service.admin;

import com.sh.sys.model.IndexConfig;
import com.sh.sys.vo.JsonResult;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ConfigService {

    JsonResult configType(byte configType, int page, int limit);

    JsonResult addConfig(IndexConfig indexConfig, HttpSession session);

    JsonResult update(IndexConfig indexConfig);

    JsonResult delete(List<Integer> ids);
}

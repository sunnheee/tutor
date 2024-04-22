package com.sh.sys.service.admin;

import com.sh.sys.model.SubCategory;
import com.sh.sys.vo.JsonResult;


import javax.servlet.http.HttpSession;
import java.util.List;

public interface AdminCategoryService {

   JsonResult list(int categoryLevel, int parentId, int page, int limit);

   JsonResult add(SubCategory category, HttpSession session);

   JsonResult delete(List<Integer> ids);

   JsonResult update(SubCategory category);
}

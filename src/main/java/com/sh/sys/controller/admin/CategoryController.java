package com.sh.sys.controller.admin;

import com.sh.sys.model.SubCategory;
import com.sh.sys.service.admin.impl.AdminCategoryServiceImpl;
import com.sh.sys.vo.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class CategoryController {

    @Resource
    private AdminCategoryServiceImpl categoryService;

    @GetMapping("categories/list")
    public JsonResult getList(int categoryLevel, int parentId, int page, int limit){
        return categoryService.list(categoryLevel,parentId,page,limit);
    }

    @PostMapping("categories/save")
    public JsonResult add(@RequestBody SubCategory category, HttpSession session){
        return categoryService.add(category,session);
    }

    @PostMapping("categories/delete")
    public JsonResult delete(@RequestBody List<Integer> ids) {
        return categoryService.delete(ids);
    }

    @PostMapping("categories/update")
    public JsonResult update(@RequestBody SubCategory category){
       return categoryService.update(category);
    }
}

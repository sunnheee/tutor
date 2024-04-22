package com.sh.sys.controller.admin;


import com.sh.sys.dao.SubCategoryMapper;
import com.sh.sys.dao.TutorSubMapper;
import com.sh.sys.model.SubCategory;
import com.sh.sys.model.TutorSub;
import com.sh.sys.service.admin.impl.SubjectServiceImpl;
import com.sh.sys.vo.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class SubjectController {


    @Resource
    private SubjectServiceImpl subjectService;

    @Resource
    private SubCategoryMapper subCategoryMapper;

    @Resource
    private TutorSubMapper tutorSubMapper;

    @GetMapping("subs/list")
    @ResponseBody
    public JsonResult list(int page,int limit){
        return subjectService.list(page,limit);
    }

    @PutMapping("subs/status/{status}")
    @ResponseBody
    public JsonResult status(@PathVariable("status") int status,
                             @RequestBody List<Integer> ids){
        return subjectService.status(status,ids);
    }

    @GetMapping("subs/edit")
    public String edit(Model model,@RequestParam(defaultValue = "0") int tutorSubId){
        List<SubCategory> firstLevel = subCategoryMapper.selectByLevel(1);
        List<SubCategory> secondLevel = subCategoryMapper.selectByLevel(2);
        if(tutorSubId > 0){
            TutorSub sub = tutorSubMapper.selectByPrimaryKey(tutorSubId);
            int id2 = sub.getSubCategoryId();
            SubCategory cat2 = subCategoryMapper.selectById(id2);
            int id1 = cat2.getParentId();
            model.addAttribute("sub",sub);
            model.addAttribute("firstLevelCategoryId",id1);
            model.addAttribute("secondLevelCategoryId",id2);
        }
        model.addAttribute("firstLevelCategories",firstLevel);
        model.addAttribute("secondLevelCategories",secondLevel);
        return "admin/edit";
    }

    @PostMapping("subs/save")
    @ResponseBody
    public JsonResult save(@RequestBody TutorSub tutorSub){
        tutorSub.setCreateTime(new Date());
        tutorSub.setUpdateTime(new Date());
        tutorSub.setCreateAdminId(0);
        tutorSub.setUpdateAdminId(0);
        tutorSub.setIsExpire(0);
        tutorSubMapper.insert(tutorSub);
        return JsonResult.success();
    }

    @PostMapping("subs/update")
    @ResponseBody
    public JsonResult update(@RequestBody TutorSub tutorSub){
        tutorSubMapper.updateByPrimaryKeySelective(tutorSub);
        return JsonResult.success();
    }

    @GetMapping("categories/listForSelect")
    @ResponseBody
    public JsonResult categoryChanged(int categoryId){
        Map<String,List<SubCategory>> result = new HashMap<>();
        List<SubCategory> second = subCategoryMapper.selectByParentId(categoryId);
        result.put("secondLevelCategories",second);
        return JsonResult.success(result);
    }
}

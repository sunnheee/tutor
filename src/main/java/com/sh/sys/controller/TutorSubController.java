package com.sh.sys.controller;

import com.sh.sys.model.TutorSub;
import com.sh.sys.service.impl.CommentServiceImpl;
import com.sh.sys.service.impl.DetailServiceImpl;
import com.sh.sys.service.impl.SubCategoryServiceImpl;
import com.sh.sys.service.impl.TutorSubServiceImpl;
import com.sh.sys.vo.JsonResult;
import com.sh.sys.vo.PageResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class TutorSubController {

    @Resource
    private TutorSubServiceImpl tutorSubService;

    @Resource
    private SubCategoryServiceImpl subCategoryService;

    @Resource
    private DetailServiceImpl detailService;

    @Resource
    private CommentServiceImpl commentService;

    @GetMapping("searchByCat")
    public String searchByCategoryId(Model model, int categoryId, @RequestParam(defaultValue = "1") int page) {
        model.addAttribute("pageResult", tutorSubService.searchByCat(categoryId, page));
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categorys", subCategoryService.getCategoryMenu());
        model.addAttribute("popularTags", subCategoryService.getHotTags());
        return "search";
    }

    @GetMapping("searchByKeyword")
    public String searchByKeyword(Model model, String keyword, @RequestParam(defaultValue = "1") int page) {
        model.addAttribute("pageResult", tutorSubService.searchByKeyword(keyword, page));
        model.addAttribute("keyword", keyword);
        model.addAttribute("categorys", subCategoryService.getCategoryMenu());
        model.addAttribute("popularTags", subCategoryService.getHotTags());
        return "search";
    }

    @GetMapping("collectThing")
    public String collectThingView(Model model, HttpSession session, @RequestParam(defaultValue = "1") int page) {
        model.addAttribute("pageResult", tutorSubService.getCollListByUser(session, page));
        return "collectThing";
    }

    @GetMapping("wish")
    public String wishView(Model model, HttpSession session, @RequestParam(defaultValue = "1") int page) {
        model.addAttribute("pageResult", tutorSubService.getWishListByUser(session, page));
        return "wish";
    }

    @GetMapping("detail")
    public String DetailView(Model model, int subId) {
        model.addAttribute("vo", detailService.getDetailVo(subId));
        model.addAttribute("comments", commentService.getComments(subId));
        int count = commentService.getCount(subId);
        model.addAttribute("count", count);
        return "detail";
    }

    @PostMapping("addSubject")
    @ResponseBody
    public JsonResult addSubject(@RequestBody TutorSub tutorSub, HttpSession session) {
        return tutorSubService.addSubject(tutorSub, session);
    }

    @PostMapping("updateSubject")
    @ResponseBody
    public JsonResult updateSubject(@RequestBody TutorSub tutorSub, HttpSession session) {
        return tutorSubService.updateSubject(tutorSub, session);
    }

    @GetMapping("user-center/mineSubjects")
    public String mineSubjects(Model model,HttpSession session,@RequestParam(defaultValue = "1") int page){
        model.addAttribute("pageResult",tutorSubService.mineSubjects(session,page));
        return "mineSubjects";
    }

    @PostMapping("deleteSubject")
    @ResponseBody
    public JsonResult deleteSubject(int id) {
        return tutorSubService.deleteSubject(id);
    }

}

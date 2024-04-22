package com.sh.sys.service;

import com.sh.sys.model.TutorSub;
import com.sh.sys.vo.JsonResult;
import com.sh.sys.vo.PageResult;

import javax.servlet.http.HttpSession;

public interface TutorSubService {

    PageResult<TutorSub> searchByCat(int categoryId, int page);

    PageResult<TutorSub> searchByKeyword(String keyword,int page);

    PageResult<TutorSub> getCollListByUser(HttpSession session, int page);

    PageResult<TutorSub> getWishListByUser(HttpSession session,int page);

    JsonResult addSubject(TutorSub tutorSub,HttpSession session);

    JsonResult updateSubject(TutorSub tutorSub,HttpSession session);

    PageResult<TutorSub> mineSubjects(HttpSession session,int page);

    JsonResult deleteSubject(int id);
}

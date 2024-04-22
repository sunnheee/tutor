package com.sh.sys.service.impl;

import com.sh.sys.constants.Constants;
import com.sh.sys.dao.TutorMapper;
import com.sh.sys.dao.TutorSubMapper;
import com.sh.sys.dao.UserCollMapper;
import com.sh.sys.dao.UserWishMapper;
import com.sh.sys.model.TutorSub;
import com.sh.sys.model.User;
import com.sh.sys.service.TutorSubService;
import com.sh.sys.vo.JsonResult;
import com.sh.sys.vo.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class TutorSubServiceImpl implements TutorSubService {

    @Resource
    private TutorSubMapper tutorSubMapper;

    @Resource
    private TutorMapper tutorMapper;

    @Resource
    private UserCollMapper userCollMapper;

    @Resource
    private UserWishMapper userWishMapper;

    @Override
    public PageResult<TutorSub> searchByCat(int categoryId, int page) {
        int count = tutorSubMapper.selectCountByCatId(categoryId);
        int offset = (page-1)*12;
        int totalPage = count/12;
        if(count%12!=0){
            totalPage++;
        }
        List<TutorSub> list = tutorSubMapper.selectByCatId(categoryId,offset,12);
        PageResult<TutorSub> res = new PageResult<>();
        res.setCurrentPage(page);
        res.setList(list);
        res.setTotalPage(totalPage);
        return res;
    }

    @Override
    public PageResult<TutorSub> searchByKeyword(String keyword, int page) {
        int count = tutorSubMapper.selectCountByKeyword(keyword);
        int offset = (page-1)*12;
        int totalPage = count/12;
        if(count%12!=0){
            totalPage++;
        }
        List<TutorSub> list = tutorSubMapper.selectByKeyword(keyword, offset, 12);
        PageResult<TutorSub> res = new PageResult<>();
        res.setCurrentPage(page);
        res.setList(list);
        res.setTotalPage(totalPage);
        return res;
    }

    @Override
    public PageResult<TutorSub> getCollListByUser(HttpSession session, int page) {
        User u = (User) session.getAttribute(Constants.LOGIN_USER_KEY);
        Long userId = u.getUserId();
        List<Integer> ids = userCollMapper.getIdsByUserId(userId);
        int count = ids.size();
        int offset = (page-1)*12;
        int totalPage = count/12;
        if(count%12!=0){
            totalPage++;
        }
        PageResult<TutorSub> pr = new PageResult<>();
        List<TutorSub> subList = tutorSubMapper.selectByIdsAndLimit(ids,offset,12);
        pr.setTotalPage(totalPage);
        pr.setList(subList);
        pr.setCurrentPage(page);
        return pr;
    }

    @Override
    public PageResult<TutorSub> getWishListByUser(HttpSession session, int page) {
        User u = (User) session.getAttribute(Constants.LOGIN_USER_KEY);
        Long userId = u.getUserId();
        List<Integer> ids = userWishMapper.getIdsByUserId(userId);
        int count = ids.size();
        int offset = (page-1)*6;
        int totalPage = count/6;
        if(count%6!=0){
            totalPage++;
        }
        PageResult<TutorSub> pr = new PageResult<>();
        List<TutorSub> subList = tutorSubMapper.selectByIdsAndLimit(ids,offset,6);
        pr.setTotalPage(totalPage);
        pr.setList(subList);
        pr.setCurrentPage(page);
        return pr;
    }

    @Override
    public JsonResult addSubject(TutorSub tutorSub,HttpSession session){
        User u = (User) session.getAttribute(Constants.LOGIN_USER_KEY);
        tutorSub.setUpdateAdminId(9527);
        tutorSub.setCreateAdminId(9527);
        tutorSub.setCreateTime(new Date());
        tutorSub.setUpdateTime(new Date());
        tutorSub.setTutorId(tutorMapper.selectIdByUserId(u.getUserId()));
        tutorSub.setIsExpire(0);
        tutorSubMapper.insert(tutorSub);
        return JsonResult.success();
    }

    @Override
    public JsonResult updateSubject(TutorSub tutorSub,HttpSession session){
        tutorSub.setUpdateTime(new Date());
        tutorSubMapper.updateByPrimaryKeySelective(tutorSub);
        return JsonResult.success();
    }

    @Override
    public PageResult<TutorSub> mineSubjects(HttpSession session,int page){
        User u = (User) session.getAttribute(Constants.LOGIN_USER_KEY);
        int tutorId = tutorMapper.selectIdByUserId(u.getUserId());
        int count = tutorSubMapper.selectCountByTutorId(tutorId);
        int offset = (page-1)*6;
        int totalPage = count/6;
        if(count%6!=0){
            totalPage++;
        }
        List<TutorSub> list = tutorSubMapper.selectByTutorId(tutorId, offset, 6);
        PageResult<TutorSub> pr = new PageResult<>();
        pr.setTotalPage(totalPage);
        pr.setList(list);
        pr.setCurrentPage(page);
        return pr;
    }

    @Override
    public JsonResult deleteSubject(int id){
        tutorSubMapper.deleteById(id);
        return JsonResult.success();
    }
}

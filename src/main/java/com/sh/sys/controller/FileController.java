package com.sh.sys.controller;

import com.sh.sys.constants.Constants;
import com.sh.sys.model.User;
import com.sh.sys.service.impl.UploadServiceImpl;
import com.sh.sys.vo.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
public class FileController {

    @Resource
    private UploadServiceImpl uploadService;

    @PostMapping("upload/avatarFile")
    public Map<String,Object> upload(MultipartFile file,HttpSession session){
        System.out.println(file.getOriginalFilename());
        Map<String,Object> map = new HashMap<>();
        try {
            String url = uploadService.upload(file.getInputStream());
            System.out.println(url);
            session.setAttribute("uploadAvatar",url);
            map.put("resultCode", 0);
            map.put("data", url);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    //上传多张图片
    @PostMapping("upload/files")
    public Map<String,Object> uploadFiles(HttpServletRequest req){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(req.getSession().getServletContext());

        if(!multipartResolver.isMultipart(req)){
            return null;
        }

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
        List<String> fileUrl = new ArrayList<>();
        Iterator<String> fileNames = multiRequest.getFileNames();
        while(fileNames.hasNext()) {
            String name = fileNames.next();
            MultipartFile file = multiRequest.getFile(name);
            try {
                String url = uploadService.upload(file.getInputStream());
                fileUrl.add(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("resultCode",0);
        map.put("data",fileUrl);
        return map;
    }

    @PostMapping("/user-center/getUploadAvatarUrl")
    public JsonResult getAvatarUrl(HttpSession session) {
        String avatarUrl = (String) session.getAttribute("uploadAvatar");
        return JsonResult.success(avatarUrl);
    }
    @PostMapping("/user-center/getDefaultAvatarUrl")
    public JsonResult getDefaultUrl(HttpSession session){
        User u = (User) session.getAttribute(Constants.LOGIN_USER_KEY);
        String avatarUrl = u.getAvatarUrl();
        System.out.println(avatarUrl);
        if("".equals(avatarUrl) || avatarUrl == null){
            return JsonResult.success("../images/img-upload.png");
        }else {
            return JsonResult.success(avatarUrl);
        }
    }

    @PostMapping("upload/photoFile")
    public Map<String,Object> uploadPhoto(MultipartFile file,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        try {
            String url = uploadService.upload(file.getInputStream());
            System.out.println(url);
            session.setAttribute("photoUrl",url);
            map.put("resultCode", 0);
            map.put("data", url);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping("/user-center/getUploadPhotoUrl")
    public JsonResult getPhotoUrl(HttpSession session) {
        String photoUrl = (String) session.getAttribute("photoUrl");
        return JsonResult.success(photoUrl);
    }

    @PostMapping("/user-center/getDefaultPhotoUrl")
    public JsonResult getDefaultPhotoUrl(){
        return JsonResult.success("../images/img-upload.png");
    }

    @PostMapping("upload/file")
    public Map<String,Object> upload(MultipartFile file){
        Map<String,Object> map = new HashMap<>();
        try {
            String url = uploadService.upload(file.getInputStream());
            System.out.println(url);
            map.put("resultCode", 0);
            map.put("data", url);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}

package com.baizhi.cxx.controller;


import com.baizhi.cxx.dao.VideoDao;
import com.baizhi.cxx.dto.UserDto;
import com.baizhi.cxx.dto.VideoDto;
import com.baizhi.cxx.entity.User;
import com.baizhi.cxx.entity.Video;
import com.baizhi.cxx.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/video")
public class VideoController {
    @Autowired
    VideoDao videoDao;
    @Autowired
    VideoService videoService;

    @RequestMapping("queryVideoByPage")
    @ResponseBody
    public VideoDto queryUserByPage(Integer rows, Integer page){
        VideoDto videoDto = videoService.queryVideoByPage(rows, page);
        return videoDto;
    }

    @RequestMapping("editVideo")
    @ResponseBody
    public String editVideo(Video video, String oper){
        String uid =null;
        if(oper.equals("add")){
            uid = videoService.addVideo(video);
        }
        if(oper.equals("edit")){
            //videoService.deleteVideoFile(video);
            uid=videoService.updateVideo(video);
        }
        if(oper.equals("del")){
            videoService.deleteVideoFile(video);
        }
        return uid;
    }

    @RequestMapping("uploadVideoFile")
    public void uploadVideoFile(String id, MultipartFile path, HttpServletRequest request){
        videoService.uploadVideoFile(id,path,request);
    }

}

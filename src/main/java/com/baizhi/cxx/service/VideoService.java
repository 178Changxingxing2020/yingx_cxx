package com.baizhi.cxx.service;

import com.baizhi.cxx.dto.UserDto;
import com.baizhi.cxx.dto.VideoDto;
import com.baizhi.cxx.entity.User;
import com.baizhi.cxx.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface VideoService {
    public VideoDto queryVideoByPage(Integer row, Integer page);
    public String addVideo(Video video);
    public void uploadVideoFile(String id, MultipartFile path, HttpServletRequest Request);
    public void deleteVideoFile(Video video);
//    public void uploadVideoStatus(String id);
    public String updateVideo(Video video);

}

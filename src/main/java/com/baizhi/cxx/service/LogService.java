package com.baizhi.cxx.service;

import com.baizhi.cxx.dto.LogDto;
import com.baizhi.cxx.dto.UserDto;
import com.baizhi.cxx.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface LogService {
    public LogDto queryLogByPage(Integer row, Integer page);
//    public String addUser(User user);
//    public void uploadUserFile(String id, MultipartFile headImg, HttpServletRequest Request);
//    public void uploadUserStatus(String id);
//    public String updateUser(User user);
}

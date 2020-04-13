package com.baizhi.cxx.service;

import com.baizhi.cxx.dto.UserDto;
import com.baizhi.cxx.entity.User;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    public UserDto queryUserByPage(Integer row, Integer page);
    public String addUser(User user);
    public void uploadUserFile(String id, MultipartFile headImg,HttpServletRequest Request);
    public void uploadUserStatus(String id);
    public String updateUser(User user);
}

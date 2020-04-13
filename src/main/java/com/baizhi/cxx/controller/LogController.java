package com.baizhi.cxx.controller;


import com.baizhi.cxx.dao.LogDao;
import com.baizhi.cxx.dao.UserDao;
import com.baizhi.cxx.dto.LogDto;
import com.baizhi.cxx.dto.UserDto;
import com.baizhi.cxx.entity.User;
import com.baizhi.cxx.service.LogService;
import com.baizhi.cxx.service.UserService;
import com.baizhi.cxx.util.AliyunSendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@Controller
@RequestMapping("/log")
public class LogController {
    @Autowired
    LogDao logDao;
    @Autowired
    LogService logService;


    @RequestMapping("queryLogByPage")
    @ResponseBody
    public LogDto queryLogByPage(Integer rows, Integer page){
        LogDto logDto = logService.queryLogByPage(rows, page);
        return logDto;
    }

//
//    @RequestMapping("editUser")
//    @ResponseBody
//    public String editUser(User user,String oper){
//        String uid =null;
//        if(oper.equals("add")){
//             uid = userService.addUser(user);
//        }
//        if(oper.equals("edit")){
//             uid = userService.updateUser(user);
//        }
//        if(oper.equals("del")){
//            userDao.deleteByPrimaryKey(user);
//        }
//        return uid;
//    }
//
//    @RequestMapping("SendSmsCode")
//    @ResponseBody
//    public HashMap<String,Object> SendSmsCode(String phone){
//        System.out.println("----进入发送----");
//        System.out.println(phone);
//        AliyunSendSms aliyunSendSms = new AliyunSendSms();
//        String random = aliyunSendSms.getRandom(6);
//        System.out.println(random);
//        HashMap<String, Object> map = aliyunSendSms.SendSms(phone,random);
//        map.put("code",random);
//        return map;
//    }
//
//    @RequestMapping("uploadUserFile")
//    public void uploadUserFile(String id, MultipartFile headImg, HttpServletRequest request){
//        userService.uploadUserFile(id,headImg,request);
//        //return null;
//    }
//
//    @RequestMapping("uploadUserStatus")
//    @ResponseBody
//    public void uploadUserStatus(String id){
//        System.out.println(id);
//        userService.uploadUserStatus(id);
//    }
}

package com.baizhi.cxx.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.cxx.dao.UserDao;
import com.baizhi.cxx.dto.UserDto;
import com.baizhi.cxx.entity.User;
import com.baizhi.cxx.service.UserService;
import com.baizhi.cxx.util.AliyunSendSms;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;


    @RequestMapping("queryUserByPage")
    @ResponseBody
    public UserDto queryUserByPage(Integer rows, Integer page){
        UserDto userDto = userService.queryUserByPage(rows, page);
        return userDto;
    }

    @RequestMapping("exportUserFile")
    @ResponseBody
    public void exportUserFile(){
        List<User> userList = userDao.selectAll();
        for (User user : userList) {
            String headImg = user.getHeadImg();
            user.setHeadImg("src/main/webapp/upload/img/"+headImg);
            System.out.println(user);
        }


        ExportParams exportParams = new ExportParams("应学用户信息表", "user");

        //参数：标题，表名，实体类类对象，导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,User.class,userList);

        try {
            workbook.write(new FileOutputStream(new File("E:\\yingx-user1.xls")));

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("editUser")
    @ResponseBody
    public String editUser(User user,String oper){
        String uid =null;
        if(oper.equals("add")){
             uid = userService.addUser(user);
        }
        if(oper.equals("edit")){
             uid = userService.updateUser(user);
        }
        if(oper.equals("del")){
            userDao.deleteByPrimaryKey(user);
        }
        return uid;
    }

    @RequestMapping("SendSmsCode")
    @ResponseBody
    public HashMap<String,Object> SendSmsCode(String phone){
        System.out.println("----进入发送----");
        System.out.println(phone);
        AliyunSendSms aliyunSendSms = new AliyunSendSms();
        String random = aliyunSendSms.getRandom(6);
        System.out.println(random);
        HashMap<String, Object> map = aliyunSendSms.SendSms(phone,random);
        map.put("code",random);
        return map;
    }

    @RequestMapping("uploadUserFile")
    public void uploadUserFile(String id, MultipartFile headImg, HttpServletRequest request){
        userService.uploadUserFile(id,headImg,request);
        //return null;
    }

    @RequestMapping("uploadUserStatus")
    @ResponseBody
    public void uploadUserStatus(String id){
        System.out.println(id);
        userService.uploadUserStatus(id);
    }
}

package com.baizhi.cxx.app;


import com.baizhi.cxx.common.CommonResult;
import com.baizhi.cxx.service.VideoService;
import com.baizhi.cxx.util.AliyunSendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("app")
public class AppInterfaceController {

    @Autowired
    VideoService videoService;



    /*发送验证码的接口*/
    @RequestMapping("getPhoneCode")
    public CommonResult getPhoneCodes(String phone, HttpServletRequest request, HttpSession session){

        AliyunSendSms aliyunSendSms = new AliyunSendSms();
        //生成随机数
        String random = aliyunSendSms.getRandom(6);
        System.out.println("存储验证码："+random);
        //发送验证码
        HashMap<String, Object> map = aliyunSendSms.SendSms(phone, random);
        System.out.println(map);
        CommonResult commonResult = null;
        if (map.get("message").toString().equals("发送成功")){

            commonResult = new CommonResult("100", "发送成功", phone);
        }
        else {

            commonResult = new CommonResult("100", "发送成功", phone);
        }


        return commonResult;
    }
}

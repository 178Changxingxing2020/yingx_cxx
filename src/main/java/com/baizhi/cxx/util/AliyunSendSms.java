package com.baizhi.cxx.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Random;

public class AliyunSendSms {
    public  HashMap<String,Object> SendSms(String phoneNumbers, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FcajTRNQHdLfJbZocNc", "F5lT1Xy5s6Orc22LSUE23a7yqak620");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", "爱我中华app");
        request.putQueryParameter("TemplateCode", "SMS_186968690");
        request.putQueryParameter("TemplateParam", "{\"code\":"+code+"}");
        CommonResponse response =null;

        HashMap<String, Object> map = new HashMap<>();
        try {
             response = client.getCommonResponse(request);
            System.out.println(response.getData());
            map.put("data",response.getData());
            map.put("message","发送成功");

        } catch (ServerException e) {
            e.printStackTrace();
            map.put("message","发送失败");
        } catch (ClientException e) {
            e.printStackTrace();
            map.put("message","发送失败");
        }

        return map;
    }

    //生成随机数
    public String  getRandom(int n){
        char[] code =  "0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(code[new Random().nextInt(code.length)]);
        }
        return sb.toString();
    }
}

package com.baizhi.cxx;

import com.baizhi.cxx.dao.AdminDao;
import com.baizhi.cxx.entity.Admin;
import com.baizhi.cxx.util.AliyunSendSms;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YingxCxxApplicationTests {

    @Autowired
    AdminDao adminDao;

    @Test
    public void contextLoads() {
        AliyunSendSms aliyunSendSms = new AliyunSendSms();
        HashMap<String, Object> map = aliyunSendSms.SendSms("18339161623", "123123");
    }

    @Test
    public void test(){
        List<Admin> list = adminDao.select(null);
        for (Admin admin : list) {
            System.out.println(admin);
        }

    }

}

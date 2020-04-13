package com.baizhi.cxx;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.baizhi.cxx.dao.AdminDao;
import com.baizhi.cxx.dao.UserDao;
import com.baizhi.cxx.entity.User;
import com.baizhi.cxx.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasypoiTests {

    @Autowired
    AdminDao adminDao;
    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;

    @Test
    public void exportFile(){
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
            workbook.write(new FileOutputStream(new File("E:\\yingx-user.xls")));

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void importFile(){
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);

        List<User> list=null;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("E:\\yingx-user.xls"));
            list = ExcelImportUtil.importExcel(fileInputStream, User.class, importParams);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (User user : list) {
            System.out.println(user);
        }

    }





}

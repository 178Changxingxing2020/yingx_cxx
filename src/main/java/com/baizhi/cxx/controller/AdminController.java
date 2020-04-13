package com.baizhi.cxx.controller;

import com.baizhi.cxx.dao.AdminDao;
import com.baizhi.cxx.entity.Admin;
import com.baizhi.cxx.util.CreateValidateCode;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminDao adminDao;

    @RequestMapping("login")
    @ResponseBody
    public Map login(HttpSession session, Admin admin,String enCode){
        String code = session.getAttribute("code").toString();
        HashMap<String, String> map = new HashMap<>();

        if(code.equals(enCode)){
            Admin admin1 = new Admin();
            admin1.setUsername(admin.getUsername());
            Admin admin2 = adminDao.selectOne(admin1);
            if(admin2!=null){
                if(admin.getPassword().equals(admin2.getPassword())){
                    session.setAttribute("admin",admin);
                    map.put("status","200");
                    map.put("message","登陆成功");
                }else {
                    map.put("status","400");
                    map.put("message","密码错误");
                }
            }else {
                map.put("status","400");
                map.put("message","用户名错误");
            }
        }else {
            map.put("status","400");
            map.put("message","验证码错误");
        }
        return map;

    }

    @RequestMapping("loginOut")
    public String loginOut(HttpServletRequest request){

        request.getSession().removeAttribute("admin");
        return "redirect:/login/login.jsp";
    }

    @RequestMapping("/getImageCode")
    public void getImageCode(HttpServletResponse response, HttpSession session){
        CreateValidateCode createValidateCode = new CreateValidateCode();
        String code = createValidateCode.getCode();
        try {
            createValidateCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.setAttribute("code",code);
    }
}

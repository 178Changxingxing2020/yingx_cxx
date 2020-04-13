package com.baizhi.cxx.aspect;


import com.baizhi.cxx.annotation.AddLog;
import com.baizhi.cxx.dao.LogDao;
import com.baizhi.cxx.entity.Admin;
import com.baizhi.cxx.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Aspect
@Configuration
public class LogAspect {

    @Autowired
    HttpSession session;
    @Autowired
    LogDao logDao;

    @Around("@annotation(com.baizhi.cxx.annotation.AddLog)")
    public Object addLogs(ProceedingJoinPoint proceedingJoinPoint){

        Admin admin = (Admin) session.getAttribute("admin");

        //时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formats = format.format(date);

        //操作   哪个方法
        String name = proceedingJoinPoint.getSignature().getName();

        //获取方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        //获取方法上的注解
        AddLog addLog = method.getAnnotation(AddLog.class);

        //获取注解中属性的值   value
        String value = addLog.value();



        Log log = null;
        String message=null;
        String id = UUID.randomUUID().toString().replace("-", "");
        try {
            Object proceed = proceedingJoinPoint.proceed();
            message="操作成功";
            //System.out.println("管理员："+admin.getUsername()+"--时间："+new Date()+"--操作："+methodName+"--状态："+message);

            log = new Log(id, admin.getUsername(), new Date(), value, message);
            logDao.insert(log);
            System.out.println("操作成功----------"+log);


            System.out.println("数据库插入");
            return proceed;
        } catch (Throwable throwable) {
            message="操作失败";
            //System.out.println("管理员："+admin.getUsername()+"--时间："+new Date()+"--操作："+methodName+"--状态："+message);
            log = new Log(id, admin.getUsername(), new Date(), value, message);
            System.out.println("操作失败----------"+log);
            logDao.insert(log);
            throwable.printStackTrace();
            return null;
        }


    }
//    @Around("execution(* com.baizhi.cxx.serviceImpl.*.*(..)) & execution(* !com.baizhi.cxx.serviceImpl.*.query*(..))")
//    public Object addLogs(ProceedingJoinPoint proceedingJoinPoint){
//
//        Admin admin = (Admin) session.getAttribute("admin");
//
//        //时间
//        Date date = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String formats = format.format(date);
//
//        //操作   哪个方法
//        String name = proceedingJoinPoint.getSignature().getName();
//
//        //获取方法
//        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
//        Method method = signature.getMethod();
//
//
//        String message=null;
//        try {
//            Object proceed = proceedingJoinPoint.proceed();
//            message="操作成功";
//            //System.out.println("管理员："+admin.getUsername()+"--时间："+new Date()+"--操作："+methodName+"--状态："+message);
//            System.out.println("数据库插入");
//            return proceed;
//        } catch (Throwable throwable) {
//            message="操作失败";
//            //System.out.println("管理员："+admin.getUsername()+"--时间："+new Date()+"--操作："+methodName+"--状态："+message);
//            throwable.printStackTrace();
//        }
//
//        return null;
//    }
}

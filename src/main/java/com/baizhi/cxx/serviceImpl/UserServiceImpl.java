package com.baizhi.cxx.serviceImpl;

import com.baizhi.cxx.annotation.AddLog;
import com.baizhi.cxx.dao.AdminDao;
import com.baizhi.cxx.dao.UserDao;
import com.baizhi.cxx.dto.UserDto;
import com.baizhi.cxx.entity.Admin;
import com.baizhi.cxx.entity.User;
import com.baizhi.cxx.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;

    @Override

    public UserDto queryUserByPage(Integer row, Integer page) {//总每页显示条数，当前页
//        private Integer page;   // 当前页
//        private Integer total;  // 总页数
//        private Integer records;    // 总行数
//        private List<User> rows;  //该页总数据行
        UserDto userDto = new UserDto();
        int size = userDao.selectCount(null);//总条数
        userDto.setTotal(size);
        userDto.setPage(page);//当前页
        int total=0;
        total=(size%row==0?size/row:size/row+1);
        userDto.setTotal(total);//设置总页数
        List<User> list = userDao.selectByRowBounds(null, new RowBounds((page - 1) * row, page * row));
        userDto.setRows(list);
        return userDto;
    }

    @Override
    @AddLog(value = "添加用户")
    public String addUser(User user) {
        user.setId(UUID.randomUUID().toString().replace("-",""));
        user.setCreateDate(new Date());
        user.setStatus("1");
        userDao.insert(user);
        return user.getId();
    }

    @AddLog(value = "修改用户")
    public String updateUser(User user){
        userDao.updateByPrimaryKeySelective(user);
        return user.getId();

    }


    public void uploadUserFile(String id, MultipartFile headImg, HttpServletRequest request){

        //根据相对路径获取绝对真实路径
        String realPath = request.getServletContext().getRealPath("/upload/img");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        //获取文件名
        String newName=new Date().getTime()+"-"+headImg.getOriginalFilename();

        try {
            //文件上传
            headImg.transferTo(new File(realPath,newName));

            //修改图片路径
            User user = new User();
            user.setId(id);
            user.setHeadImg(newName);
            userDao.updateByPrimaryKeySelective(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

       // return  null;
    }

    public void uploadUserStatus(String id){
        User user = new User();
        user.setId(id);
        User user1 = userDao.selectOne(user);
        if (user1.getStatus().equals("1")){
            user1.setStatus("0");
        }else{
            user1.setStatus("1");
        }
        userDao.updateByPrimaryKeySelective(user1);
        System.out.println(user1);
    }


}

package com.baizhi.cxx.serviceImpl;

import com.baizhi.cxx.dao.CategoryDao;
import com.baizhi.cxx.dao.UserDao;
import com.baizhi.cxx.dao.VideoDao;
import com.baizhi.cxx.dto.UserDto;
import com.baizhi.cxx.dto.VideoDto;
import com.baizhi.cxx.entity.Category;
import com.baizhi.cxx.entity.User;
import com.baizhi.cxx.entity.Video;
import com.baizhi.cxx.service.UserService;
import com.baizhi.cxx.service.VideoService;
import com.baizhi.cxx.util.AliyunOssUtil;
import com.baizhi.cxx.util.InterceptVideoPhotoUtil;
import com.sun.org.apache.regexp.internal.RE;
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

@Service("videoService")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoDao videoDao;
    @Autowired
    VideoService videoService;
    @Autowired
    CategoryDao categoryDao;

    @Override
    public VideoDto queryVideoByPage(Integer row, Integer page) {//总每页显示条数，当前页
//        private Integer page;   // 当前页
//        private Integer total;  // 总页数
//        private Integer records;    // 总行数
//        private List<User> rows;  //该页总数据行
        VideoDto videoDto = new VideoDto();
        int size = videoDao.selectCount(null);//总条数
        videoDto.setTotal(size);
        videoDto.setPage(page);//当前页
        int total=0;
        total=(size%row==0?size/row:size/row+1);
        videoDto.setTotal(total);//设置总页数
        List<Video> list = videoDao.selectByRowBounds(null, new RowBounds((page - 1) * row, page * row));
        for (Video video : list) {
            Category category = new Category();
            category.setId(video.getCategoryId());
            Category category1 = categoryDao.selectByPrimaryKey(category);
            video.setCategory(category1);
        }
        videoDto.setRows(list);
        return videoDto;
    }

    @Override
    public String addVideo(Video video) {
        video.setId(UUID.randomUUID().toString().replace("-",""));
        video.setPublishDate(new Date());
        video.setUserId("1");
        video.setCategoryId("1");
        video.setGroupId("1");
        videoDao.insert(video);
        return video.getId();
    }


    @Transactional
    public void uploadVideoFile(String id, MultipartFile path, HttpServletRequest Request){

        //上传到阿里云

        //获取文件名
        String filename = path.getOriginalFilename();
        String newName=new Date().getTime()+"-"+filename;
        /*1.视频上传至阿里云
         *上传字节数组
         * 参数：
         *   bucket:存储空间名
         *   headImg: 指定MultipartFile类型的文件
         *   fileName:  指定上传文件名  可以指定上传目录：  目录名/文件名
         * */
        AliyunOssUtil.uploadFileBytes("yingx-186-cxx",path,"video/"+newName);

        //频接视频完整路径
        String netFilePath="https://yingx-186-cxx.oss-cn-beijing.aliyuncs.com/video/"+newName;

        /*2.截取视频第一帧做封面
         * 获取指定视频的帧并保存为图片至指定目录
         * @param videofile 源视频文件路径
         * @param framefile 截取帧的图片存放路径
         * */
        String realPath = Request.getSession().getServletContext().getRealPath("/upload/cover");

        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }

        //频接本地存放路径    D:\动画.jpg
        // 1585661687777-好看.mp4
        String[] names = newName.split("\\.");
        String interceptName=names[0];
        String coverName=interceptName+".jpg";  //频接封面名字
        String coverPath= realPath+"\\"+coverName;  //频接视频封面的本地绝对路径


        //截取封面保存到本地
        try {
            InterceptVideoPhotoUtil.fetchFrame(netFilePath,coverPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*3.将封面上传至阿里云
         *上传本地文件
         * 参数：
         *   bucket:  存储空间名
         *   fileName:  指定上传文件名  可以指定上传目录：  目录名/文件名
         *   localFilePath: 指定本地文件路径
         * */
        AliyunOssUtil.uploadFile("yingx-186-cxx","photo/"+coverName,coverPath);


        //4.删除本地文件
        File file1 = new File(coverPath);
        //判断是一个文件，并且文件存在
        if(file1.isFile()&&file1.exists()){
            //删除文件
            boolean isDel = file1.delete();
            System.out.println("删除："+isDel);
        }

        //5.修改视频信息
        //添加修改条件
//        VideoExample example = new VideoExample();
//        example.createCriteria().andIdEqualTo(id);
        Video video2=new Video();
        video2.setId(id);

        //修改的结果
        //Video video = new Video();

        video2.setPath("https://yingx-186-cxx.oss-cn-beijing.aliyuncs.com/video/"+newName);
        video2.setCover("https://yingx-186-cxx.oss-cn-beijing.aliyuncs.com/photo/"+coverName);

        System.out.println("====调用修改方法====="+video2);

        //调用修改方法
        videoDao.updateByPrimaryKeySelective(video2);
    }

    @Transactional
    public void deleteVideoFile(Video video){
        Video video1 = videoDao.selectByPrimaryKey(video);

        videoDao.deleteByPrimaryKey(video);

        //字符串拆分
        String[] pathSplit = video1.getPath().split("/");
        String[] coverSplit = video1.getCover().split("/");


        String videoName=pathSplit[pathSplit.length-2]+"/"+pathSplit[pathSplit.length-1];
        String coverName=coverSplit[coverSplit.length-2]+"/"+coverSplit[coverSplit.length-1];

        System.out.println(videoName);
        System.out.println(coverName);

        /*2.删除视频
         * 删除阿里云文件
         * 参数：
         *   bucker: 存储空间名
         *   fileName:文件名    目录名/文件名
         * */
        AliyunOssUtil.delete("yingx-186-cxx",videoName);

        /*3.删除封面
         * 删除阿里云文件
         * 参数：
         *   bucker: 存储空间名
         *   fileName:文件名    目录名/文件名
         * */
        AliyunOssUtil.delete("yingx-186-cxx",coverName);

    }

    public String updateVideo(Video video){
        System.out.println("updateVideo"+video);
        Video video1 = videoDao.selectByPrimaryKey(video);

        //字符串拆分
        String[] pathSplit = video1.getPath().split("/");
        String[] coverSplit = video1.getCover().split("/");

        String videoName=pathSplit[pathSplit.length-2]+"/"+pathSplit[pathSplit.length-1];
        String coverName=coverSplit[coverSplit.length-2]+"/"+coverSplit[coverSplit.length-1];

        System.out.println(videoName);
        System.out.println(coverName);

        /*2.删除视频
         * 删除阿里云文件
         * 参数：
         *   bucker: 存储空间名
         *   fileName:文件名    目录名/文件名
         * */
        AliyunOssUtil.delete("yingx-186-cxx",videoName);

        /*3.删除封面
         * 删除阿里云文件
         * 参数：
         *   bucker: 存储空间名
         *   fileName:文件名    目录名/文件名
         * */
        AliyunOssUtil.delete("yingx-186-cxx",coverName);

        video.setPath(null);
        videoDao.updateByPrimaryKeySelective(video);

//        videoDao.deleteByPrimaryKey(video1);
//
//        video1.setBrief(video.getBrief());
//        video1.setTitle(video.getTitle());
//
//        addVideo(video1);


        return  video.getId();
    }

//
//    public String updateUser(User user){
//        userDao.updateByPrimaryKeySelective(user);
//        return user.getId();
//
//    }
//
//    public void uploadUserFile(String id, MultipartFile headImg, HttpServletRequest request){
//
//        //根据相对路径获取绝对真实路径
//        String realPath = request.getServletContext().getRealPath("/upload/img");
//        File file = new File(realPath);
//        if (!file.exists()){
//            file.mkdirs();
//        }
//        //获取文件名
//        String newName=new Date().getTime()+"-"+headImg.getOriginalFilename();
//
//        try {
//            //文件上传
//            headImg.transferTo(new File(realPath,newName));
//
//            //修改图片路径
//            User user = new User();
//            user.setId(id);
//            user.setHeadImg(newName);
//            userDao.updateByPrimaryKeySelective(user);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//       // return  null;
//    }
//
//    public void uploadUserStatus(String id){
//        User user = new User();
//        user.setId(id);
//        User user1 = userDao.selectOne(user);
//        if (user1.getStatus().equals("1")){
//            user1.setStatus("0");
//        }else{
//            user1.setStatus("1");
//        }
//        userDao.updateByPrimaryKeySelective(user1);
//        System.out.println(user1);
//    }


}

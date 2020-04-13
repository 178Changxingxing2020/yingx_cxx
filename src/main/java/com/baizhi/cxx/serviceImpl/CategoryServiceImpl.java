package com.baizhi.cxx.serviceImpl;

import com.baizhi.cxx.dao.CategoryDao;
import com.baizhi.cxx.dao.VideoDao;
import com.baizhi.cxx.dto.CategoryDto;
import com.baizhi.cxx.dto.UserDto;
import com.baizhi.cxx.entity.Category;
import com.baizhi.cxx.entity.User;
import com.baizhi.cxx.entity.Video;
import com.baizhi.cxx.service.CategoryService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service("categoryService")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;
    @Autowired
    CategoryService categoryService;
    @Autowired
    VideoDao videoDao;


    @Override
    public CategoryDto queryCategoryByPage(Integer rows, Integer page) {
        CategoryDto categoryDto = new CategoryDto();
        Category category1 = new Category();
        category1.setLevels("1");
        int size = categoryDao.selectCount(category1);//总条数
        categoryDto.setTotal(size);
        categoryDto.setPage(page);//当前页
        int total=0;
        total=(size%rows==0?size/rows:size/rows+1);
        categoryDto.setTotal(total);//设置总页数
        List<Category> list = categoryDao.selectByRowBounds(category1, new RowBounds((page - 1) * rows, page * rows));

        categoryDto.setRows(list);
        return categoryDto;
    }

    public CategoryDto querySecondCategoryByPage(Integer rows, Integer page,String id){
        CategoryDto categoryDto = new CategoryDto();
        Category category1 = new Category();
        category1.setLevels("2");
        category1.setParentId(id);
        int size = categoryDao.selectCount(category1);//总条数
        categoryDto.setTotal(size);
        categoryDto.setPage(page);//当前页
        int total=0;
        total=(size%rows==0?size/rows:size/rows+1);
        categoryDto.setTotal(total);//设置总页数
        List<Category> list = categoryDao.selectByRowBounds(category1, new RowBounds((page - 1) * rows, page * rows));
        categoryDto.setRows(list);
        return categoryDto;
    }

    @Transactional
    public String addCategory(Category category){
        //System.out.println(category);
        category.setId(UUID.randomUUID().toString().replace("-",""));
        categoryDao.insert(category);
        return category.getId();
    }
    @Transactional
    public String updateCategory(Category category){
        categoryDao.updateByPrimaryKey(category);
        return category.getId();
    }
    @Transactional
    public Integer deleteByPrimaryKey(Category category){
        Category category1 = new Category();
        category1.setParentId(category.getId());
        int size = categoryDao.select(category1).size();
        if(size==0){
            categoryDao.deleteByPrimaryKey(category);
        }
        return size;
    }
    @Transactional
    public Integer deleteSecondByPrimaryKey(Category category){

        Video video = new Video();
        video.setCategoryId(category.getId());
        int size = videoDao.selectCount(video);
//        Category category1 = new Category();
//        category1.setParentId(category.getId());
//        int size = categoryDao.select(category1).size();
        if(size==0){
            categoryDao.deleteByPrimaryKey(category);
        }
        return size;
    }


}

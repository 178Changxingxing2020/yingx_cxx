package com.baizhi.cxx.service;

import com.baizhi.cxx.dto.CategoryDto;
import com.baizhi.cxx.dto.UserDto;
import com.baizhi.cxx.entity.Category;
import com.baizhi.cxx.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface CategoryService {

      public CategoryDto queryCategoryByPage(Integer rows, Integer page);
      public CategoryDto querySecondCategoryByPage(Integer rows, Integer page,String id);
      public String addCategory(Category category);
      public String updateCategory(Category category);
      public Integer deleteByPrimaryKey(Category category);
      public Integer deleteSecondByPrimaryKey(Category category);

}

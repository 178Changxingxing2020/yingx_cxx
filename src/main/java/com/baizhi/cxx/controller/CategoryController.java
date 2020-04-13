package com.baizhi.cxx.controller;


import com.baizhi.cxx.dao.CategoryDao;
import com.baizhi.cxx.dto.CategoryDto;
import com.baizhi.cxx.entity.Category;
import com.baizhi.cxx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryDao categoryDao;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("queryFirstCategoryByPage")
    @ResponseBody
    public CategoryDto queryFirstCategoryByPage(Integer rows, Integer page){
        CategoryDto categoryDto = categoryService.queryCategoryByPage(rows, page);
        return  categoryDto;
    }
    @RequestMapping("querySecondCategory")
    @ResponseBody
    public CategoryDto querySecondCategory(Integer rows, Integer page,String id){
        CategoryDto categoryDto = categoryService.querySecondCategoryByPage(rows,page,id);
        return  categoryDto;
    }

    @RequestMapping("editFirstCategory")
    @ResponseBody
    public String editFirstCategory(Category category, String oper){
        String uid =null;
        if(oper.equals("add")){
            uid = categoryService.addCategory(category);
        }
        if(oper.equals("edit")){
           // uid = userService.updateUser(user);
             uid = categoryService.updateCategory(category);
        }
        if(oper.equals("del")){
            //userDao.deleteByPrimaryKey(user);
            uid = categoryService.deleteByPrimaryKey(category).toString();
        }
        return uid;
    }



    @RequestMapping("editSecondCategory")
    @ResponseBody
    public String editSecondCategory(Category category, String oper,String parentId){
        System.out.println("========"+category);
        System.out.println("========"+parentId);
        String uid =null;
        if(oper.equals("add")){
            uid = categoryService.addCategory(category);
        }
        if(oper.equals("edit")){
            // uid = userService.updateUser(user);
            uid = categoryService.updateCategory(category);
        }
        if(oper.equals("del")){
            //userDao.deleteByPrimaryKey(user);
            uid = categoryService.deleteSecondByPrimaryKey(category).toString();
        }
        return uid;
    }
}

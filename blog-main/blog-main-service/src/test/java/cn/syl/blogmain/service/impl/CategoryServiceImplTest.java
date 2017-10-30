package cn.syl.blogmain.service.impl;

import cn.syl.blogmain.pojo.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: syl  Date: 2017/10/30 Email:nerosyl@live.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvc
public class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryService;
    @Test
    public void saveCategory() throws Exception {
        Category category  = new Category();
        category.setName("java");
        category.setParentId("0");
        category.setHasParent(false);
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        categoryService.saveCategory(category);
    }

    @Test
    public void insertCategories() throws Exception {
        List<Category> list = new ArrayList<>();

        for (int i=0;i<100;i++){
            Category category  = new Category();
            category.setName("java"+i);
            category.setParentId("0"+i);
            category.setHasParent(false);
            category.setCreateTime(new Date());
            category.setUpdateTime(new Date());
            list.add(category);
        }

        categoryService.insertCategories(list);
    }

    @Test
    public void deleteCategoryById() throws Exception {
        categoryService.deleteCategoryById(100L);
    }

    @Test
    public void updateCategory() throws Exception {
        Category category = categoryService.findCategoryById(2L);
        category.setName("修改你的名称");
        categoryService.updateCategory(category);
    }

    @Test
    public void findAll() throws Exception {
        Iterable<Category> all = categoryService.findAll();
        for (Category category : all) {
            System.err.println(category.toString());
        }

    }

    @Test
    public void findCategoryById() throws Exception {
        Category category = categoryService.findCategoryById(99L);
        System.err.println(category.toString());
    }


    @Test
    public void findCategoryByPage() {
        Page<Category> page = categoryService.findCategoryByPage(5-1, 20);
        System.out.println("总页数"+page.getTotalPages());
        System.out.println("总记录数目"+page.getTotalElements());
        System.out.println("当前页面"+(page.getNumber()+1));
        System.out.println("当前页面的内容"+page.getContent());
        System.out.println("当前页面的记录数目"+page.getNumberOfElements());



    }

    @Test
    public void findCategoryByPageAndOrder() {

        Page<Category> pageAndOrder = categoryService.findCategoryByPageAndOrder(0, 5);
        List<Category> content = pageAndOrder.getContent();
        for (Category category : content) {
            System.out.println(category.toString());
        }
    }



}
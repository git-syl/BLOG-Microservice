package cn.syl.mainweb.controller;

import cn.syl.blogmain.pojo.Category;
import cn.syl.blogmain.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: syl  Date: 2017/10/28 Email:nerosyl@live.com
 */
//@RestController()
@Controller
@RequestMapping("/admin")
public class CategoryController {
    //idea 不能检测RPC的注入??
   // @Resource
    private CategoryService categoryService;
    @RequestMapping("/hello")
    public String hello(){
        Category category = new Category();
        category.setName("java");
        category.setHasParent(false);
        category.setStatus((byte) 1);
        categoryService.saveCategory(category);
        return "hello";
    }

    @RequestMapping("/t1")
    public String index(Model model){
        model.addAttribute("name","username");
      return "hello";
    }


}

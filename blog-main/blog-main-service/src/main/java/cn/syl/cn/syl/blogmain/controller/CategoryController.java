package cn.syl.cn.syl.blogmain.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: syl  Date: 2017/10/28 Email:nerosyl@live.com
 */
@RestController()
@RequestMapping("/category")
public class CategoryController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

}

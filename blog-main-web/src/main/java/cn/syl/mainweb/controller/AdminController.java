package cn.syl.mainweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author: syl  Date: 2017/11/4 Email:nerosyl@live.com
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/home")
    public String index(){
        return "starter";
    }


    @GetMapping("/vcode")
    public String welcome(Map<String, Object> model) {
        return "validatecode";
    }
}

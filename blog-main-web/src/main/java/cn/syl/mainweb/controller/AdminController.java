package cn.syl.mainweb.controller;

import cn.syl.blogcom.utils.BlogResult;
import cn.syl.blogcom.utils.CookieUtils;
import cn.syl.blogmain.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTML;
import java.util.Map;

/**
 * @author: syl  Date: 2017/11/4 Email:nerosyl@live.com
 */
@Controller
@RequestMapping("/admin")
public class AdminController {


    @RequestMapping("/home")
    public String index(){
        return "starter";
    }



}

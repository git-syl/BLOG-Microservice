package cn.syl.sso.controller;

import cn.syl.blogcom.utils.BlogResult;
import cn.syl.blogcom.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author: syl  Date: 2017/11/5 Email:nerosyl@live.com
 */
//@RestController
public class LoginController {
//    @Resource
//    UserService userService;
//
//    @Value("${user.cookie}")
//    private String USE_COOKIE_PRE;
//
//    //@GetMapping("/login")
//   // public String loginPage(){
////        return "login";
////    }
//
//
//    @GetMapping("/login")
//    public BlogResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
//        BlogResult result = userService.login(username, password);
//        if (result.getSuccess()==1){
//            CookieUtils.setCookie(request,response,USE_COOKIE_PRE,result.getData().toString());
//        }
//        return  result;
//
//    }
}

package cn.syl.mainweb.controller;

import cn.syl.blogcom.utils.BlogResult;
import cn.syl.blogcom.utils.CookieUtils;
import cn.syl.blogmain.pojo.User;
import cn.syl.blogmain.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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
//@RequestMapping("/manger")
@RequestMapping("${manger.home.pre}")
public class AdminController {

    @Autowired
    UserService userService;

    @Value("${user.cookie}")
    private String USE_COOKIE_PRE;

    @Value("${login.url}")
    public String LOGIN_URL;

    @Value("${manger.home.url}")
    private String LOGIN_SUCCESS_URL;



    @RequestMapping("/home")
    public String indexPage(){
        return "starter";
    }

    //由于已经通过单点登录系统了,shiro在此时只做权限分配,不参与密码校验
    @RequestMapping("/shiro")
   // public String shiroValidate(String redisToken,HttpServletRequest request){
    public String shiroValidate(String redisToken,HttpServletRequest request){

        //从redis获取用户信息,找不到说明登录过期需要重新登录
        String cookieToken = CookieUtils.getCookieValue(request, USE_COOKIE_PRE);
        System.err.println("cookieToken--->"+cookieToken);
        User user = userService.findUserByToken(cookieToken);
        if (user==null){
            return "redirect:"+LOGIN_URL;
        }

        Subject subject = SecurityUtils.getSubject();
        // 用户名密码令牌对象
        AuthenticationToken token = new UsernamePasswordToken(user.getUsername(), "");
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:"+LOGIN_URL;
        }
//        User user = (User) subject.getPrincipal();
//        // user放入session
//        request.getSession().setAttribute("loginUser", user);
        //todo:页面跳转了两次. shiro一次
       // return "/";
        return "redirect:"+LOGIN_SUCCESS_URL;

    }

    @RequestMapping("/logout")
    public String safeLogout(HttpServletRequest request,HttpServletResponse response){
        String cookieToken = CookieUtils.getCookieValue(request, USE_COOKIE_PRE);
        userService.delTokenByCookieToken(cookieToken);
        CookieUtils.deleteCookie(request,response,USE_COOKIE_PRE);
        return "redirect:"+LOGIN_URL;
    }



}

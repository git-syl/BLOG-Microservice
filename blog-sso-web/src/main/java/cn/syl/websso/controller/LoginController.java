package cn.syl.websso.controller;

import cn.syl.blogcom.utils.BlogResult;
import cn.syl.blogcom.utils.CookieUtils;
import cn.syl.sso.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: syl  Date: 2017/11/6 Email:nerosyl@live.com
 */
@Controller
@RequestMapping("/admin")
public class LoginController {


    @Value("${user.cookie}")
    private String USE_COOKIE_PRE;

    @Value("${manger.url}")
    private String REDIRECT_MANGER_URL;

    @Autowired
    LoginService loginService;


    @RequestMapping("/vcode")
    public String validateCode(){
        return "validatecode";
    }

    @RequestMapping
    public String loginPage(Model model){
        model.addAttribute("redirectUrl",REDIRECT_MANGER_URL);
        return "login";
    }



    @RequestMapping(value = "/login",produces = { "application/json;charset=UTF-8"})
    @ResponseBody
    public BlogResult login(String username, String password,String authcode, HttpServletRequest request, HttpServletResponse response) {

        String picCode = request.getSession().getAttribute("key").toString();

        if (!picCode.equals(authcode)){
            return BlogResult.no("验证码错误");
        }
        BlogResult result = loginService.login(username, password);
        if (result.getSuccess()==1){
            CookieUtils.setCookie(request,response,USE_COOKIE_PRE,result.getData().toString());
        }


        return  result;

    }

    @RequestMapping(value = "/mlogin",produces = { "application/json;charset=UTF-8"})
    @ResponseBody
    public BlogResult loginM(String username, String password,String authcode, HttpServletRequest request, HttpServletResponse response) {

        String picCode = request.getSession().getAttribute("key").toString();

        if (!picCode.equals(authcode)){
            return BlogResult.no("验证码错误");
        }
        BlogResult result = loginService.login(username, password);
        if (result.getSuccess()==1){
            CookieUtils.setCookie(request,response,USE_COOKIE_PRE,result.getData().toString());
        }

        return  result;

    }



}

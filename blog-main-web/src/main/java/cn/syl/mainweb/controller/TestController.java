package cn.syl.mainweb.controller;

/**
 * test shiro
 *
 * @author: syl  Date: 2017/11/1 Email:nerosyl@live.com
 */

import cn.syl.blogmain.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class TestController {

   // @Autowired
    //private Ijedis

    @RequestMapping("/thome")
    public String home(){
        return "starter";
    }

    @RequestMapping({"/tindex"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/tlogin", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/tlogin", method = RequestMethod.POST)
    public String login(String username, String password, HttpServletRequest request) {

        //String validateCode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        // if (StringUtils.isNotBlank(checkcode) && checkcode.equals(validateCode)) {
        // 使用shiri方式
        // 获得当前对象的状态:未认证
        Subject subject = SecurityUtils.getSubject();
        // 用户名密码令牌对象
        AuthenticationToken token = new UsernamePasswordToken(username,
                password);
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            return "login";
        }
        User user = (User) subject.getPrincipal();
        // user放入session
        request.getSession().setAttribute("loginUser", user);
        return "index";
    }

    /**
     * 用户添加;
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//权限管理;
    @ResponseBody
    public String userInfoAdd(){
        return "userInfoAdd";
    }

    @RequestMapping("/userView")
    @RequiresPermissions("userInfo:view")//权限管理;
    @ResponseBody
    public String userInfoView(){
        return "userView";
    }

    @RequestMapping("/userGG")
    @RequiresPermissions("userInfo:ggg")//权限管理;
    @ResponseBody
    public String userInfoGGG(){
        return "userInfoGG";
    }



}

package cn.syl.mainweb.config;

import cn.syl.mainweb.config.shiro.MyShiroRealm;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: syl  Date: 2017/11/1 Email:nerosyl@live.com
 */
@Configuration
public class ShiroConfiguration {//ShiroConfiguration {

//    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
//        System.out.println("ShiroConfiguration.shirFilter()");
@Bean
public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
    System.out.println("ShiroConfiguration.shirFilter()");
      //  ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
       // shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 身份认证realm;
     * (这个需要自己写，账号密码校验；权限等)
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }


    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *  所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * @return
     */
    @Bean
    public /*SimpleCredentialsMatcher*/HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));

        return hashedCredentialsMatcher;

//或:
//        SimpleCredentialsMatcher simpleCredentialsMatcher=  new SimpleCredentialsMatcher(){
//            @Override
//            public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//
//                UsernamePasswordToken upToken = (UsernamePasswordToken) token;
//                //将用户在页面输入的原始密码加密   param : 1.用户页面填写的密码, 加密的盐
//                //String pwd = Encrypt.md5(upToken.getPassword().toString(), upToken.getUsername());
//                String pwd = md5(new String(upToken.getPassword()), upToken.getUsername());
//                //3取出数据库加密的密码
//                Object dbPwd = info.getCredentials();  //从AuthRealm doGetAuthenticationInfo传入的密码,数据库查询的密码.
//
//                return  this.equals(pwd,dbPwd);
//            }
//        };
//
//        return simpleCredentialsMatcher;
    }

    public static String md5(String password, String salt){
        return new Md5Hash(password,salt,2).toString();
    }



}

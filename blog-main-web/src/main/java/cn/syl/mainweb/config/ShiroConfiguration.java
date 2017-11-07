package cn.syl.mainweb.config;

import cn.syl.mainweb.config.shiro.MyShiroRealm;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro权限配置类
 * @author: syl  Date: 2017/11/1 Email:nerosyl@live.com
 */
@Configuration
public class ShiroConfiguration {

    @Value("${manger.home.url}")
    private String LOGIN_SUCCESS_URL;

    @Value("${manger.shiro.url}")
    private String LOGIN_URL;

    @Value("${manger.home.logout}")
    private String LOGOUT_URL;

    /**
     * 配置shiro框架过滤器工厂
     * 设置一些过滤器
     */
    @Bean
public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
    System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        //filterChainDefinitionMap.put(LOGOUT_URL, "logout");

        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->

        //静态资源
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/images/**","anon");
        filterChainDefinitionMap.put("/lib/**","anon");

        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/","anon");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl(LOGIN_URL);
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl(LOGIN_SUCCESS_URL);
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        //
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 自定义身份认证realm;
     * 1.注入密码验证规则 hashedCredentialsMatcher
     * 2.向securityManager注入这个自定义的realm
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
        //注入缓存管理器;
        securityManager.setCacheManager(ehCacheManager());
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
    public SimpleCredentialsMatcher /*HashedCredentialsMatcher*/ hashedCredentialsMatcher(){

        //1:
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");//MD5算法;
//        hashedCredentialsMatcher.setHashIterations(2);//散列的次数
//        return hashedCredentialsMatcher;

//2:或:
        SimpleCredentialsMatcher simpleCredentialsMatcher=  new SimpleCredentialsMatcher(){
            @Override
            public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//
//                UsernamePasswordToken upToken = (UsernamePasswordToken) token;
//                //将用户在页面输入的原始密码加密   param : 1.用户页面填写的密码, 加密的盐
//                //String pwd = Encrypt.md5(upToken.getPassword().toString(), upToken.getUsername());
//                String pwd = md5(new String(upToken.getPassword()), upToken.getUsername());
//                //3取出数据库加密的密码
//                Object dbPwd = info.getCredentials();  //从AuthRealm doGetAuthenticationInfo传入的密码,数据库查询的密码.
//
//                return  this.equals(pwd,dbPwd);
                return true;
            }
        };

        return simpleCredentialsMatcher;
    }

    public static String md5(String password, String salt){
        return new Md5Hash(password,salt,2).toString();
    }

    /**
     *  开启shiro aop注解支持: eg:@RequiresPermissions("userInfo:ggg")//权限管理;
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中：
     * 安全管理器：securityManager
     */
    @Bean
    public EhCacheManager ehCacheManager(){
        System.out.println("ShiroConfiguration.getEhCacheManager()");
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:shiro/ehcache-shiro.xml");
        return cacheManager;
    }



}

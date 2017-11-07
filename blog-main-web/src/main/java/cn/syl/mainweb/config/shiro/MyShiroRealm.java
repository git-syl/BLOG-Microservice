package cn.syl.mainweb.config.shiro;


import cn.syl.blogmain.pojo.Authority;
import cn.syl.blogmain.pojo.Role;
import cn.syl.blogmain.pojo.User;
import cn.syl.blogmain.repository.UserRepository;
import cn.syl.blogmain.service.AuthorityService;
import cn.syl.blogmain.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Realm
 * @author: syl  Date: 2017/11/1 Email:nerosyl@live.com
 */
public class MyShiroRealm extends AuthorizingRealm {


    @Resource
    private UserService userService;

    @Resource
    private AuthorityService authorityService;

    @Value("${password.salt}")
    String salt;

    //验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.err.println("MyShiroRealm.doGetAuthenticationInfo()");
       String username = (String) token.getPrincipal();
        System.err.println(username);

        User user = userService.findUserByUsername(username);

        if (user==null){
            return null;
        }

        System.err.println(salt);

        //1:
//        SimpleAuthenticationInfo authenticationInfo =
//                new SimpleAuthenticationInfo(
//                        user, //用户对象
//                        user.getPassword(), //密码
//                        ByteSource.Util.bytes(username+salt),//salt=username+salt
//                        getName()  //realm name
//                );

        //2:或:
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username, //用户名
                user.getPassword(), //密码""
                getName()  //realm name
        );

        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //为用户授予权限 staff-list
        //info.addStringPermission("staff-list");
        //获取当前登录对象
        //User user = (User) SecurityUtils.getSubject().getPrincipal();//User user2 = (User) principals.getPrimaryPrincipal();

        User user = (User) principalCollection.fromRealm(this.getName()).iterator().next();//根据传入的值选择realm

        if ("admin".equals(user.getUsername())) {

            Iterable<Authority> iterable = authorityService.findAll();
            iterable.forEach(auriter->{
                info.addStringPermission(auriter.getCode());
                System.err.println("admin有权限:"+auriter.getCode());
            });

        }else {

            Set<Authority> authoritySet = new HashSet<>(0);
            //根据用户id查询他的权限
            User userEntity = userService.findUserByUsername(user.getUsername());
            Set<Role> roles = userEntity.getRoles();
            //遍历用户的权限 取出权限
            roles.forEach(role -> {
                authoritySet.addAll(role.getAuthorities());
            });
            authoritySet.forEach(authority -> {
                info.addStringPermission(authority.getCode());
                System.err.println("有权限:"+authority.getCode());
            });

        }

        return info;

    }


}

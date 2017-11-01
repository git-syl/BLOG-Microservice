package cn.syl.mainweb.config.shiro;

import cn.syl.blogmain.pojo.User;
import cn.syl.blogmain.repository.UserRepository;
import cn.syl.blogmain.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * @author: syl  Date: 2017/11/1 Email:nerosyl@live.com
 */
public class MyShiroRealm extends AuthorizingRealm {

  //  @Resource
  //  UserRepository userRepository;

    @Resource
    UserService userService;

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

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username, //用户名
                user.getPassword(), //密码
                getName()  //realm name
        );
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


}

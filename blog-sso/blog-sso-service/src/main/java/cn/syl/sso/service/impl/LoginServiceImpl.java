package cn.syl.sso.service.impl;

import cn.syl.blogcom.utils.BlogResult;
import cn.syl.blogcom.utils.JsonUtils;
import cn.syl.blogcom.utils.ShiroMD5;
import cn.syl.blogmain.pojo.User;
import cn.syl.blogmain.repository.UserRepository;
import cn.syl.sso.jedis.IJedis;
import cn.syl.sso.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author: syl  Date: 2017/11/6 Email:nerosyl@live.com
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Value("${password.salt}")
    private String salt;

    //user.session=USER_SESSION:
    @Value("${user.session}")
    private String USER_SESSION_PRE;

    @Value("${session.expire}")
    private int sec;


    @Autowired
    UserRepository userRepository;

    @Autowired
    IJedis jedis;

    @Transactional(readOnly = true) //jackjon no session ?? todo: 2 非只读事务会触发 flushed从而更新数据  ?
    //json转化死循环 ? todo:
    //dubbo 接收的entity序列化出错? todo:
    @Override
    public BlogResult login(String username, String password) {
        System.err.println("LoginServiceImpl-->login");
        User user =  userRepository.findUserByUsername(username);

        if (user==null){
            return  BlogResult.no("用户名无效");
        }

        String dbpassword = user.getPassword();

//        String dbpassword = user.getPassword()==null? "":user.getPassword();
//        String shiropassword = ShiroMD5.md5(password, username + salt)==null?"x":ShiroMD5.md5(password, username + salt);
//        System.err.println("dbpassword"+dbpassword);  System.err.println("shiropassword"+shiropassword);

        //验证密码
        if (dbpassword.equals(ShiroMD5.md5(password, username + salt))){
            String token = UUID.randomUUID().toString();
            //把用户信息 保存到redis中,为了安全排除密码
            user.setPassword(null);
          //  User user1 = new User();
           //// BeanUtils.copyProperties(user,user1); //copy 一份用户信息
           // user1.setPassword(null);
            String json = JsonUtils.objectToJsonExclude(user,"exRoleF","roles");
            System.err.println("LoginServiceImpl-->afterobjectToJsonExclude");
            jedis.set(USER_SESSION_PRE+token, json);
            //redis过期时间
            jedis.expire(USER_SESSION_PRE+token,sec);

            return BlogResult.ok(token);
        }
        return BlogResult.no("用户名或密码错误");
    }
}
